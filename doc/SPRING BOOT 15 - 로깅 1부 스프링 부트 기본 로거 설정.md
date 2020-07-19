# SPRING BOOT 15 - 로깅 1부: 스프링 부트 기본 로거 설정



* 로킹 퍼사드 vs 로거

  * Commons Logging, SLF4j
    * 퍼사드 로거...
    * 스프링 코어에서 사용하는 Commons Logging을 제외시키고 SLF4j를 포함하여 사용할 수 있다.
  * JUL, Log4J2, **Logback**



* 스프링 5에 로거 관련 변경 사항

  * Spring-JCL
    * Commons Logging -> SLF4j or Log4j2
    * pom.xml에 exclusion 안해도 됨.
  * Commons Logging, SLF4j 중 어떤 것을 사용하더라도 결국 Logback이 사용된다.
    * 참고로 Logback은 SLF4j의 구현체이다.



* 스프링 부트 로깅
  * 기본 포멧
  * --debug
    * 일부 핵심 라이브러리만 디버깅 모드로
  * --trace
    * 전부 다 디버깅 모드로
  * 컬러 출력: spring.output.ansi.enabled=always
  * 파일 출력: logging.file(file name) or logging.path(directory)
  * 로그 레벨 조정: logging.level.<패키지> = <로그 레벨>



