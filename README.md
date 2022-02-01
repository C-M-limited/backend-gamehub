# backend-gamehub

## Docker DataBase SetUp:
### Postgres:
docker run --name gamehub_postgres -e POSTGRES_USER=user -e POSTGRES_PASSWORD=ubuntu -p 5432:5432 -d postgres
### Pgadmin 
docker run --name pgadmin -e PGADMIN_DEFAULT_EMAIL=chrisleebed@gamil.com -e PGADMIN_DEFAULT_PASSWORD=ubuntu -p 5555:80 -d dpage/pgadmin4
#### Pgadmin connection to postgres 
<img src="https://github.com/C-M-limited/backend-gamehub/blob/main/README_IMG/Pgadmin%20%26%20Postgres%20connect.png" width=300 >

## Learning Material:
<a href="https://www.codejava.net/frameworks/spring-boot/connect-to-postgresql-database-examples">Create Model to Postgres</a>
<p/>
<a href="https://www.youtube.com/watch?v=f5bdUjEIbrg">JPA Relationships Tutorial</a>
