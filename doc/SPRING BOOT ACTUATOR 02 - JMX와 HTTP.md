# SPRING BOOT ACTUATOR 02 - JMX와 HTTP

* JMX
  * JConsole
    *  https://docs.oracle.com/javase/tutorial/jmx/mbeans/
    *  https://docs.oracle.com/javase/7/docs/technotes/guides/management/jconsole.html
    * Java application에 대한 모니터링을 도와준다.
      * 메모리
      * 스래드
      * 로딩된 클레스
      * 요약
  * VisualVM

* HTTP

  * 공개 옵션 조정

    ```properties
    # 모든 정보 공개
    management.endpoints.web.exposure.include=*
    
    #
    management.endpoints.web.exposure.exclude=env,beans
    ```

    * 이렇게 공개할 경우, 보안에 대해 문제가 있기 때문에 스프링 시큐리티를 이용하여 접근에 대해 제한을 주어야 한다.