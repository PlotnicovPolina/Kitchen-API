FROM adoptopenjdk/openjdk11:latest
EXPOSE 8081
ADD target/kitchen-api.jar kitchen-api.jar
ENTRYPOINT ["java", "-jar", "/kitchen-api.jar"]