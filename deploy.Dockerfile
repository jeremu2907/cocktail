FROM gradle:8.11.0-jdk17-alpine

WORKDIR /opt/app
ENV HOME /opt/app

COPY . .

RUN gradle clean && \
    gradle build -x test
