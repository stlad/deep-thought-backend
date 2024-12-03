FROM openjdk:22-alpine
ARG JAR_FILE=deepthought.jar
COPY ${JAR_FILE} deepthought.jar
ENTRYPOINT ["java", "-jar", "/deepthought.jar"]