FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

COPY ./gradle /app/.gradle
COPY gradle /app/gradle
COPY gradlew /app/
COPY build.gradle.kts /app/
COPY settings.gradle.kts /app/

RUN chmod +x gradlew && ./gradlew build -x test --no-daemon --dry-run

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]