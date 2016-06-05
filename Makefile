all: help

help:
	@echo ""
	@echo "-- Help Menu"
	@echo ""
	@echo "   1. make sonar-analysis        - perform sonar analysis"

.PHONY: sonar-analysis
sonar-analysis:
	# http://docs.sonarqube.org/display/SONAR/Analyzing+with+Maven
	@mvn clean install
	@mvn sonar:sonar -Dsonar.host.url=http://localhost:59000
