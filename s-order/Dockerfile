FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
COPY .gradle /app/.gradle
RUN chmod +x gradlew && ./gradlew build -x test

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]