FROM openjdk:17-jdk-slim

RUN apt-get update && apt-get install -y dos2unix

WORKDIR /app

COPY gradlew ./
COPY gradle gradle
COPY build.gradle ./
COPY settings.gradle ./

RUN dos2unix gradlew

COPY src src

RUN ./gradlew build --no-daemon -x test

EXPOSE 8080

# 만약 루트 디렉토리 이름이 다르다면 "build/libs/(디렉토리이름)-0.0.1-SNAPSHOT.jar" 으로 바꿔주기!
ENTRYPOINT ["java", "-jar", "build/libs/MovieServer-0.0.1-SNAPSHOT.jar"]