jar:
	./gradlew shadowJar

docker: jar
	cd src/docker/ && cp ../../build/libs/letty-all.jar . && docker build -t letty:$(appVersion) .
