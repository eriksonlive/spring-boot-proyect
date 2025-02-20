# -----------------------------
# Etapa 1: Build de la aplicación (Compilación)
# -----------------------------
    FROM maven:3.9.6-eclipse-temurin-21 AS builder

    # Establece el directorio de trabajo dentro del contenedor
    WORKDIR /app
    
    # Copia los archivos del proyecto al contenedor
    COPY . .
    
    # Compila el proyecto y genera el JAR en la carpeta target
    RUN mvn clean package -DskipTests -DfinalName=auth-service
    
    # -----------------------------
    # Etapa 2: Imagen de Producción
    # -----------------------------
    FROM openjdk:21-jdk-slim
    
    # Establece un volumen (opcional, para logs o datos temporales)
    VOLUME /tmp
    
    # Copia el JAR desde la etapa de build
    COPY --from=builder /app/target/auth-service.jar app.jar
    
    # Expone el puerto de la aplicación (ajústalo según la configuración)
    EXPOSE 8081
    
    # Comando para iniciar la aplicación
    ENTRYPOINT ["java", "-jar", "/app.jar"]
    