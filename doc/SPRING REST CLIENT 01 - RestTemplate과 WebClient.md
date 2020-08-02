# SPRING REST CLIENT 01 - RestTemplate과 WebClient

* RestTemplate

  * Blocking I/O 기반의 Synchronous API

  * RestTemplateAutoConfiguration

  * 프로젝트에 spring-web 모듈이 있다면 RestTemplateBuilder를 빈으로 등록해준다.

  * 예제

    * 의존성 추가

      ```xml
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
      ```

    * Controller

      ```java
      @RestController
      public class HelloController {
      
          @GetMapping("/hello")
          public String hello() throws InterruptedException {
              Thread.sleep(5000l);
              return "hello";
          }
      
          @GetMapping("/world")
          public String world() throws InterruptedException {
              Thread.sleep(3000l);
              return "world";
          }
      }
      ```

    * Runner

      ```java
      @Component
      public class RestRunner implements ApplicationRunner {
      
          @Autowired
          RestTemplateBuilder restTemplateBuilder;
      
          @Override
          public void run(ApplicationArguments args) throws Exception {
              RestTemplate restTemplate = restTemplateBuilder.build();
      
              StopWatch stopWatch = new StopWatch();
              stopWatch.start();
      
              String helloResult = restTemplate.getForObject("http://localhost:8080/hello", String.class);
              System.out.println(helloResult);
      
              String worldResult = restTemplate.getForObject("http://localhost:8080/world", String.class);
              System.out.println(worldResult);
      
              stopWatch.stop();
              System.out.println(stopWatch.prettyPrint());
      
      
          }
      }
      ```

    * 결과

      ```java
      hello
      world
      StopWatch '': running time = 8160477100 ns
      ---------------------------------------------
      ns         %     Task name
      ---------------------------------------------
      8160477100  100%  
      ```

      

* WebClient

  * Non-Blocking I/O 기반의 Asynchronous API

  * WebClientAutoConfiguration

  * 프로젝트에 spring-webflux 모듈이 있다면 WebClient.Builder를 빈으로 등록해준다.

  * 예제

    * 의존성 추가

      ```xml
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-webflux</artifactId>
      </dependency>
      ```

    * Controller

      ```java
      @RestController
      public class HelloController {
      
          @GetMapping("/hello")
          public String hello() throws InterruptedException {
              Thread.sleep(5000l);
              return "hello";
          }
      
          @GetMapping("/world")
          public String world() throws InterruptedException {
              Thread.sleep(3000l);
              return "world";
          }
      }
      ```

    * Runner

      ```java
      @Component
      public class RestRunner implements ApplicationRunner {
      
          @Autowired
          WebClient.Builder builder;
      
          @Override
          public void run(ApplicationArguments args) throws Exception {
              WebClient webClient = builder.build();
      
              StopWatch stopWatch = new StopWatch();
              stopWatch.start();
      
              Mono<String> helloMono = webClient.get().uri("http://localhost:8080/hello")
                      .retrieve()
                      .bodyToMono(String.class);
      
              helloMono.subscribe(s -> {
                  System.out.println(s);
      
                  if (stopWatch.isRunning()) {
                      stopWatch.stop();
                  }
      
                  System.out.println(stopWatch.prettyPrint());
                  stopWatch.start();
              });
      
              Mono<String> worldMono = webClient.get().uri("http://localhost:8080/world")
                      .retrieve()
                      .bodyToMono(String.class);
      
              worldMono.subscribe(s -> {
                  System.out.println(s);
      
                  if (stopWatch.isRunning()) {
                      stopWatch.stop();
                  }
      
                  System.out.println(stopWatch.prettyPrint());
                  stopWatch.start();
              });
          }
      }
      ```

    * 결과

      ```java
      world
      StopWatch '': running time = 3942363600 ns
      ---------------------------------------------
      ns         %     Task name
      ---------------------------------------------
      3942363600  100%  
      
      hello
      StopWatch '': running time = 5892549000 ns
      ---------------------------------------------
      ns         %     Task name
      ---------------------------------------------
      3942363600  067%  
      1950185400  033%  
      ```

      