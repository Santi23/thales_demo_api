
# Thales Demo API (English Version)

## Introduction

Thales Demo API is a project developed (for job interview purpose) with **Spring Boot** in 2.X version, Java 8 (1.8) and available to deploy on **WildFly**. This demo show how works a REST API implementation using Spring Boot and application to be configured when deployed as a WAR file (.war). Internally, it consumes an external public API using **Feign** and **RestTemplate**, this strange situation was defined for the purpose of showing and testing different ways of accessing to HTTP request.

## Technologies Used

- **Java 8**
- **Spring Boot 2.7.18** ([Documentation](https://docs.spring.io/spring-boot/docs/2.7.18/reference/html))
- **WildFly 23.0.2.Final** ([Documentation](https://docs.wildfly.org/23/Admin_Guide.html))
- **Maven 3.9.9** ([Documentation](https://maven.apache.org/guides/))
- **Feign** ([Documentation](https://cloud.spring.io/spring-cloud-openfeign/reference/html/))
- **RestTemplate** ([Documentation](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html))
- **Mockito 4.5.1** ([Documentation]())
- **Lombok 1.18.32** ([Documentation]())

## Prerequisites

- **Java 8 JDK** installed ([Download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html))
- **Maven 3.8+** ([Download](https://maven.apache.org/download.cgi)) **It is required if the IDE doesnt have one installed.**
- **Docker (latest version)** configured and running 
- **WildFly 23.0.2.Final** configured and running 

## Installation and Setup

### Clone the repository

```bash
git clone https://github.com/Santi23/thales_demo_api.git
cd thales_demo_api
```

### Build the project

```bash
mvn clean package
```

### Deploy on WildFly

1. Copy the generated WAR file to the WildFly deployment folder:

```bash
cp target/thales-1.1.war /opt/jboss/wildfly/standalone/deployments/
```

2. Start WildFly:

```bash
./standalone.sh
```
If you use Docker follow next steps: 
- Pull image or create DockerFile and build it.
- Run a new container with the previous image created and defined ports to use (e.g: 8083 to deployments and 9991 to admin)

```bash
docker run -p 8080:8080 -p 9990:9990 --name wildfly-server -d app-thales
```

3. Access the API at:

```
http://localhost:8080/thales-1.1/
```

## Best Practices and Suggested Improvements

1. **Security:** Implement authentication and authorization with **Spring Security**.
2. **Configuration:** Use **Spring Cloud Config** to manage external configurations.
3. **Logging:** Complete integration with **Logback** with custom configurations.
4. **Error Handling:** Create a **Global Exception Handler**.
5. **Documentation:** Include **Swagger** for API documentation.
6. **Health Check:** Add **Spring Boot Actuator**.
7. **Database:** Configure **Liquibase** for database versioning.
8. **Deployment:** Automate with **Docker** and **Kubernetes**.
9. **Entidad de base de datos:** Implemente **JPA** e **Hibernate** para administrar entidades de la base de datos.
10. **CI/CD:** Integrate with **GitHub Actions** or **Jenkins**.
11. **Dependency Management:** Avoid outdated versions and use **Spring Dependency Management**.
12. **Optimize Feign:** Enable **circuit breakers** with **Resilience4J**.
13. **Header Security:** Configure **CORS and CSP**.
14. **Code Analysis:** Use **SonarQube**.
15. **Monitoring:** Implement **Dynatrace, Prometheus or Grafana**.

---
## Example Angular Frontend App that consumes this application as a API

[See frontend project here](https://github.com/Santi23/app-thales-employees-frontend/commits/master/)
---

## Contact

For any questions or suggestions, feel free to create an issue in this repository.

---

Thanks for reading.🚀

-----------------------------------------
