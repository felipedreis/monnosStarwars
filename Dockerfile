FROM openjdk:8-jdk-alpine

COPY ./target/starwars-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch starwars-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","starwars-0.0.1-SNAPSHOT.jar"]