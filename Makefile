# https://www.gnu.org/prep/standards/html_node/Makefile-Basics.html#Makefile-Basics
SHELL = /bin/sh

timestamp := $(shell /bin/date "+%Y%m%d%H%M%S")

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

.PHONY: docker-verify
docker-verify:
	@docker-compose -f build/docker/build-environment.yml run --rm --user=$(UID) build
	# findbugs likes to create these
	@rm -rf ?/

.PHONY: update-parent
update-parent: ##@maintenance Updates the Maven parent to its latest version
	@mvn versions:update-parent -U -DgenerateBackupPoms=false
	@git add pom.xml
	@git commit pom.xml -s -m 'Update to latest parent'

.PHONY: release-into-local-nexus
release-into-local-nexus: ##@release Releases all artifacts into a local nexus
	@mvn clean deploy scm:tag -Prelease -Drevision=$(timestamp) -DpushChanges=false -DskipLocalStaging=true -Drelease=local

.PHONY: release-into-sonatype-nexus
release-into-sonatype-nexus: ##@release Releases all artifacts into Maven Central (through Sonatype OSSRH)
	@mvn clean deploy scm:tag -Prelease -Drevision=$(timestamp) -DpushChanges=false -Drelease=sonatype
	@git push --tags origin master
