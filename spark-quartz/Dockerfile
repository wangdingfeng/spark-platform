FROM java:8

VOLUME /tmp

COPY target/spark-quartz-0.0.1-SNAPSHOT.jar spark-quartz.jar

RUN bash -c "touch /spark-quartz.jar"

EXPOSE 9030

ENTRYPOINT ["java","-jar","spark-quartz.jar"]