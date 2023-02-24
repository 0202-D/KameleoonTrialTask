FROM openjdk:17
EXPOSE 9090
ADD target/KameleoonTrialTask-0.0.1-SNAPSHOT.jar kameleoonTask.jar
ENTRYPOINT ["java", "-jar", "kameleoonTask.jar"]
