FROM openjdk:8-jdk-alpine

COPY target/transaction-processor-1.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]