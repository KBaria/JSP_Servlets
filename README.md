# JSP_Servlet_Ecommerce_WebApp

### this is a simple ecommerce web application made with jsp, servlets, jdbc (MySQL) along with Javascript for DOM manipulation and making asynchronous requests to servlets and CSS to style pages

## It has features like :
1. login -- using jdbc to query database for authentication.
2. register -- using jdbc to insert a new user in the database.
3. password reset -- using jdbc to verify email and send an email with OTP to reset password using Jakarta mail.
4. logout -- using http sessions create and remove user sessions.
5. buy a product -- feature to buy a product along with setting up quantity of product.
6. adding products to cart -- feature to add products to cart managing quantity of products updating the cart and buying cart products.
7. viewing orders -- ability to view orders placed by user.

## Schema design

### Tables :

|Tables|
|------|
|user|
|user_address|
|product|
|category|
|cart|
|order_detail|

### user
|Field|Type|Key|
|-----|----|---|
|email|varchar(45)|primary key|
|first_name|varchar(45)||
|last_name|varchar(45)||
|password|varchar(45)||
|contact|varchar(10)||

### user_address
|Field|Type|Key|
|-----|----|---|
|email|varchar(45)|primary key|
|address|varchar(200)||
|state|varchar(45)||
|city|varchar(45)||
|pin|varchar(6)||

### product
|Field|Type|Key|
|-----|----|---|
|id|int|primary key|
|name|varchar(45)||
|description|varchar(100)||
|price|decimal(10,2)||
|category_id|int||

### category
|Field|Type|Key|
|-----|----|---|
|id|int|primary key|
|name|varchar(45)||
|description|varchar(100)||

### cart
|Field|Type|Key|
|-----|----|---|
|id|int|primary key|
|email|varchar(45)||
|product_id|int||
|qty|int||

### order_detail
|Field|Type|Key|
|-----|----|---|
|order_id|int|primary key|
|email|varchar(45)||
|order_date|datetime||
|product_id|int||
|qty|int||
