IMG    := shop-test
LATEST := ${IMG}:latest

build:
	@mvn clean package -DskipTests

run-integration-tests:
	@mvn test -PintegrationTest

run-smoke-integration-tests:
	@mvn test -PintegrationTest -PsmokeTest

build-docker-image:
	@docker build -t ${LATEST} .

create-manifest-dir:
	[ -d "manifest" ] || mkdir -p "manifest"

bake-integration-test-job-manifest:
	@make create-manifest-dir
	@helm template shop-test ./k8s/shop-test -f ./k8s/shop-test/values-integration.yaml>> manifest/shop-test.yml
