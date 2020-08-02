# SPRING SECURITY 01 - Starter-Security

* 특정 urlPath에 대해, view만 리턴한다면, 핸들러를 만들지 않고 아래와 같은 코드로 구현 가능하다.

  ```java
  @Configuration
  public class WebConfig implements WebMvcConfigurer {
  
      @Override
      public void addViewControllers(ViewControllerRegistry registry) {
          // hello에 대해 hello view를 리턴
          registry.addViewController("/hello").setViewName("hello");
      }
  }
  ```

* 스프링 시큐리티

  * 웹 시큐리티
  * 메소드 시큐리티
  * 다양한 인증 방법 지원
    * LDAP, 폼 인증, Basic 인증, OAuth

* 종속성 추가

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  ```

* 추가하고 나면, 모든 요청이 자동 설정에 의해 인증을 요구하게된다.

  * 요청이 원하는 `accept header`에 따라서 인증하는 방식이 달라진다.

    * 기본적으로는 Basic 인증을 요구하게된다.

  * 테스트 코드

    ```java
    @RunWith(SpringRunner.class)
    @WebMvcTest(HomeController.class)
    public class HomeControllerTest {
    
        @Autowired
        MockMvc mockMvc;
    
        // view가 제대로 리턴되는지 확인
        @Test
        public void hello() throws Exception {
            mockMvc.perform(get("/hello"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("hello"));
        }
    
        @Test
        public void my() throws Exception {
            mockMvc.perform(get("/my"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("my"));
        }
    
    }
    ```

  * 결과

    ```
    ockHttpServletRequest:
          HTTP Method = GET
          Request URI = /hello
           Parameters = {}
              Headers = []
                 Body = <no character encoding set>
        Session Attrs = {SPRING_SECURITY_SAVED_REQUEST=DefaultSavedRequest[http://localhost/hello]}
    ```

* 테스트 코드 변경

  ```java
   @Test
  public void hello() throws Exception {
      mockMvc.perform(get("/hello")
                      .accept(MediaType.TEXT_HTML))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(view().name("hello"));
  }
  ```

  * 위와 같이 accept header를 `TEXT_HTML`로 설정하게되면, form을 통해 인증을 받으려고 한다.

* 기본적으로 생성하는 인증 정보는 아래와 같다.

  ```
  2020-08-02 09:30:18.452  INFO 125920 --- [           main] .s.s.UserDetailsServiceAutoConfiguration : 
  
  Using generated security password: 3456ad28-847b-4efc-8906-d25eb01815dc
  ```

  * username: user
  * password: 매번 다름
    * 3456ad28-847b-4efc-8906-d25eb01815dc

* 스프링 부트 시큐리티 자동 설정

  * **SecurityAutoConfiguration**

    * 별다른 설정을 하지 않으면, 기본 설정을 따라간다.

      ```java
      @Configuration
      public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
          
      }
      ```

      * 위와 같이 설정하면 스프링 부트 시큐리티 자동 설정을 사용하지 않는다.
        * 거의 비슷하게 설정되어있다고 한다.

  * **UserDetailsServiceAutoConfiguration**

    * `UserDetailsService`가 빈으로 등록되어있지 않으면, 인메모리로 유저 정보를 관리한다.

* 일반적으로 프로젝트들은 본인들의 UserDetailsService를 등록하게끔 되어있다.

  * 따라서 스프링 부트가 제공해주는 시큐리티 자동 설정을 사용할 일이 거의 없다고 한다.

* 위에서 보이다시비, 시큐리티 의존성을 추가하면서 테스트가 깨져버리고 말았다. 이 부분은 다음과 같이 해결 가능하다.

  ```xml
  <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-test</artifactId>
      <scope>test</scope>
  </dependency>
  ```

  ```java
  @Test
  @WithMockUser // 이 애노테이션으로 인증 문제를 해결한다.
  public void hello() throws Exception {
      mockMvc.perform(get("/hello"))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(view().name("hello"));
  }
  ```

  

  