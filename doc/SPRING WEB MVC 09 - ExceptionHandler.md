# SPRING WEB MVC 09 - ExceptionHandler

* 기본적인 에러 헨들러가 있다.

* `accept`가 `text/html`일 경우에는 html로 오류를 리턴하지만

  * 별다른 명시를 하지 않을 경우에는 json으로 리턴한다.

* MVC 예외 처리방법

  * @ControllerAdvice
  * @ExchangepHandler

* 스프링 부트가 제공하는 기본 예외 처리기

  * BasicErrorController
    * HTML과 JSON 응답 지원
  * 커스터마이징 방법
    * ErrorController 구현
    * `BasicErrorController`을 상속해서 커스터마이징 하는 것을 레퍼런스에서 추천

* 커스텀 에러 페이지

  * 상태 코드 값에 따라 에러 페이지 보여주기
    * src/main/resources/static|template/error/
    * 404.html
    * 5xx.html
    * ErrorViewResolver 구현
      * 좀더 동적으로 만들고싶으면 사용

  