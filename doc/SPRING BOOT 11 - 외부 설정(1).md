# SPRING BOOT 11 - 외부 설정(1)

https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config

### 사용할 수 있는 외부 설정

* properties

* YAML

*  환경 변수

* 커 맨드 라인 아규먼트

  

### 프로퍼티 우선순위

1. 유저 홈 디렉토리에 있는 spring-boot-dev-tools.properties

2. 테스트에 있는 @TestPropertySource

   ```java
   @RunWith(SpringRunner.class)
   @TestPropertySource(properties = "name=hanum2)
   @SpringBootTest
   public class SampleRunnerTest {
   
       @Autowired
       Environment environment;
   
       @Test
       public void contextLoad(){
           assertThat(environment.getProperty("name"))
               .isEqualTo("hanum");
       }
   
   }
   ```

   

3. @SpringBootTest 애노테이션의 properties 애트리뷰트

   ```java
   @RunWith(SpringRunner.class)
   @SpringBootTest(properties = "name=hanum2")
   public class SampleRunnerTest {
   
       @Autowired
       Environment environment;
   
       @Test
       public void contextLoad(){
           assertThat(environment.getProperty("name"))
               .isEqualTo("hanum");
       }
   
   }
   ```

   

4. 커맨드 라인 아규먼트

5. SPRING_APPLICATION_JSON (환경 변수 또는 시스템 프로티) 에 들어있는
  프로퍼티

6. ServletConfig 파라미터

7. ServletContext 파라미터

8. java:comp/env JNDI 애트리뷰트

9. System.getProperties() 자바 시스템 프로퍼티

10. OS 환경 변수

11. RandomValuePropertySource

12. JAR 밖에 있는 특정 프로파일용 application properties

13. JAR 안에 있는 특정 프로파일용 application properties

14. JAR 밖에 있는 application properties

15. JAR 안에 있는 application properties

16. @PropertySource

17. 기본 프로퍼티 (SpringApplication.setDefaultProperties)
    application.properties 우선 순위 (높은게 낮은걸 덮어 씁니다.)

18. file:./config/

19. file:./

20. classpath:/config/

21. classpath:/
   랜덤값 설정하기
   ● ${random.*}
   플레이스 홀더
   ● name = keesun
   ● fullName = ${name} baik



===========

* 테스트용 프로퍼티 추가하기

  * 인텔리J
    * Project Structure -> Modules -> test/resources를 선택하여 `Test Resources`로 설정
  * 테스트용 프로퍼티와, 일반 프로퍼티를 같이 사용할 경우 주의할 점.
    * 두 프로퍼티와의 sync를 맞추어주지 않으면, 테스트가 깨질 수 있다.
      * 일반 프로퍼티에는 존재하는 key가 테스트용 프로퍼티에는 존재하지 않는 경우에 발생
    * 해결 방법
      * 두 프로퍼티의 sync를 맞추어준다.
      * 

* 프로퍼티에서 랜덤 값 사용하기

  ```
  # 렌덤 값 주기
  hanum.age = ${random.int}
  
  # 서버 포트에 랜덤 값을 주려면 0으로 설정할 것
  server.port=0
  ```

* 프로퍼티 재사용

  ```
  name=hanum
  fullname=${name} chae
  ```

* application.properties 우선 순위(높은 것이 먼저 사용된다.)

  * file:./config/
  * file:./
  * classpath:/config/
  * classpath:/