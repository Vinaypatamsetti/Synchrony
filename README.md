Used H2 in memory data base
Please find the connection details below

spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa

Implemented two apis
1. to save user which post mapping method , to save user we need to send User deatils in requestbody
Uri - http://localhost:8080/saveUser
Sample Request body =
{
    "name":"Patamsetti Vinay",
    "userName":"vinay",
    "password":"vinay@123",
    "age": 22
}
2.Fetch user that is get mapping method, fetching user based on userName and authentication based on the password and user name that a user need to send in request param and if the user is authenticated successfully  showing basic details to user
http://localhost:8080/getUserBasedOnName?userName=vinay&password=vinay@123

Tried to integrate Imgur api but unable to do that even from postman only upload is working and dont know where the uploaded image is stored , get image is responding with 401 status even after providing token. 
