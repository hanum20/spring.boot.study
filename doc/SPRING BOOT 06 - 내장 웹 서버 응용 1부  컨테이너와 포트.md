# SPRING BOOT 06 - 내장 웹 서버 응용 1부 : 컨테이너와 포트

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <!-- Exclude the Tomcat dependency -->
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<!-- Use Jetty instead -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

* 기본적으로 포함되는 내장 톰켓을 제외시킨다.
* Jetty를 의존성으로 추가한다.



### Disabling Web Server

> 스프링은 기본적으로 웹 서버로 프로젝트를 구성하려고 한다. 이를 프로퍼티에서 설정할 수 있다.

```
spring.main.web-application-type=none
```



### HTTP Port 변경

> 프로퍼티에서 `server.port`로 설정할 수 있다.

```
# 다음과 같이 임의의 포트를 설정 할 수 있음
server.port=8080

# value를 0으로 주면, 무작위로 포트를 설정하게 된다.
server.port=0
```



### 실행중에 HTTP Port에 접근하는 추천 방법

```java
@Component
public class PortListener implements ApplicationListener<ServletWebServerInitializedEvent> {
    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
        ServletWebServerApplicationContext applicationContext = servletWebServerInitializedEvent.getApplicationContext();
        applicationContext.getWebServer().getPort();
        
    }
}
```

* Servlet Web Server가 생성이 되면, 이 이벤트 리스너가 호출이 된다.
* `ServletWebServerApplicationContext`이기 때문에 Web Server를 알 수  있다.
* Web Server를 통해 Port를 알 수 있다.