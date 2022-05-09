FROM java:8

COPY target/*.jar /pingpang-platform.jar

CMD ["--spring.profiles.active=product"]

EXPOSE 10010

ENTRYPOINT ["java", "-jar", "/pingpang-platform.jar"]