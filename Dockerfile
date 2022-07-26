FROM openjdk:17-alpine3.14
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/task_manager.jar
ADD ${JAR_FILE} task_manager.jar
ENTRYPOINT ["java", "-jar", "/task_manager.jar"]
