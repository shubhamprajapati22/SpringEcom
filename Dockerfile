#FROM have image name with tag
FROM openjdk:27-ea-trixie
#ADD have source and destination path
ADD target/SpringEcom.jar /tmp/SpringEcom.jar
#ENTRYPOINT have command that will execute at start
ENTRYPOINT ["java", "-jar", "/tmp/SpringEcom.jar"]
#EXPOSE will ask to run on prot 8080
#EXPOSE 8080



#now run in command prompt

#docker build -t shubham/SpringEcom:v1 . //. denote the current directory of dockerFile