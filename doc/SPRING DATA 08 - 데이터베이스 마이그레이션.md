# SPRING DATA 08 - 데이터베이스 마이그레이션

* Flyway

  * DB 스키마도 버전 관리하듯이 sql 파일로 관리할 수 있다.

  * 기본적으로 sql 파일을 사용

  * 의존성 추가

    ```xml
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-core</artifactId>
    </dependency>
    ```

  * docker로 postgres db 준비

  * `resource` 밑에 `db/migration` 생성

    * `V1__init.sql`  생성
      * naming rule 잘 지켜주어야 함.
      * syntax도 잘 지켜주어야 함. (`;`  특히 주의) 

  * 마이그레이션 파일 이름

    * V숫자_이름.sql
    * V는 꼭 대문자로
    * 숫자는 순차적으로 (타임스탬프 권장)
    * 숫자와 이름 사이에 언더바 두 개.
    * 이름은 가능한 서술적으로.

  * 실행해보면, flyway table(`flyway_schema_history`)이 생성된 것을 볼 수 있다.

    * sql 파일에 대한 history를 관리한다.

  * **주의할 점**

    * 한 번 적용된, sql 파일은 절대로 수정하면 안된다.
    * 새로 만들어야 한다.