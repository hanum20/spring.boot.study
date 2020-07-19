# SPRING WEB MVC 11 - CORS

* SOP와 CORS

  * Single-Origin Policy
    * 기본
    * Origin이 다르면 호출하지 못한다.
  * Cross-Origin Resource Sharing
    * SOP를 해결하기 위한 방법
  * Origin?
    * URI 스키마 (http, https)
    * hostname (hanum.me, localhost)
    * 포트 (8080, 18080)
    * 이 세가지를 종합한 것이 하나의 오리진이다.

* 스프링 부트는 CORS 설정을 자동으로 해준다.

* 테스트

  * Client1(localhost:8080)

    ```java
    @SpringBootApplication
    @RestController
    public class SpringCorsApplication {
    
    	@GetMapping("/hello")
    	public String hello() {
    		return "hello";
    	}
    
    	public static void main(String[] args) {
    		SpringApplication.run(SpringCorsApplication.class, args);
    	}
    
    }
    ```

  * Client2(localhost:18080)

    ```html
    <body>
    <script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
        <script>
            $(function() {
                $.ajax("http://localhost:8080/hello")
                    .done(function(msg) {
                    	alert(msg);
                	})
                    .fail(function() {
                    	alert("fail");
                	});
                
            });
        </script>
    </body>
    ```

  * SOP 정책에 의해 client2의 코드는 실패하게 된다. 이 문제를 해결하려면 서버로부터 `Access-Control-Allow-Origin`가 있어야 한다.
    **애노테이션**을 @Controller나 @RequestMapping에 추가하거나

    ```java
    @SpringBootApplication
    @RestController
    public class SpringCorsApplication {
    
    	@CrossOrigin(origins = "http://localhost:18080")
    	@GetMapping("/hello")
    	public String hello() {
    		return "hello";
    	}
    
    	public static void main(String[] args) {
    		SpringApplication.run(SpringCorsApplication.class, args);
    	}
    
    }
    ```

    **WebMvcConfigurer** 사용해서 글로벌 설정

    ```java
    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:18080");
            
        }
    }
    ```

    

