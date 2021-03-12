FROM adoptopenjdk/openjdk11:alpine-jre-nightly

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENV CLAMAV_HOST="127.0.0.1"
ENV CLAMAV_PORT=3310

ENTRYPOINT ["java", "-jar", "-Dclamav.host=${CLAMAV_HOST}", "-Dclamav.port=${CLAMAV_PORT}", "/app.jar"]