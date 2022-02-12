# docker run --name gamehub_postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=ubuntu -p 5432:5432 -d postgres
# docker run --name pgadmin -e PGADMIN_DEFAULT_EMAIL=chrisleebed@gamil.com -e PGADMIN_DEFAULT_PASSWORD=ubuntu -p 5555:80 -d dpage/pgadmin4
# docker run --name redis -p 6379:6379 -d redis
# docker run -di --name gamehub_backend -p 8080:8080 game-hub-backend.jar


#build image
# docker build -t game-hub-backend.jar .

FROM openjdk:17
COPY target/gameHubBackend.jar app.jar



#VOLUME /tmp
#EXPOSE 8080
#ADD target/gameHubBackend.jar gameHubBackend.jar
#ENTRYPOINT ["java","-jar","gameHubBackend.jar"]
