FROM java:8

VOLUME /tmp

COPY target/spark-flowable-biz-0.0.1-SNAPSHOT.jar spark-flowable.jar

RUN bash -c "touch /spark-flowable.jar"

EXPOSE 9030

ENTRYPOINT ["java","-jar","spark-flowable.jar"]