FROM openjdk:17-alpine
COPY target/SalasInfo-0.0.1-SNAPSHOT.jar /usr/local/lib/SalasInfo.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/SalasInfo.jar"]