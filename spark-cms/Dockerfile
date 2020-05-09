FROM java:8

VOLUME /tmp

COPY targetspark-cms-0.0.1-SNAPSHOT.jar spark-cms.jar

RUN bash -c "touch /spark-cms.jar"

EXPOSE 9040

ENTRYPOINT ["java","-jar","spark-cms.jar"]