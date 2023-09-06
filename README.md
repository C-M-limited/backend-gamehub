# backend-gamehub
## ERD Diagram:
<img src="https://github.com/C-M-limited/backend-gamehub/blob/main/README_IMG/Game_Hub_ERD.png" width=3000 >

## Design Idea:
#### Register Page:
<img src="https://github.com/C-M-limited/backend-gamehub/blob/main/README_IMG/Register.png" width=500 >

#### User Profile Page"
<img src="https://github.com/C-M-limited/backend-gamehub/blob/main/README_IMG/Profile.png" width=500 >

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
<p/>
<a href="https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql"/>JPA Relationships Tutorial(works)</a>
