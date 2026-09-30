# Java Servlet Practical

## Experiments
* **Experiment 1**: Generic Servlet
* **Experiment 2**: HTTP Servlet

## Requirements
* JDK
* Apache Tomcat 9
* VS Code
* Servlet API

## Java Version
Detected Java Version: **Java 27** (build 27+35-2325)

## Tomcat Version
Detected Tomcat Version: **Apache Tomcat 11.0.26** was found in `C:\apache-tomcat-11.0.26`, however, **Tomcat 9 is required** for this practical because it relies on the `javax.servlet` API, whereas Tomcat 11 uses the `jakarta.servlet` API. 
*Note: A Tomcat 9 installation was not found in the current environment.*

## Project Structure
```text
ServletPractical/
│
├── README.md
│
├── GenericServletApp/
│   ├── WEB-INF/
│   │   ├── web.xml
│   │   └── classes/
│   │       └── MyGenericServlet.class
│   │
│   └── index.html
│
├── HttpServletApp/
│   ├── WEB-INF/
│   │   ├── web.xml
│   │   └── classes/
│   │       └── MyHttpServlet.class
│   │
│   └── index.html
│
└── src/
    ├── MyGenericServlet.java
    └── MyHttpServlet.java
```

## Generic Servlet

* **What GenericServlet is:** `GenericServlet` is an abstract class provided by the Servlet API (`javax.servlet.GenericServlet`) that implements the `Servlet` interface. It is protocol-independent, meaning it can handle any protocol, not just HTTP.
* **What `service()` does:** The `service()` method is the core lifecycle method of a servlet. The web container (Tomcat) calls this method to handle requests from the client and to write the response back.
* **URL mapping:** Mapped to `/generic` in `web.xml`.
* **Expected output:** 
  ```text
  Welcome to Generic Servlet Example!
  ```

## HTTP Servlet

* **What HttpServlet is:** `HttpServlet` is an abstract class that extends `GenericServlet` (`javax.servlet.http.HttpServlet`) and is specifically designed to handle HTTP protocols. 
* **What `doGet()` does:** The `doGet()` method is called by the `service()` method of `HttpServlet` when an HTTP GET request is received. It is used to handle typical browser requests.
* **Difference between `GenericServlet` and `HttpServlet`:** `GenericServlet` is protocol-independent, whereas `HttpServlet` is tied specifically to the HTTP protocol. `HttpServlet` provides HTTP-specific methods like `doGet()`, `doPost()`, `doPut()`, etc., while `GenericServlet` only provides a single `service()` method.
* **URL mapping:** Mapped to `/http` in `web.xml`.
* **Expected output:** 
  ```text
  Welcome to HTTP Servlet Example!
  This is a response from doGet() method.
  ```

## Compilation

Since Tomcat 9 is missing, you must install it first (e.g. to `C:\apache-tomcat-9.0.x`).
Once installed, run these exact commands in PowerShell from the project root:

```powershell
# Compile Generic Servlet
javac -classpath "C:\apache-tomcat-9.0.x\lib\servlet-api.jar" -d "GenericServletApp\WEB-INF\classes" src\MyGenericServlet.java

# Compile HTTP Servlet
javac -classpath "C:\apache-tomcat-9.0.x\lib\servlet-api.jar" -d "HttpServletApp\WEB-INF\classes" src\MyHttpServlet.java
```

*(Note: Replace `C:\apache-tomcat-9.0.x` with your actual Tomcat 9 installation path)*

## Deployment

Copy the `GenericServletApp` and `HttpServletApp` folders directly into the Tomcat `webapps` directory:

```text
C:\apache-tomcat-9.0.x\webapps\GenericServletApp
C:\apache-tomcat-9.0.x\webapps\HttpServletApp
```

## Running Tomcat

On Windows, open PowerShell and run:
```powershell
cd "C:\apache-tomcat-9.0.x\bin"
.\startup.bat
```
If `startup.bat` closes too quickly and you cannot see errors, you can run Tomcat in the foreground using:
```powershell
cd "C:\apache-tomcat-9.0.x\bin"
.\catalina.bat run
```

## Testing

* **Generic Servlet:** [http://localhost:8080/GenericServletApp/generic](http://localhost:8080/GenericServletApp/generic)
* **HTTP Servlet:** [http://localhost:8080/HttpServletApp/http](http://localhost:8080/HttpServletApp/http)

## Common Errors

* **ClassNotFoundException**: This happens if you compiled the Java file, but did not place the `.class` file inside `WEB-INF/classes`. **Fix**: Ensure `MyGenericServlet.class` and `MyHttpServlet.class` are strictly inside their respective `WEB-INF/classes` folders.
* **404 Not Found**: This means the URL path is incorrect, or Tomcat failed to load your app. **Fix**: Double-check that your folder names match the URL (`/GenericServletApp/` and `/HttpServletApp/`), check the `<url-pattern>` in `web.xml`, and check Tomcat logs for startup errors.
* **500 Internal Server Error**: Your Java code threw an exception during runtime. **Fix**: Check the Tomcat logs (in the terminal or `logs/catalina.out`) for the stack trace and fix the Java code.
* **javax.servlet cannot be resolved**: This happens during compilation if the `servlet-api.jar` is missing or the classpath is incorrect. **Fix**: Ensure you pass the correct `-classpath` pointing exactly to `<TOMCAT_HOME>\lib\servlet-api.jar`. It also happens if you try using Tomcat 10+ since it uses `jakarta.servlet`.
* **Port 8080 already in use**: Another service (or another Tomcat instance) is already using port 8080. **Fix**: Open Task Manager, find the `java.exe` process holding the port, and end it. Alternatively, change the port in `<TOMCAT_HOME>\conf\server.xml`.
* **Tomcat fails to start**: Incorrect JDK configuration or malformed `web.xml`. **Fix**: Run `catalina.bat run` to see the exact error message in the console. Ensure your `web.xml` has proper XML formatting.
