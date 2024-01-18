pull:
	git pull

release: pull
	./gradlew bootBuildImage
