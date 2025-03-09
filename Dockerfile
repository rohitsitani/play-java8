FROM openjdk:17-jdk-alpine
MAINTAINER rohitsitani@gmail.com
COPY src/ /src/
RUN javac /src/com/rohit/algorithms/MyAlgorithms.java -d /app
WORKDIR /app
CMD ["java","com.rohit.algorithms.MyAlgorithms"]
