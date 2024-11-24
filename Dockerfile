# pull base image that contains all required tools and libraries
FROM openjdk:17-jdk-alpine

# Create a folder where app code will be stored
WORKDIR /app

# Copy source code from host machine to your computer
COPY . . 

# compile the application code
RUN javac ExpenseSharingApp.java

# run the application
CMD ["java", "ExpenseSharingApp"]
