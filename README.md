# Gamehub (Backend)
<a href="https://github.com/C-M-limited/frontend-gamehub">Click Here To Find the Frontend Code</a>
## Why Gamehub ?
<p>The inspiration behind creating the GameHub was a defining moment in my gaming journey. It all began when I embarked on a mission to acquire a second-hand copy of GTA5 from the Facebook Marketplace. Little did I know that this quest would reveal a glaring issue in the gaming world.

I quickly realized that the existing second-hand trading platforms were far from being gamer-friendly. They were designed with a general audience in mind, unable to grasp the unique needs of gamers. In my case, I sought a specific game – GTA5 for the PS4.

Imagine my surprise when, upon typing 'GTA 5' into the search bar, I was inundated with an overwhelming array of results. The list spanned multiple gaming consoles, from PS3 to PS5, Xbox 360 to Xbox Series X. I found myself manually filtering out the PS4 games from the rest.

But the challenges didn't end there. Some listings used full game names like 'Grand Theft Auto' instead of 'GTA,' while others were presented in the gaming nicknames.

As a gamer, I realized the frustration of this quest was all too common. The time wasted in navigating these complexities left a mark. That's when the idea for the GameHub was born.

The GameHub is our answer to these gaming woes. Designed by gamers, for gamers, it streamlines the hunt for the best deals and makes buying second hand game more accessible. No more sifting through irrelevant listings. We created the GameHub to be your gaming sanctuary, a place where gamers unite, share, and uncover the best deals with ease.</p>

## Tech Using
<p>Frontend: Typescript, Next.JS, Redux, Material UI</p>
<p>Backend: Java, Spring Boot, PostgreSQL, Redis, AWS, Render, Supabase</p>
<p>Developing Tools: Docker</p>

## Development Log
<ul>
  <li><span>2023 - 10 - 25:  </span> UI enhancement, switching from dark theme to light theme</li>
  <li><span>2023 - 10 - 19:  </span> Switching backend hosting platform from AWS to Render</li>
  <li><span>2023 - 10 - 19:  </span> Switching storage hosting platform from AWS to Supabase</li>
  <li><span>2022 - 02 - 27:  </span> Add counting post click rate function</li>
  <li><span>2022 - 02 - 11:  </span> Add Subscribe Post function</li>
  <li><span>2022 - 02 - 05:  </span> First Version Released</li>
</ul>

## Next Goal
<ul>
  <li>Adding Console selection and filtering</li>
</ul>

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
docker run --name pgadmin -e PGADMIN_DEFAULT_EMAIL=chrisleebed@gmail.com -e PGADMIN_DEFAULT_PASSWORD=ubuntu -p 5555:80 -d dpage/pgadmin4
#### Pgadmin connection to postgres 
<img src="https://github.com/C-M-limited/backend-gamehub/blob/main/README_IMG/Pgadmin%20%26%20Postgres%20connect.png" width=300 >

## Learning Material:
<a href="https://www.codejava.net/frameworks/spring-boot/connect-to-postgresql-database-examples">Create Model to Postgres</a>
<p/>
<a href="https://www.youtube.com/watch?v=f5bdUjEIbrg">JPA Relationships Tutorial</a>
<p/>
<a href="https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql"/>JPA Relationships Tutorial(works)</a>
