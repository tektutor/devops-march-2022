FROM alpine:3.12
MAINTAINER Jeganathan Swaminathan <mail2jegan@gmail.com>

RUN apk add --no-cache python3 py3-pip

RUN pip3 install flask

COPY hello.py /hello.py

WORKDIR /

EXPOSE 80 

CMD [ "python3", "hello.py" ]
