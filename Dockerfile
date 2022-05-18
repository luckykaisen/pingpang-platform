FROM java:8

COPY /target/*.jar /pingpang-platform.jar
COPY 顺序循环模板.xlsx /home/storage/pingpang/excel/template/顺序循环模板.xlsx

CMD ["--spring.profiles.active=product"]

EXPOSE 10010

ENTRYPOINT ["java", "-jar", "/pingpang-platform.jar"]