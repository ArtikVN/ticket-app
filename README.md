# ticket-app



###Objective of the project:
* Practice building Java applications using Spring and Hibernate.
* Simulation of simple cinema functionality

###Basic functionality:
* User Registration
* User authentication and authorization
* View availability of sessions
* User Cart
* Placement of an order by the user

###Technologies used to create the application:
* Java 11
* MySQL
* Hibernate
* Spring (Core, WEB, Security)
* Apache Tomcat (to run app locally)
* Maven Checkstyle Plugin

###Required to run the application:
* configure TomCat
* In the DataInitializer, add your login details as an administrator
* In the db.properties file, configure the connection to the database
#####Application is ready to go

What requests can the user make:
```GET: /cinema-halls``` — display all the cinema halls<br>
```GET: /movies``` — display all the movies we have<br>
```GET: /movie-sessions/available``` — display all the available movie sessions<br>
```GET: /orders``` — display all the orders of the currently logged user<br>
```POST: /orders/complete``` — complete the current order<br>
```PUT: /shopping-carts/movie-sessions``` — add movie session to the shopping cart<br>
```GET: /shopping-carts/by-user``` — display the contents of the currently logged user's shopping cart<br>

What requests can an administrator make:
```GET: /users/by-email``` — find user by email<br>
```POST: /cinema-halls```— add new cinema hall<br>
```POST: /movies```— add new movie<br>
```POST: /movie-sessions```— add new movie session<br>
```PUT: /movie-sessions/{id}```— update movie session with specified id<br>
```DELETE: /movie-sessions/{id}```— delete movie session by id<br>