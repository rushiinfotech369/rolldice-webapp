# RollDice WebApp (Java 21 + Maven + Tomcat)

This is a simple web application version of the RollDice demo that you can deploy to Apache Tomcat (WAR).

## Build
```
mvn clean package
```

## Deploy to Tomcat
Copy the generated WAR file from `target/` into your Tomcat `webapps/` directory:
```
cp target/rolldice-webapp-0.1.0.war /path/to/tomcat/webapps/
```
Tomcat will automatically extract and deploy the WAR. Then open:
```
http://<tomcat-host>:<port>/rolldice-webapp/roll
```

## Notes
- This project compiles with **Java 21**.
- The servlet uses the `javax.servlet` API (Servlet 4.0). If you deploy to **Tomcat 10+ (Jakarta)**, replace imports with `jakarta.servlet.*` or use Tomcat 9.
