# ForoHub API

![Badge en Desarollo](https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green)

Es una API Rest desarrollada con Spring Boot. Permite la creación, lectura, actualización y eliminación
de temas (tópicos) por usuarios autenticados.

## Características

- Autenticación de usuarios
- Gestión de temas
- Persistencia en base de datos relacional

## Requisitos
- [Java JDK 17 o superior](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven 4 o superior]()
- [Spring 3.3.1](https://start.spring.io/)
- [MySql 8.0]()

## Dependencias
- [Spring Web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
- [Spring Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Spring Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
- [Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)
- [MySQL Connector](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)
- [Flyway Migration](https://mvnrepository.com/artifact/org.flywaydb/flyway-core)
- [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
- [DevTools (opcional)](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)

## Instalación

1. Clona este repositorio en tu máquina local:

    ```sh
    git clone https://github.com/BFeeer/ForoHub.git
    cd ForoHub
    ```
   
## Configuración   

1. Ejecuta la sentencia dentro del gestor de bases de datos
    ```sh
    CREATE DATABASE forohub;
    ```
3. Crea variables de entorno para configurar las credenciales de acceso a la base de datos
   ```sh
   DB_HOST = "localhost"
   DB_NAME = "forohub"
   DB_USER = "tu_usuario"
   DB_PASSWORD = "tu_contraseña"
    ```   

## Uso

1. Abre el proyecto en un entorno de desarrollo integrado (IDE)
2. Ejecuta la aplicación
3. Accede a la [documentacion - Swagger UI]() para explorar y testear los endpoints 
