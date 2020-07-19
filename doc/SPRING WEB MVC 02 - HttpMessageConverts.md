# SPRING WEB MVC 02 - HttpMessageConverts

* Spring mvc의 일부

* http 요청 본문을 객체로.. 또는 객체를 요청 본문으로 변환하는데 사용

* @ResponseBody(or @RequestBody)와 함께 사용

  ```java
  @PostMapping("/user")
  public @ResponseBody User create(@RequestBody User user) {
      reutrn null;
  }
  ```

* 기본적으로 컴포지션 타입일 경우에는 JsonConverter가 사용된다.

* 꼭 `@ResponseBody`또는 `@RestController`를 사용해야 한다. 그렇지 않으면 뷰 리졸버를 사용하게된다.

* 스프링 부트

  * 뷰 리졸버 설정 제공
  * HttpMessageConvertersAutoConfiguration

* 테스트

  ```java
  @Test
  public void createUser_JSON() throws Exception {
      String userJson = "{\"username\":\"hanum\"}";
      mockMVC.perform(post("/users/create")
      	.contentType(MediaType.APPLICATION_JSON_UTF8)
      	.accept(MediaType.APPLICATION_JSON_UTF8)
      	.content(userJson))
          	.andExpect(status().isOk())
      		.andExpect(jsonPath("$.username", is(equalTo("hanum"))));
  }
  ```

* 헨들러

  ```
  @PostMapping("/user/create")
  public @ResponseBody User create(@RequestBody User user){
      return user;
  }
  ```

* User class

  ```java
  public class User {
      private Long id;
      private String username;
      
      public Long getId() {
          return id;
      }
      public void setId(Long id) {
          this.id = id;
      }
       public String getUsername() {
          return id;
      }
      public void setUsername(String username) {
          this.username = username;
      }
  }
  ```

  