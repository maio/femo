IMAGE_NAME ?= maio/femo

pull:
	git pull

build-image:
	./gradlew bootBuildImage --imageName=$(IMAGE_NAME)

