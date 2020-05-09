FROM java:8

VOLUME /tmp

COPY target/spark-gateway-0.0.1-SNAPSHOT.jar spark-gateway.jar

RUN bash -c "touch /spark-gateway.jar"

EXPOSE 9001

ENTRYPOINT ["java","-jar","spark-gateway.jar"]