# SPRING WEB MVC 03 - ViewResolve

* **ContentNegotiationViewResolver**

  * 요청이 들어오면, 요청의 응답을 만들 수 있는 모든 뷰를 찾아낸다.
  * 최종적으로  accept header을 분석하여 적합한 뷰를 선택한다.
  * 종종 accept header를 제공하지 않는 요청도 있어서 `/path?format=pdf`를 사용할 수 있다.

* accept header에 따라 응답 방식이 달라진다.

* 테스트

  ```java
  @Test
  public void createUser_XML() throws Exception {
      String userJson = "{\"username\":\"hanum\"}";
      mockMVC.perform(post("/users/create")
      	.contentType(MediaType.APPLICATION_JSON_UTF8)
      	.accept(MediaType.APPLICATION_XML)
      	.content(userJson))
          	.andExpect(status().isOk())
      		.andExpect(xpath("/User/username").string("hanum"));
  }
  ```

* 406 Not Acceptable

  * 이러한 요청을 처리할 컨버터가 없는 것...

    * 여기에서는 XML로 컨버팅 해줄 컨버터가 없다.
    * 컨버터를 추가하면 됨

  * 종속성 추가

    ```xml
    <dependency>
    	<groupId>com.fasterxml.jackson.dataformat</groupId>
        <artifactId>jackson-dataformat-xml</artifactId>
        <version>2.9.6</version>
    </dependency>
    ```

    * classpath에 xml 컨버터를 추가하면, 자동으로 구성된다.
    * 따라서 이후에는 알아서 xml 컨버팅을 진행하게된다.