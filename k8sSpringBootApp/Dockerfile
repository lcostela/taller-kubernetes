FROM openjdk:11-jre-slim

ENV TZ America/Montevideo

COPY target/*.jar /opt/
RUN mv /opt/*.jar /opt/app.jar

RUN mkdir /etc/spring-boot-app
COPY target/classes/application.yml /etc/spring-boot-app

ENTRYPOINT exec java -jar /opt/app.jar --spring.config.location=file:/etc/spring-boot-app/application.yml