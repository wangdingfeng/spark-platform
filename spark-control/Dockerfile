FROM java:8

VOLUME /tmp

COPY target/spark-control-0.0.1-SNAPSHOT.jar spark-control.jar

RUN bash -c "touch /spark-control.jar"

EXPOSE 9002

ENTRYPOINT ["java","-jar","spark-control.jar"]