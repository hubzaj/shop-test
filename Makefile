IMG    := shop-test
LATEST := ${IMG}:latest

build:
	@mvn clean package -DskipTests

run-integration-tests:
	@mvn test

run-smoke-integration-tests:
	@mvn test -PsmokeTest

build-docker-image:
	@docker build -t ${LATEST} .
