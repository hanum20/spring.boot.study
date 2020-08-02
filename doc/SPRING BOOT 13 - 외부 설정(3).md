# SPRING BOOT 13 - 외부 설정(3)

타입-세이프 프로퍼티 

* @ConfigurationProperties여러 프로퍼티를 묶어서 읽어올 수 있음
* 빈으로 등록해서 다른 빈에 주입할 수 있음
  *  @EnableConfigurationProperties
  * **@Component**
    * 이 부분이 자주 쓰일 것임
  *  @Bean
    * 흔하지 않은 케이스
* 융통성 있는 바인딩
  * 아래의 4 가지 같은 경우에도 바인딩을 해준다.
  *  context-path (케밥)
  * context_path (언드스코어)
  * contextPath (캐멀)
  * CONTEXTPATH
* 프로퍼티 타입 컨버전
  *  @DurationUnit
* 프로퍼티 값 검증
  *  @Validated
  *  JSR-303 (@NotNull, ...)
    * @Validated가 JSR-303의 구현체임
      * 하이버네이트를 임포트해야 사용 가능
    * @Size
    * @NotEmpty
* 메타 정보 생성



* @Value
  * 아주 정확하게 사용해야 함.
  *  SpEL 을 사용할 수 있지만...
  * **위에 있는 기능들은 전부 사용 못합니다**