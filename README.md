# Hi, I'm Raluca! 👋

Here you can find the documentation of the E-commerce project

## 🚀 About Me
💻(Aspiring) back-end software developer | 👨‍💻Helping companies to build great back-ends | Java, Spring Boot | Passionate about solving problems using technology

## 🛠 Skills
Back-end development · Unit Testing · Java · Algorithms · OOP · Relational Databases · SQL · Git · HTML · CSS · Web services · REST APIs · Spring Boot · Spring Framework · MySQL · Data Structures · Web Development · Software Development

## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://kimkalura.github.io/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/floriana-raluca-deftu/)

# E-commerce
This application was developed for transactions made on the Internet.

## Features
As a client, I can:
- add item to cart
- view all products from user cart
- delete cart item from user cart
- delete all user cart items
- add order
- view order details
- add item to wishlist
- view all items by user
- delete product from user wishlist

As an admin, I can:
- add category
- view category
- update category
- add product
- view all products by category
- update product
- view all orders by user

## Built with

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)


## API Reference

#### Add item to cart

```http
  POST /cart/add
```

| Parameter | Type   | Description                                           |
|:----------|:-------|:------------------------------------------------------|
| `body`    | `json` | **Required**.  The properties of the cart to be added |

Request body example:

```json
{
  "productId": 0,
  "quantity": 0
}
```  

#### View all products from user cart

```http
  GET /cart/${userId}
```
| Parameter | Type     | Description                         |
|:----------|:---------|:------------------------------------|
| `id`      | `string` | **Required**. Id of a user to fetch |

Postman example:

![App Screenshot](https://i.imgur.com/0n4KpK5.png)


#### Delete cart item from user cart

```http
  DELETE /cart/delete/${cartItemId}
```

#### Delete all user cart items

```http
  DELETE /cart/delete/user/${userId}
```

#### Add order

```http
  POST /order/add
```

| Parameter | Type   | Description                                    |
|:----------|:-------|:-----------------------------------------------|
| `body`    | `json` | **Required**. Properties of the order to added |

Request body example:

```json
{
  "product": 0,
  "quantity": 0
}
```  

Postman example:

![App Screenshot](https://i.imgur.com/EPPm4l7.png)


#### View order details

```http
  GET /order/details/{orderId}
```

#### Add item to wishlist

```http
  POST /wishlist/add
```

| Parameter | Type   | Description                                       |
|:----------|:-------|:--------------------------------------------------|
| `body`    | `json` | **Required**. Properties of the wishlist to added |

Request body example:

```json
{
  "productId": 0,
  "userId": 0
}
```  


#### View all items by user

```http
  POST /wishlist/${userId}
```

#### Delete product from user wishlist

```http
  DELETE /wishlist/delete
```

#### Add category

```http
  POST /category/create
```
| Parameter | Type   | Description                                          |
|:----------|:-------|:-----------------------------------------------------|
| `body`    | `json` | **Required**. The properties of category to be added |

Request body example:

```json
{
  "name":"string",
  "description": "string"
}
```  

#### View category

```http
  GET /category/
```

#### Update category

```http
  GET /category/update/${categoryId}
```
| Parameter | Type     | Description                                            |
|:----------|:---------|:-------------------------------------------------------|
| `id`      | `string` | **Required**. Id of the category to fetch              |
| `body`    | `json`   | **Required**. The properties of category to be updated |

Request body example:

```json
{
  "name":"string",
  "description": "string"
}
```  

#### Add product

```http
  POST /product/create
```

| Parameter | Type   | Description                                        |
|:----------|:-------|:---------------------------------------------------|
| `body`    | `json` | **Required**. The properties of the product to add |

Request body example:

```json
{
  "name":"string",
  "description": "string",
  "price": 0,
  "categoryId": 0
}
```  

Postman example:

![App Screenshot](https://i.imgur.com/5cyr3le.png)

#### View all products by category

```http
  GET /product/
```

Postman example:

![App Screenshot](https://i.imgur.com/ZZ3mAe0.png)


#### Update product

```http
  PUT /product/update/${productId}
```

| Parameter | Type   | Description                                        |
|:----------|:-------|:---------------------------------------------------|
| `id`      | `json` | **Required**. Id of the product to fetch           |
| `body`    | `json` | **Required**. The properties of the product to add |


Request body example:

```json
{
  "name": "string",
  "description": "string",
  "price": 0,
  "categoryId": 0
}
```

Postman example:

![App Screenshot](https://i.imgur.com/X9gZHpi.png)


#### View all orders by user

```http
  GET /order/${userId}
```


## API Authentication and Authorization

There are only two requests which don't require authorization headers.

#### Authenticate (login)

```http
  POST /authenticate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to authenticate  |

Request body example:

```json
{
  "password": "string",
  "username": "string"
}
```  

#### Register

```http
  POST /authenticate
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `body` | `json` | **Required**. The properties of user to register  |

Request body example:

```json
{
  "email": "string",
  "password": "string",
  "username": "string"
}
```  
After running the authenticate request, the client will obtain an access token that will be used in all subsequent request in order to authenticate the user and to authorize the user based on its role.

This is an example of what should be included in the request header:

```http
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjcxMTQzMzEyfQ.dxIzsD9Bm8y_kw3MOoZ2JXIKOg--uZaA5XNtBLdGYc4Ps3nlzBFDwBJi0bEeHlCggonZ6nQ2zwCI0D5a7dXjmw
```  
## Prerequisites

For building and running the application you need:
- JDK 1.8 or higher
- Maven 3

For deploying on Heroku you need:
- GIT
- Heroku CLI
## Dependencies

You don't need any additional dependencies.
All dependecies related to database management, server management, security management and so on, will be automatically injected by Maven using the pom.xml file located in the root folder of the project.
## Installation

Clone the project

```bash
  git clone https://github.com/KimKalura/E-commerce
```

Go to the project directory

```bash
  cd my-project
```

## Run Locally

Use maven to build the app and, to run it, and to start the local embedded Tomcat server

```bash
  mvn spring-boot:run
```


## Running Tests

To run tests, run the following command

```bash
  mvn test
```


## Environment Variables

You can deploy this project using Heroku or other providers as well, by specifying the following environment variables:

`PROFILE`

`MYSQL_URL`

`MYSQL_USER`

`MYSQL_PASS`



## Deployment

To deploy this project run

```bash
  git push heroku master
```


## Usage

You cand use the a demo version of the app, using SwaggerUI and following this link:

```bash
https://pure-tundra-12782.herokuapp.com/swagger-ui/
```

First, obtain an access token by running the /authenticate endpoint with username "user" and password "pass", which will grant you admin rights in the application.

![App Screenshot](https://i.imgur.com/GX34Sdn.png)

After that, authorize yourself by clicking the authorize button and copy-pasteing the token from the response.

![App Screenshot](https://i.imgur.com/arTX2Ke_d.webp?maxwidth=760&fidelity=grand)

From now on, you can use all other available endpoints, as per swagger documentation.

## Badges

![Maintained](https://img.shields.io/badge/Maintained%3F-yes-green.svg)
![GIT](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white)
![Heroku](https://img.shields.io/badge/heroku-%23430098.svg?style=for-the-badge&logo=heroku&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![JWT](https://img.shields.io/badge/json%20web%20tokens-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)


