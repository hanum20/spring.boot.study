# SPRING BOOT ACTUATOR 03 - 스프링 부트 어드민

* 스프링 진영에서 제공하는 APP은 아니다. 제 3자가 제공.

* Actuator 정보를 제공

* 툴이자 어플리케이션

* 어드민 서버가 필요하다.

* 긱 프로젝트 설정

  * 어드민 서버 측

    * 의존성 추가

      ```xml
      <dependency>
          <groupId>de.codecentric</groupId>
          <artifactId>spring-boot-admin-starter-server</artifactId>
          <version>2.0.1</version>
      </dependency>
      ```

    * `@EnableAdminServer`

      ```java
      @SpringBootApplication
      @EnableAdminServer
      public class Application {
          ...
      }
      ```

  * 클라이언트 측

    * 의존성 추가

      ```xml
      <dependency>
          <groupId>de.codecentric</groupId>
          <artifactId>spring-boot-admin-starter-client</artifactId>
          <version>2.0.1</version>
      </dependency>
      ```

    * `application.properties`

      ```properties
      spring.boot.admin.client.url=http://localhost:8080
      management.endpoints.web.exposure.include=*
      ```

      