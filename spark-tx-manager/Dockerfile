FROM java:8

VOLUME /tmp

COPY target/spark-tx-manager-0.0.1-SNAPSHOT.jar spark-tx-manager.jar

RUN bash -c "touch /spark-tx-manager.jar"

EXPOSE 9030

ENTRYPOINT ["java","-jar","spark-tx-manager.jar"]