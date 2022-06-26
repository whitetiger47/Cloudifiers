FROM openjdk:8
ADD build/libs/cloudifiers-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "app.jar"]