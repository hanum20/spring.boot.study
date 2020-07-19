# SPRING WEB MVC 10 - Spring HATEOAS

* Hypermedia As The Engine Of Application State
  * GitHub api에서 사용하고 있음
  * 서버: 현재 리소스와 연관된 링크 정보를 클라이언트에게 제공한다.
  * 클라이언트: 연관된 링크 정보를 바탕으로 리소스에 접근한다.
  * 연관된 링크 정보
    * Relation
    * Hypertext Reference
* ObjectMapper 제공
  * web만 들어있어도 자동적으로 사용 가능
  * spring.jackson.*
  * Jackson2ObjectMapperBuilder
  * 빈으로 등록되어있음
* 일정 버전에서는 `Resource`가 사라진 듯
* LinkDiscovers 제공
  * 클라이언트 쪽에서 링크 정보를 Rel 이름으로 찾을때 사용할 수 있는 XPath 확장 클래스