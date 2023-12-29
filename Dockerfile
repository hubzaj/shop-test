FROM maven:3.8.8-eclipse-temurin-21

WORKDIR /app

COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

ENTRYPOINT ["mvn", "test"]
