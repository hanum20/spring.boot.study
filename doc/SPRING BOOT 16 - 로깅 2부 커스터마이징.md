# SPRING BOOT 16 - 로깅 2부: 커스터마이징



* 로거 설정

  ```
  # application.properties
  spring.output.ansi.enabled=always
  logging.path=logs
  logging.level.org.springframework=debug
  ```

* 커스텀 로그 설정 파일 사용하기

  * logback: logback-spring.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <configuration>
    	<include resource="org/springframework/boot/logging/logback/base.xml"/>
    	<logger name="me.hanum" level="DEBUG"/>
    </configuration>
    ```

  * Log4J2: log4j2-spring.xml

    * pom.xml에 logback 제외 및 log4j2 추가

  

  * Logback extension
    * 프로파일 `<springProfile name="프로파일">`
    * Environment 프로퍼티 `<springProperty>`



