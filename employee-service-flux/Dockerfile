FROM openjdk:oraclelinux8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} employee-service-flux.jar
ENTRYPOINT ["java","-jar","/employee-service-flux.jar"]
EXPOSE 9595