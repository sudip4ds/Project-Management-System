FROM openjdk:11
ADD target/mainclient.jar mainclient.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","mainclient.jar"]