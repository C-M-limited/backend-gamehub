# backend-gamehub

## Docker DataBase SetUp:
### Postgres:
docker run --name gamehub_postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=ubuntu -p 5432:5432 -d postgres
### Pgadmin 
docker run --name pgadmin -e PGADMIN_DEFAULT_EMAIL=chrisleebed@gamil.com -e PGADMIN_DEFAULT_PASSWORD=ubuntu -p 5555:80 -d dpage/pgadmin4
#### Pgadmin connection to postgres 
<img src="https://github.com/bigbigphone2/BigTwoGame/blob/main/Screenshot%202021-12-13%20at%2010.12.58%20PM.png" width=800 >
