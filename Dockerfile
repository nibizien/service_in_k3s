FROM openjdk:8-jdk-alpine
ARG PROJECT_VERSION
VOLUME /tmp
ADD target/docker_pod_info-${PROJECT_VERSION}.jar docker_pod_info.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /docker_pod_info.jar" ]
