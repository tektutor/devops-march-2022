FROM alpine:3.12
MAINTAINER Jeganathan Swaminathan <mail2jegan@gmail.com>

ENV CLASSPATH mysql-connector-java-5.1.17-bin.jar:.

RUN apk add --no-cache openjdk8 

COPY MySQL.class /MySQL.class
COPY mysql-connector-java-5.1.17-bin.jar /mysql-connector-java-5.1.17-bin.jar

WORKDIR /

EXPOSE 80

CMD [ "java", "MySQL", "${SECRET_HOSTNAME}", "${SECRET_DATABASE}", "${SECRET_PORT}", "${SECRET_USERNAME}", "${SECRET_PASSWORD}" ]
