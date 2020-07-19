# SPRING WEB MVC 04 - 정적 리소스 지원

> 스프링 부트가 기본적으로 제공하는 정적 리소스 지원에 대해 알아본다.

* 정적 리소스 매핑 `/**`

  * classpath:/static

  * classpath:/public

  * classpath:/resources/

  * classpath:/META-INF/resources

  * spring.mvc.static-path-pattern: 맵핑 설정 변경 가능

    * application.properties에서 설정

      ```
      spring.mvc.static-path-pattern=/static/**
      ```

    * `localhost:8080/hello.html` => `localhost:8080/static/hello.html`

  * spring.mvc.static-locations: 리소스 찾을 위치 변경 가능

    * 사용할 경우 기본적인 리소스 위치를 사용하지 않게 된다.

* Last-Modified 헤더를 보고 304 응답 보냄

* ResourceHttpRequestHandler가 처리

  * Last-Modified 헤더를 보고 304 응답 보냄

  * WebMvcConfigurer의 addResourceHandlers로 커스터마이징 가능

    ```java
    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/m/**") // 다음과 같은 요청이 오면
                .addResourceLocations("classpath:/m/") // 다음과 같은 디렉토리에서 리소스를 주겠다.
                .setCachePeriod(20); // 초 단위
        }
    }
    ```

    * 스프링 부트의 기본 설정을 유지하면서, 본인이 원하는 설정을 추가 가능
    * **주의할 점** : 반드시 `/`로 끝나야 한다.
    * 추천 방법

