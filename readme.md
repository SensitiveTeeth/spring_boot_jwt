# wiki
- jwt expired: 3600 seconds(1 hour)
- application port: 8080
- database: H2 Java in memory database
___

Please run this application with IntelliJ

The Postman collection file is located in 
```
./spring_boot_jwt.postman_collection.json
```

# Readme
## Signup new account
*** User name must be unique in database ***

* API
http://localhost:8080/api/auth/signup

* method - POST
* request route param: None
* request query param: None
* request body param:
```
format: raw
{
    "username": <username>,
    "password": <password>
}
```
* return value: < username >

---
## Login an account

* API
http://localhost:8080/api/auth/login

* method - POST
* request route param: None
* request query param: None
* request body param:
```
format: raw
{
    "username": <username>,
    "password": <password>
}
```
* return value: < JWT token >
---
## Retrieve public content
* API
http://localhost:8080/api/test/all

* method - GET
* request route param: None
* request query param: None
* request body param: None
* return value: 
```
[
    {
        "id": 1,
        "content": "first content"
    },
    ...
]
```
---
## Access user’s content
*** Must call this api with JWT Token ***

*** JWT Token can be get with login API ***

* API
http://localhost:8080/api/test/user/{user_id}

* method - GET
* required header:
```
{ Authorization: Bearer <JWT token>}
```

* request route param: {user_id}
* request query param: None
* request body param: None
* return value: user's content
