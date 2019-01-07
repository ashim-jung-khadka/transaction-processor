FROM openjdk:8-jdk-alpine

ARG JAR_FILE
ADD target/${JAR_FILE} transaction-processor.jar

ENTRYPOINT ["java","-jar","/transaction-processor.jar"]