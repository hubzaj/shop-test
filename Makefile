IMG    := shop-test
LATEST := ${IMG}:latest

OWNER := OVERRIDE-WITH-ENV-OWNER-SUFFIX

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
	@helm template ${IMG} ./k8s/${IMG} -f ./k8s/${IMG}/values-integration.yaml --set name=${IMG}-${OWNER},onDemandSuffix=${OWNER} >> manifest/${IMG}-${OWNER}.yml

deploy-shop-test-job:
	@make bake-integration-test-job-manifest
	@kubectl apply -f manifest/${IMG}-${OWNER}.yml

display-test-result:
	@kubectl logs -l job-name=${IMG}-${OWNER}  --tail=1000

teardown-shop-test-job:
	@rm -rf manifest
	@kubectl delete job ${IMG}-${OWNER}
