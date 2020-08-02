# SPRING BOOT ACTUATOR 01 - 소개

* <https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready-endpoints>

* 의존성 추가

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>
  ```

* 애플리케이션의 각종 정보를 확인할 수 있는 Endpoints

  * 다양한 Endpoints 제공

  * JMX 또는 HTTP를 통해 접근 가능

  * shutdown을 제외한 모든 Endpoint는 기본적으로 활성화 상태

  * 활성화 옵션 조정

    * `application.properties`

      ```properties
      management.endpoints.enabled-by-default=false
      management.endpoint.info.enabled=true
      ```

* 예제

  * `http://localhost:8080/actuator`과 같이 접속하면, 몇 가지 정보를 확인할 수 있게된다.

    ```
    {"_links":{"self":{"href":"http://localhost:8080/actuator","templated":false},"health-path":{"href":"http://localhost:8080/actuator/health/{*path}","templated":true},"health":{"href":"http://localhost:8080/actuator/health","templated":false},"info":{"href":"http://localhost:8080/actuator/info","templated":false}}}
    ```

    * HTTP 방식으로 확인하는 예제이다.

* 활성화는 되어있는데 왜 확인을 할 수 없는지?

  * 활성화는 되어있지만 **공개**는 되어있지 않기 때문이다.
    * expose, enable