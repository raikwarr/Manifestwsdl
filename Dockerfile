ARG FROM_TAG=11
FROM ufstdms-dev-docker.repo.netcompany.com/ncops/ol7/java/jdk:${FROM_TAG}

EXPOSE 8080
COPY libs/integration-manifest-consume-0.0.1-SNAPSHOT.jar integration-manifest-consume.jar

ENTRYPOINT ["java"]
CMD ["-Dspring.profiles.active=default", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/integration-manifest-consume.jar"]



