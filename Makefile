# https://www.gnu.org/prep/standards/html_node/Makefile-Basics.html#Makefile-Basics
SHELL = /bin/sh

all: help

help:
	@echo ""
	@echo "-- Help Menu"
	@echo ""
	@echo "   1. make sonar-analysis        - perform sonar analysis"
	@echo "   2. make sign-waiver           - GPG sign the WAIVER"
	@echo "   3. make release               - perform the next release"
	@echo "   4. make docker-verify         - verify the project inside a pre-defined docker container"

.PHONY: sonar-analysis
sonar-analysis:
	# http://docs.sonarqube.org/display/SONAR/Analyzing+with+Maven
	@mvn clean install
	@mvn sonar:sonar -Dsonar.host.url=http://localhost:59000 -Dsonar.pitest.mode=reuseReport

.PHONY: sign-waiver
sign-waiver:
	@gpg2 --no-version --armor --sign AUTHORS/WAIVER

.PHONY: release
release:
	@mvn release:prepare release:perform

.PHONY: docker-verify
docker-verify:
	@docker-compose -f build/docker/build-environment.yml run --rm --user=$(UID) build
	# findbugs likes to create these
	@rm -rf ?/
