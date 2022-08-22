## TXT Vaperion - Online vape store

A vape store website made with Spring Boot, there is 3 roles: CUST, DIRE, and STAF. CUST can only visit homepage while STAF and ADMIN can visit both homepage and admin page. User can sign in and sign up with normal account or with OAuth2 account (GG,FB), edit profile, see orders history, reset password, and checkout, search & sort products. DIRE and STAF can visit admin page, CRUD User, Product, Order, Category (to be added), and have authorizing privilege, export data to excel, pdf, csv, upload excel, pdf, csv to db (to be added)

Stack used:
  *	Stack:
    -	Frontend: AngularJS, Thymeleaf, Bootstrap 5.2, JQuery
    -	Backend: Spring Boot, Spring Data JPA, Spring Security, OAuth2
    -	Database: SQL Server 2019
  *	Software:
    -	Visual Studio Code + Spring Boot extension
    -	[Adoptium Temurin JDK 17](https://adoptium.net/)

## Installation
1. Clone this repo, create database by running the db.sql script inside db folder
2. Config application.properties according to your credentials
  ```
    # for SQLServer Database connection
    spring.datasource.url=jdbc:sqlserver://localhost:1433;database=<your database>
    spring.datasource.username=<your username>
    spring.datasource.password=<your password>
    spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
    spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
    spring.jpa.show-sql=false
    spring.jpa.hibernate.ddl-auto = none
  ```
  - Create a app password for your gmail in order to send email in this webapp
  ```
    # for send mail contact
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.username=<your gmail username>
    spring.mail.password=<your gmail app password>
    spring.mail.properties.mail.smpt.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
  ```
  - Create Google, Facebook OAuth app and put your client-id, secret key to here (if you don't know how pls check this [link]   (https://www.codejava.net/frameworks/spring-boot/social-login-with-google-and-facebook-examples) )
  ```
    # Google
    spring.security.oauth2.client.registration.google.client-id=<your google client id>
    spring.security.oauth2.client.registration.google.client-secret=<your google client secret>
    spring.security.oauth2.client.registration.google.scope=email,profile

    # Facebook
    spring.security.oauth2.client.registration.facebook.client-id=<your facebook client id>
    spring.security.oauth2.client.registration.facebook.client-secret=<your facebook client secret>
    spring.security.oauth2.client.registration.facebook.scope=email,public_profile
  ```

## Visuals - Demo

- User homepage

![image](https://user-images.githubusercontent.com/52403567/185976060-377e0e9c-56b8-406e-aa80-d224edd1e714.png)

![image](https://user-images.githubusercontent.com/52403567/185976150-6e8b923f-abcd-4f2c-989a-c71f59af4da7.png)

![image](https://user-images.githubusercontent.com/52403567/185976439-d6daf87b-2f16-43d3-8785-d4d989ea4fac.png)

![image](https://user-images.githubusercontent.com/52403567/185976703-7a78a42d-ccd1-4c63-9066-a2fa06e46424.png)

![image](https://user-images.githubusercontent.com/52403567/185975720-0171ce13-6ad9-4822-9846-a93bf6695f6e.png)

![image](https://user-images.githubusercontent.com/52403567/185975794-c839c7b3-d1a6-4b65-a68e-ed7cd1329537.png)

![image](https://user-images.githubusercontent.com/52403567/185975922-b7785c69-1d3c-4c72-96c4-be819aca4999.png)

- Admin homepage

![image](https://user-images.githubusercontent.com/52403567/185976834-1abbf189-3e00-48ac-a952-01f66e49296c.png)

![image](https://user-images.githubusercontent.com/52403567/185977013-8d2e07db-9ceb-4e5f-b555-1e553511f90c.png)

![image](https://user-images.githubusercontent.com/52403567/185977079-ca612ff8-8a76-421f-8c53-f2ddcd053b25.png)

![image](https://user-images.githubusercontent.com/52403567/185977133-d1d135d6-4538-4e59-bc36-127cae09a90a.png)

![image](https://user-images.githubusercontent.com/52403567/185977182-2d447ad2-e7a0-4606-8372-c267b17c064d.png)


## Support
If things are unclear, PM me at [balisongian@gmail.com](mailto:balisongian@gmail.com)


## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.


## Authors and acknowledgment
Author of this project: [Jackytank](https://github.com/jackytank)

## License
[MIT](https://choosealicense.com/licenses/mit/)
