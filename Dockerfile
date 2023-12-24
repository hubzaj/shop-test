FROM eclipse-temurin:17-jdk-jammy

RUN apt-get update
RUN apt-get install -y maven

WORKDIR /app

COPY pom.xml ./
COPY src ./src
RUN mvn clean package -DskipTests

ENTRYPOINT ["mvn", "test"]
