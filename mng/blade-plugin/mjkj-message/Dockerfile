FROM bladex/alpine-java:openjdk8-openj9_cn_slim

MAINTAINER 1337915488@qq.com

RUN mkdir -p /blade/message

WORKDIR /blade/message

EXPOSE 9873

ADD ./blade-message.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=dev"]
