FROM gcr.io/distroless/java17-debian11

WORKDIR /deployment

COPY --chown=1001 target/*.jar ./app.jar

ENV SERVER_PORT 8080
ENV _JAVA_OPTIONS '-XX:+UseContainerSupport -XX:MaxRAMPercentage=90.0 -Dfile.encoding=UTF8'

EXPOSE 8080
EXPOSE 8081

USER 65535

ENTRYPOINT ["java","-jar","/deployment/app.jar"]