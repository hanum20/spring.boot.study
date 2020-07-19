# SPRING WEB MVC 05 - webjar

> 부트스트랩이나 앵귤러js 같은 것들을 jar로 참조할 수 있다.

* 왜 사용하는지?

  * 오프라인 환경에서 개발을 세팅할 때

* 어떻게 추가하나?

  1. MVN Repository에서 웹 jar 검색
  2. pom.xml에 의존성을 추가한다.
  3. js의 위치를 HTML에서 가져온다.

* 예시

  * html

    ```html
    <script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
    <script>
        $(function() {
            alert("ready!");
        });
    </script>
    ```

  * pom.xml

    ```xml
    <dependency>
    	<groupId>org.webjars.bower</groupId>
        <artifactId>jquery</artifactId>
        <version>3.3.1</version>
    </dependency>
    
    <!--이 의존성은 jquery webjar의 경로에서 버전을 입력하지 않아도 되게 한다.-->
    <dependency>
    	<groupId>org.webjars</groupId>
        <artifactId>webjars-locator-core</artifactId>
        <version>0.35</version>
    </dependency>
    ```

    * 리소스 체이닝이 이것을 가능하게 한다.

    * before

      ```html
      <script src="/webjars/jquery/3.3.1/dist/jquery.min.js"></script>
      ```

    * after

      ```html
      <script src="/webjars/jquery/dist/jquery.min.js"></script>
      ```

      