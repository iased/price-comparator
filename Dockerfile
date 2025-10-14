# ----------------------
# Stage 1: Build Angular Frontend
# ----------------------
FROM node:20-alpine AS frontend-build
WORKDIR /app
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ ./
RUN npm run build --prod

# ----------------------
# Stage 2: Build Spring Boot Backend
# ----------------------
FROM maven:3.9.11-eclipse-temurin-17 AS backend-build
WORKDIR /app
COPY backend/pom.xml .
COPY backend/src ./src
# Copy Angular build into Spring Boot resources (so backend serves frontend)
COPY --from=frontend-build /app/dist/frontend/browser ./src/main/resources/static
RUN mvn clean package -DskipTests

# ----------------------
# Stage 3: Run Application
# ----------------------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=backend-build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
