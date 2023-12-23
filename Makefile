IMG    := shop-test
LATEST := ${IMG}:latest

build:
	@mvn clean package -DskipTests

run-integration-tests:
	@mvn test

build-docker-image:
	@docker build -t ${LATEST} .
