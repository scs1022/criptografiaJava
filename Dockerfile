# Usa una imagen base con OpenJDK
FROM openjdk:17-jdk-slim AS build

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Instala Maven en el contenedor
RUN apt-get update && apt-get install -y maven

# Copia el contenido del proyecto al contenedor
COPY . .

# Compila la aplicación con Maven
RUN mvn clean package

# Usa una imagen base más ligera para la ejecución
FROM openjdk:17-jdk-slim

# Copia el archivo .jar desde la etapa de construcción
COPY --from=build /app/target/criptografia-0.0.1-SNAPSHOT.jar /criptografia.jar

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/criptografia.jar"]
