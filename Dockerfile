FROM maven:3.8.3-openjdk-17
WORKDIR /app
ADD target/customerManagementSystem-0.0.1-SNAPSHOT.jar customerManagementSystem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "customerManagementSystem-0.0.1-SNAPSHOT.jar"]
#RUN mvn clean package -DskipTests
