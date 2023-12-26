IMG    := shop-test
LATEST := ${IMG}:latest

build:
	@mvn clean package -DskipTests

run-integration-tests:
	@mvn test -Dtest=org.shop.test.integration.**

run-smoke-integration-tests:
	@mvn test -Dtest=org.shop.test.integration.** -Dgroups=SmokeTest

build-docker-image:
	@docker build -t ${LATEST} .

run-test-in-docker:
	@docker run ${LATEST}

create-manifest-dir:
	[ -d "manifest" ] || mkdir -p "manifest"

bake-integration-test-job-manifest:
	@make create-manifest-dir
	@helm template shop-test ./k8s/shop-test -f ./k8s/shop-test/values-integration.yaml>> manifest/shop-test.yml

deploy-shop-test-job:
	@make bake-integration-test-job-manifest
	@kubectl apply -f manifest/shop-test.yml

teardown-shop-test-job:
	@rm -rf manifest
	@kubectl delete job shop-test
