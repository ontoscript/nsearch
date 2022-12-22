FROM openjdk:8-jdk-alpine
MAINTAINER ontoscript.com
COPY target/hr-0.0.1.jar
ENTRYPOINT ["java","-jar","/hr-0.0.1.jar"]
