FROM openjdk:8u212-jre
LABEL maintainer="wangdingfeng@live.com"
VOLUME /tmp
COPY spark-file-biz/target/spark-file-biz.jar spark-file.jar
RUN bash -c "touch /spark-file.jar"
EXPOSE 9080
ENTRYPOINT ["java","-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom","-jar","spark-file.jar"]