# SPRING BOOT 18 - DevTools



의존성을 아래와 같이 추가한다.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```



* 의존성을 추가하는 순간 프로젝트의 설정이 변경된다.
  * 캐시 설정을 개발 환경에 맞게 변경
    * 캐시 때문에 브라우저를 강력한 새로고침을 하는 불편함을 줄여준다.
  * 클래스패스에 있는 파일이 변경 될 때마다 자동으로 재시작.
    * 직접 껐다 켜는거 (cold starts)보다 빠른다. 왜?
    *  릴로딩 보다는 느리다. (JRebel 같은건 아님)
    *  리스타트 하고 싶지 않은 리소스는? spring.devtools.restart.exclude
    * 리스타트 기능 끄려면? spring.devtools.restart.enabled = false
  * 라이브 릴로드? 리스타트 했을 때 브라우저 자동 리프레시 하는 기능
    * 브라우저 플러그인 설치해야 함.
    *  라이브 릴로드 서버 끄려면? spring.devtools.liveload.enabled = false
  * 글로벌 설정
    * ` ~/.spring-boot-devtools.properties`
    * application.properties보다 우선순위가 높음
      * 물론 `spring-boot-devtools`가 의존성에 포함되어있을 경우에 한함.