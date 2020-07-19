# SPRING BOOT 12 - 외부 설정(2)



* properties의 자동완성은 메타데이터를 기반으로 제공된다. 

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-configuration-processor</artifactId>
      <optional>true</optional>
  </dependency>
  ```

  위 플러그인은 메타데이터를 제공하는 역할을 한다.

* application.properties

  ```
  hanum.name=hanum
  hanum.age=18
  hanum.fullName=${hanum} chae
  ```

* 위 프로퍼티들을 받아올 클래스를 생성한다.

  ```java
  @Component
  @ConfigurationProperties("hanum")
  public class HanumProperties {
  
      private String name;
      private int age;
      private String fullName;
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public int getAge() {
          return age;
      }
  
      public void setAge(int age) {
          this.age = age;
      }
  
      public String getFullName() {
          return fullName;
      }
  
      public void setFullName(String fullName) {
          this.fullName = fullName;
      }
  }
  ```

  이 클래스가 자동으로 프로퍼티를 받아오기 위해서는 빈으로 등록될 필요가 있다.

* 원래는 `@EnableConfigurationProperties(HanumProperties.class)`와 같은 형식으로 `@ConfigurationProperties`를 붙여준 클래스를 명시해주어야 한다.

  * 스프링 부트에는 이러한 것들이 자동으로 등록되어있다.

* 따라서, 위 클래스에 `@Component`만 추가하면 된다.

* 제대로 받아오는지 테스트하는 코드를 작성한다.

  ```java
  @Component
  public class SampleRunner implements ApplicationRunner {
  
      @Autowired
      HanumProperties hanumProperties;
  
      @Override
      public void run(ApplicationArguments args) throws Exception {
          System.out.println("=============");
          System.out.println(hanumProperties.getFullName());
          System.out.println("=============");
      }
  
  
  }
  ```

* 결과

  ```
  2020-07-12 11:08:12.229  INFO 38496 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
  2020-07-12 11:08:12.374  INFO 38496 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
  2020-07-12 11:08:12.381  INFO 38496 --- [           main] me.hanum.propertyconfig.Application      : Started Application in 2.288 seconds (JVM running for 2.77)
  =============
  hanum chae
  =============
  ```

  

