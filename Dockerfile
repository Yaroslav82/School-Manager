FROM openjdk:21-jdk

ARG APP_VERSION=""

LABEL com.docker.product-name="School Manager" \
      org.opencontainers.image.title="School Manager" \
      org.opencontainers.image.version=${APP_VERSION}

WORKDIR /app

COPY school-manager-server/target/school-manager-server-1.0-SNAPSHOT.jar .

# For some reason port 8888 was not working.
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "school-manager-server-1.0-SNAPSHOT.jar"]