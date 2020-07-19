# SPRING BOOT 14 - 프로파일

@Profile 애노테이션은

* @Configuration
* @Component

과 같이 사용된다.



특정 프로파일 활성화 하기

* Base

  ```java
  @Profile("prod")
  @Configuration
  public class BaseConfig {
      @Bean
      public String hello() {
          return "hello";
      }
  }
  ```

* Test

  ```java
  @Profile("test")
  @Configuration
  public class TestConfig {
      @Bean
      public String hello() {
          return "testhello";
      }
  }
  ```

* application.properties

  ```
  hanum.name=hanum
  hanum.age=18
  hanum.fullName=${hanum.name} chae
  spring.profiles.active=prod
  ```

  * `spring.profiles.active` 속성으로 활성화할 profile을 설정할 수 있다.



프로파일 용 프로퍼티

```
src/main/java/resource
	+ application.properties
	+ application-prod.properties
	+ application-test.properties
```

* 위와 같이 프로퍼티 파일을 프로파일로 구분하여 사용할 수 있다.
* 특정 프로파일을 사용하기 위해서는 역시 `spring.profiles.active`를 사용한다.



프로파일 추가하기

```
src/main/java/resource
	+ application.properties
	+ application-prod.properties
	+ application-test.properties
	+ application-proddb.properties
```



```
hanum.name=hanum prod
spring.profiles.include=proddb
```

`spring.profiles.include`를 이용하여 해당 프로퍼티에 추가할 프로퍼티를 명시할 수 있다. 

