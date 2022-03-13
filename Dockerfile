# docker run --name gamehub_postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=ubuntu -p 5432:5432 -d postgres
# docker run --name pgadmin -e PGADMIN_DEFAULT_EMAIL=chrisleebed@gamil.com -e PGADMIN_DEFAULT_PASSWORD=ubuntu -p 5555:80 -d dpage/pgadmin4
# docker run --name redis -p 6379:6379 -d redis
# docker run -di --name gamehub_backend -p 8080:8080 game-hub-backend.jar
# docker run --name jenkins -p 9191:8080 -p 50000:50000 -d -v jenkins_home:/var/jenkins_home jenkins/jenkins
# docker run -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" --name=myes docker.elastic.co/elasticsearch/elasticsearch:7.10.1
# docker run --link myes:elasticsearch -p 5601:5601 docker.elastic.co/kibana/kibana:7.10.1
#build image
# docker build -t game-hub-backend.jar .

FROM openjdk:17
COPY target/gameHubBackend.jar app.jar



#VOLUME /tmp
#EXPOSE 8080
#ADD target/gameHubBackend.jar gameHubBackend.jar
#ENTRYPOINT ["java","-jar","gameHubBackend.jar"]

#scp -i ~/Users/leeyathei/Documents/Project/GameHub/gameHubKeyPair.pem ~/Users/leeyathei/Documents/Project/GameHub/backend-gamehub/target/gameHubBackend.jar  ubuntu@ec2-3-1-49-27.ap-southeast-1.compute.amazonaws.com