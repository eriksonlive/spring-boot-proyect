# Usa una imagen base con Java (por ejemplo, OpenJDK 17)
FROM openjdk:21-jdk-slim

# Define un volumen (opcional, para persistir logs u otros datos temporales)
VOLUME /tmp

# Copia el JAR construido al contenedor
COPY target/energias-renovables.jar app.jar

# Expone el puerto en el que corre la aplicación (según tu configuración, en este ejemplo 8081)
EXPOSE 8081

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]