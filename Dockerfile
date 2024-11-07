# Usa una imagen base con Maven y OpenJDK
FROM maven:3.8.6-openjdk-17 AS build

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el contenido de tu proyecto al contenedor
COPY . .

# Compila la aplicación
RUN mvn clean package

# Usa una imagen base de OpenJDK para la ejecución
FROM openjdk:17-jdk-slim

# Copia el archivo .jar desde la etapa de construcción
COPY --from=build /app/target/criptografia-0.0.1-SNAPSHOT.jar /criptografia.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/criptografia.jar"]
