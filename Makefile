.PHONY: help

help: ## Prints help for targets with comments
	@grep -E '^[a-zA-Z0-9.\ _-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

build-docker-image: ## Build docker image
#	@./mvnw clean package
	@docker build . -t ngotzmann/simple-file-virus-scanner:latest

push-latest-dockerhub: ## Push latest tag version to docker hub
	@docker push ngotzmann/simple-file-virus-scanner:latest
