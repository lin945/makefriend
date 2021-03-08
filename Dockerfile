FROM openjdk:8
MAINTAINER LIN945
# 暴露8080端口
EXPOSE 8080
# 往容器中添加jar包
ADD target/app.jar app.jar
ENTRYPOINT["java","-jar","app.jar"]
