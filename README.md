# MyDMS

This is just a private project creating my own document management system, where you can upload files, add metadata to, categorize them and search for keywords or files in the whole system.\
The system will be build with SpringBoot, Spring MVC, Spring Security, Hibernate with MySQL and Thymeleaf with HTML5, CSS3 and Bootstrap4.

In addition I added a REST API documented with Swagger.

To get it running just download the project use the database script and modify the `application.properties` so it fits your database setup.
Then simply run the SpringBoot application with the command `mvn spring-boot:run` (Maven needed) and open the URL `localhost:8080/web/home` and log in with the credentials `Username: test Password: test123`. Thats it.
To open the swagger ui open the URL `localhost:8080/swagger-ui.html`.

Future TODO:
* Update the Thymeleaf forms to use objects.
* Add more Document Types.

&copy; mschoeffel
