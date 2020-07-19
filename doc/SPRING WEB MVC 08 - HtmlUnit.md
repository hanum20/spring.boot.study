# SPRING WEB MVC 08 - HtmlUnit

*  http://htmlunit.sourceforge.net/gettingStarted.html

* html을 단위테스트 하기 위해 사용

* 의존성 추가

  ```xml
  <dependency>
  			<groupId>org.seleniumhq.selenium</groupId>
  			<artifactId>htmlunit-driver</artifactId>
  			<scope>test</scope>
  		</dependency>
  		<dependency>
  			<groupId>net.sourceforge.htmlunit</groupId>
  			<artifactId>htmlunit</artifactId>
  			<scope>test</scope>
  		</dependency>
  ```

* Test

  ```java
  @Autowired
  WebClint webClient
  
  @Test
  public void hello() throws Exception {
  	HtmlPage page = webClient.getPage("/hello");
  	httpHeading1 h1 = page.getFirstByXPath("//h1");
  	assertThat(h1.getTextContent()).isEqualToIgnoringCase("hanum");
  
  }
  ```

  