FROM maven:3.6.0-jdk-11 AS build  
COPY src /usr/src/app/src
COPY dev /usr/src/app/dev
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjre:11
COPY --from=build /usr/src/app/target/battleshipsboot-0.0.1-SNAPSHOT.jar /usr/app/battleshipsboot-0.0.1-SNAPSHOT.jar  
EXPOSE 9721
ENTRYPOINT ["java","-jar","/usr/app/battleshipsboot-0.0.1-SNAPSHOT.jar"]
