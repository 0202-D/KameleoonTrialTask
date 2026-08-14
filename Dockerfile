FROM eclipse-temurin:17-jdk

EXPOSE 9090

ADD target/KameleoonTrialTask-0.0.1-SNAPSHOT.jar kameleoonTask.jar

ENTRYPOINT ["java", "-jar", "kameleoonTask.jar"]
