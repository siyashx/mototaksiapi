package com.codesupreme.mototaksiapi.service.impl.courier;

import com.codesupreme.mototaksiapi.dao.courier.CourierRepository;
import com.codesupreme.mototaksiapi.dto.courier.CourierDto;
import com.codesupreme.mototaksiapi.model.courier.Courier;
import com.codesupreme.mototaksiapi.service.inter.courier.CourierServiceInter;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourierServiceImpl implements CourierServiceInter {

    private final CourierRepository courierRepository;

    private final ModelMapper modelMapper;

    public CourierServiceImpl(CourierRepository courierRepository, ModelMapper modelMapper) {
        this.courierRepository = courierRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CourierDto> getAllCouriers() {
        List<CourierDto> listDto = new ArrayList<>();

        List<Courier> listEntity = courierRepository.findAll();

        for (Courier entity : listEntity) {
            CourierDto dto = modelMapper.map(entity, CourierDto.class);
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public CourierDto getCourierById(Long id) {
        Courier entity = courierRepository.findById(id).orElse(null);
        if (entity != null) {
            return modelMapper.map(entity, CourierDto.class);
        }
        return null;
    }

    @Override
    public CourierDto updateCourier(Long id, CourierDto courierDto) {
        Optional<Courier> courierOptional = courierRepository.findById(id);

        if (courierOptional.isPresent()) {
            Courier courier = courierOptional.get();

            if (courierDto.getName() != null) {
                courier.setName(courierDto.getName());
            }

            if (courierDto.getSurname() != null) {
                courier.setSurname(courierDto.getSurname());
            }

            if (courierDto.getMotoImage() != null) {
                courier.setMotoImage(courierDto.getMotoImage());
            }

            if (courierDto.getMotoName() != null) {
                courier.setMotoName(courierDto.getMotoName());
            }

            if (courierDto.getMotoVolume() != null) {
                courier.setMotoVolume(courierDto.getMotoVolume());
            }

            if (courierDto.getCurrentLocation() != null) {
                courier.setCurrentLocation(courierDto.getCurrentLocation());
            }

            if (courierDto.getPhoneNumber() != null) {
                courier.setPhoneNumber(courierDto.getPhoneNumber());
            }

            if (courierDto.getPassword() != null) {
                courier.setPassword(courierDto.getPassword());
            }

            if (courierDto.getBalance() != null) {
                courier.setBalance(courierDto.getBalance());
            }

            if (courierDto.getOnline() != null) {
                courier.setOnline(courierDto.getOnline());
            }

            if (courierDto.getCurrentlyDelivering() != null) {
                courier.setCurrentlyDelivering(courierDto.getCurrentlyDelivering());
            }

            if (courierDto.getIsDisable() != null) {
                courier.setIsDisable(courierDto.getIsDisable());
            }

            courier = courierRepository.save(courier);
            return modelMapper.map(courier, CourierDto.class);
        }
        return null;
    }

    @Override
    public ResponseEntity<CourierDto> createCourier(CourierDto courierDto) {
        Courier courierEntity = modelMapper.map(courierDto, Courier.class);
        Courier savedCourier = courierRepository.save(courierEntity);
        return ResponseEntity.ok(modelMapper.map(savedCourier, CourierDto.class));
    }

    @Override
    public void deleteCourierById(Long id) {
        courierRepository.deleteById(id);
    }

    @Override
    public Boolean isCourierPhoneNumberTaken(String phoneNumber) {
        return courierRepository.findCourierByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public CourierDto findCourierByPhoneNumber(String phoneNumber) {
        Courier courier = courierRepository.findCourierByPhoneNumber(phoneNumber);
        if (courier != null) {
            return modelMapper.map(courier, CourierDto.class);
        }
        return null;
    }
}
