#FROM have image name with tag
FROM openjdk:27-ea-trixie
#ADD have source and destination path
ADD target/SpringEcom.jar /tmp/SpringEcom.jar
#ENTRYPOINT have command that will execute at start
ENTRYPOINT ["java", "-jar", "/tmp/SpringEcom.jar"]
#EXPOSE will ask to run on prot 8080
EXPOSE 8080


#multistage dockeer file
#it use to reduce image size. two or more images will be use to bulid ans run

#STAGE 1
#FROM maven:4.0.0-rc-5-eclipse-temurin-21-alpine AS builder
#
#WORKDIR /app
#COPY . .
#
#RUN mvn clean package -DskipTests
#
##STAGE-2
#FROM openjdk:27-ea-trixie AS runner
#WORKDIR /app
#
#COPY --from=builder /app/target/SpringEcom.jar SpringEcom.jar
#
#ENTRYPOINT ["java", "-jar", "SpringEcom.jar"]
#EXPOSE 8080

#now run in command prompt

#docker build -t shubham/SpringEcom:v1 . //. denote the current directory of dockerFile