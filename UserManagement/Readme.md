#User Maangement Rest API


##Requirements
1. Open JDK 1.8.0
2. MySQL 8.0.13
3. Maven 3.6.1

##Steps to Setup

####1.Clone the application

https://github.com/gowrishankar95/Crud-Operations-Using-Rest-API-Spring.git

####2. Create Mysql database


create database userDatabase 
-- --
1. UserId    int(11)   PRI  auto_increment 
 userName | varchar(20) | YES  |     | NULL    |                |
-- 
| passowrd | varchar(20) | YES  |     | NULL    |                |
-- 
+----------+-------------+------+-----+---------+----------------+
-- 


####3. Change mysql username and password as per your installation**

    open src/main/resources/application.yml

    change spring.datasource.username and spring.datasource.password as per your mysql installation



####4. Build and run the app using maven

    mvn package
    java -jar target/UserManagement-0.0.1-SNAPSHOT.jar

    Alternatively, you can run the app without packaging it using -

    mvn spring-boot:run

    The app will start running at http://localhost:8080.
    
## Explore Rest APIs

The app defines following CRUD APIs.

    GET /api/users - return the list of users in databse
    
    POST /api/users{userName,password} - create a user
    
    GET /api/users/{userId} - return user
    
    PUT /api/users/{userId,oldPassword,newPassword} - change password of existing user 
    
    DELETE /api/users/{userId} - delete a user

You can test them using postman or any other rest client.

