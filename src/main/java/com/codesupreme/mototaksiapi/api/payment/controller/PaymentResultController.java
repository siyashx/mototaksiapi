package com.codesupreme.mototaksiapi.api.payment.controller;

import com.codesupreme.mototaksiapi.dao.courier.CourierRepository;
import com.codesupreme.mototaksiapi.model.courier.Courier;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/v7")
public class PaymentResultController {

    private static final String PRIVATE_KEY = "VSJnqi0QMovynR5x1cSjO44H";

    private final CourierRepository courierRepository;

    public PaymentResultController(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @PostMapping("/payment-result")
    public ResponseEntity<String> handlePaymentResult(@RequestBody Map<String, String> requestBody) {
        try {
            String dataEncoded = requestBody.get("data");
            String receivedSignature = requestBody.get("signature");

            if (dataEncoded == null || receivedSignature == null) {
                return ResponseEntity.badRequest().body("Missing data or signature");
            }

            // İmzanı yoxla
            String toHash = PRIVATE_KEY + dataEncoded + PRIVATE_KEY;
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] sha1Bytes = md.digest(toHash.getBytes(StandardCharsets.UTF_8));
            String calculatedSignature = Base64.getEncoder().encodeToString(sha1Bytes);

            if (!calculatedSignature.equals(receivedSignature)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
            }

            // JSON decode
            byte[] decodedBytes = Base64.getDecoder().decode(dataEncoded);
            String jsonString = new String(decodedBytes, StandardCharsets.UTF_8);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonString);

            String status = jsonNode.get("status").asText();
            String orderId = jsonNode.get("order_id").asText();
            String amountStr = jsonNode.get("amount").asText();

            if (!"success".equalsIgnoreCase(status)) {
                return ResponseEntity.ok("Payment status not success");
            }

            // orderId-dən courier ID-ni çıxar (nümunə: payment_courier_42_1716043225991)
            String[] parts = orderId.split("_");
            if (parts.length < 3) {
                return ResponseEntity.badRequest().body("Invalid order_id format");
            }

            Long courierId = Long.parseLong(parts[2]);
            double amount = Double.parseDouble(amountStr);

            // Courier tap və balansını artır
            Optional<Courier> optionalCourier = courierRepository.findById(courierId);
            if (optionalCourier.isPresent()) {
                Courier courier = optionalCourier.get();
                double currentBalance = courier.getBalance() != null ? courier.getBalance() : 0.0;
                courier.setBalance(currentBalance + amount);
                courierRepository.save(courier);
                return ResponseEntity.ok("Balance updated successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Courier not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing payment result: " + e.getMessage());
        }
    }
}
