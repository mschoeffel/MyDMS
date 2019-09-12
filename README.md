# MyDMS

This is just a private project creating my own document management system, where you can upload files, add metadata to, categorize them and search for keywords or files in the whole system.\
The system will be build with SpringBoot, Spring MVC, Spring Security, Hibernate with MySQL and Thymeleaf with HTML5, CSS3 and Bootstrap4.

In addition I added a REST API documented with Swagger.

To get it running just download the project use the database script and modify the `application.properties` so it fits your database setup.
Then simply run the SpringBoot application with the command `mvn spring-boot:run` (Maven needed) and open the URL `localhost:8080/web/home` and log in with the credentials `Username: test Password: test123`. That's it.
To open the swagger ui open the URL `localhost:8080/swagger-ui.html`.

Note: If you want a demo database you can use the `mydbs_db_demo_setup.sql` to set up the database with some example data for the application. (the login there sill keeps the same)

Or, if you want to keep it really simple as demo just use the `h2_application.properties` and `h2_data.sql` and remove the "h2_" and replace the existing `application.properties`. With this new configuration the WebApp will use a H2 database instead of th MySQL and will already have some demo data.\
NOTE: H2 is not persistent so if you shut down or restart the WebApp all the data wil be resettet.

&copy; mschoeffel
