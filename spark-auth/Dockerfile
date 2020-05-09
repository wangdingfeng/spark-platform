FROM java:8

VOLUME /tmp

COPY target/spark-auth-0.0.1-SNAPSHOT.jar spark-auth.jar

RUN bash -c "touch /spark-auth.jar"

EXPOSE 9010

ENTRYPOINT ["java","-jar","spark-auth.jar"]