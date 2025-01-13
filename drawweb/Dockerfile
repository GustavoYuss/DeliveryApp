# Etapa de compilación
FROM eclipse-temurin:22-jdk AS builder

WORKDIR /drawweb

# Copiamos el código fuente al contenedor
COPY . .

# Aseguramos que Maven Wrapper tenga permisos de ejecución
RUN chmod +x mvnw

# Ejecutamos Maven para construir el JAR
RUN ./mvnw clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:22-jdk

WORKDIR /app

# Copiamos el JAR generado en la etapa de compilación
COPY --from=builder /drawweb/target/DrawWeb-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto de la aplicación
EXPOSE 8080

# Comando de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
