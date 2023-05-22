# project-jdp-2305-02

It is a team project within the JAVA DEVELOPER course at KODILLA school.

Project goal:
Online store REST API (backend) based on a specified list of endpoints prepared (in principle) by frontend team.

Project team:
Six participants of the course worked on the project, and the whole project was supervised by an experienced project manager.

##API Documentation

### Demo

The best way to see results of this project is cloning it from git-hub and run, then try to test endpoints in Postman application for example.

### Requirements

* JVM (Java 17)
* MySQL database

### Launching the project

Create user in MySQL database with following configuration:
spring.datasource.url=jdbc:mysql://localhost:3306/kodilla_project?serverTimezone=Europe/Warsaw&useSSL=False
spring.datasource.username=kodilla_user
spring.datasource.password=kodilla_password

Project uses Gradle tool and Spring/ hibernate to build.

### Endpoint description

#### Cart

#### Group

* `//groupsentity`

GET method -> shows all groups, no values.

* `/groupsentity/{id}`

GET method -> returns a group with a specific id.

* `/groupsentity/{id}`

DELETE method -> deletes a group with a specific id.

* `/groupsentity/{id}`

PUT method -> updates a group with specific id, pass values into json request body ex.:

```
{
  "id": 1,
  "name": "Ubrania"
}
```

* `/groupsentity`

POST method -> create a new group, pass values into json request body ex.:

```
{
  "id": 1,
  "name": "Ubrania"
}
```

#### Order

* `/orders`

GET method -> shows all orders, no values.

* `/orders`

POST method -> create a new order, pass values into json request body ex.:

```
{
  "orderId": 0,
  "userId": 0,
  "cartId": 0,
  "orderStatus": "CREATED"
}
```

* `/orders/{orderId}`

GET method -> returns a order with a specific id, values: orderId.

* `/orders/{orderId}`

PATCH method -> updates the order, pass values into json request body ex.:

```
{
  "orderId": 0,
  "userId": 0,
  "cartId": 0,
  "orderStatus": "CREATED"
}
```

* `/orders/{orderId}`

DELETE method -> deletes a order with specific id, values: orderId.


#### Product

* `/products`

GET method -> shows all products, no values.

* `/products/{productId}`

GET method -> returns a product with a specific id, values: productId.

* `/products/{productId}`

DELETE method -> deletes product with specific id, values: productId.

* `/products/`

POST method -> create a new product, pass values into json request body ex.:

```
{
  "id": 1,
  "name": "kurtka zimowa"",
  "description": "Pellentesque tempus interdum quam ut rhoncus. Donec ullamcorper turpis dolor. Donec euismod pretium eros et eleifend. Aliquam vulputate faucibus lorem non auctor. Vivamus erat turpis, molestie a nisl non, scelerisque luctus enim. Nunc mi mi, laoreet ac mollis nec, pharetra sit amet tortor. Vivamus a bibendum purus.",
  "price": 100,
  "groupId": 1
}
```

* `/products/{productId}`

PATCH method -> update specific values of product with given {productId} and json with new values in fields to update ex.:

```
{
  "id": 1,
  "name": "kurtka zimowa",
  "description": "test",
  "price": 300,
  "groupId": 1
}
```

* `/products/{productId}`

PUT method -> replaces product with given of {productId} and json with new product data ex.:

```
{
  "id": 1,
  "name": "kurtka zimowa2",
  "description": "test2",
  "price": 150,
  "groupId": 1
}
```

#### User

* `/users`

GET method -> shows all users, no values.

* `/users/{id}`

GET method -> returns user with a specific id, values: id.

* `/users/{id}`

DELETE method -> deletes user with specific id, values: id.

* `/users/updateUser/{id}`

PUT method -> updates user with specific id, pass values into json request body.

* `/users`

POST method -> create a new user, pass values into json request body ex.:

```
{
  "id": 1,
  "username": "Piotr",
  "status": 1,
  "userKey": 59403,
  "keyExpirationTime": "2023-05-18T18:42:39.827Z"
}
```

* `/users/switchBlockade/{id}`

PATCH method -> block user with specific id, values: id.

* `/users/generateKey/{id}`

PATCH method -> generate key(token) for user with specific id valid 1 hour, values: id.


### What can the project be used for?

This project is most simplified backend layer of generic online store. It can be developed further, for specific User requirements.
