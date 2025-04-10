    FROM openjdk:21

    WORKDIR /app

    COPY ./build/libs/news-api-0.0.1-SNAPSHOT.jar .

    EXPOSE 8080

    CMD ["sh", "-c", "java -jar news-api-0.0.1-SNAPSHOT.jar"]