# Etapa de compilación
FROM eclipse-temurin:22-jdk AS builder

WORKDIR /app
COPY . .

# Aseguramos que gradlew tenga permisos de ejecución
RUN chmod +x gradlew
RUN ./gradlew bootJar

# Etapa de ejecución
FROM eclipse-temurin:22-jdk

WORKDIR /app

# Copiamos el JAR generado en la etapa de compilación
COPY --from=builder /app/build/libs/DeliveryApp-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto de la aplicación
EXPOSE 8080

# Comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
