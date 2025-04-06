FROM openjdk:17-jdk

# Sertifika dosyasını container içine kopyala
COPY keystore.p12 /app/keystore.p12

# Uygulama JAR dosyasını kopyala
ADD target/mototaksiapi-0.0.1-SNAPSHOT.jar mototaksiapi.jar

# HTTPS portunu aç
EXPOSE 8989

# Sağlık kontrolü (HTTPS isteği)
HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD curl --fail --insecure https://localhost:8989/ || exit 1

# Spring Boot uygulamasını başlat
ENTRYPOINT ["java", "-jar", "mototaksiapi.jar"]
