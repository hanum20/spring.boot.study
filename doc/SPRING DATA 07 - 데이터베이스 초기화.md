# SPRING DATA 07 - 데이터베이스 초기화

* DB 시작(Docker)

  ```bash
  # 기존의 postgres container가 실행중인지 확인
  docker ps -a
  
  # 멈춤 상태일 경우, 시작
  docker start postgres_boot
  
  # Container 접속
  docker exec -i -t postgres_boot bash
  
  # 사용자 변경
  root@892ee620c670:/# su - postgres
  
  # psql 접속
  postgres@892ee620c670:~$ psql -U hanum springboot
  
  # 테이블 확인
  springboot-# \dt
  ```

* jpa 설정(`application.properties`) 으로 데이터베이스 초기화

  ```properties
  # data 유지 안됨, 매번 새로 만듬
  spring.jpa.hibernate.ddl-auto=create
  
  # 기존의 스키마에서 수정하는 식으로 동작하게된다.
  spring.jpa.hibernate.ddl-auto=update
  
  # 운영시 사용헤애 힐 옵션. 검증을 수행한다.
  spring.jpa.hibernate.ddl-auto=validate
  ```

  ```properties
  # 위의 옵션을 사용할지 여부를 true로 설정해주어야 한다.
  # 기본적으로는 false로 설정되어있다.
  # 위 옵션(ddl-auto)을 validate로 사용할 경우 false로 설정해야함, 왜냐하면 dll에 어떠한 변경을 가할 것은 아니기 때문
  spring.jpa.generate-ddl=true
  ```

  ```properties
  # sql을 보여준다.
  spring.jpa.show-sql=true
  ```

* SQL 스크립트를 사용한 데이터베이스 초기화

  * `schema.sql` 또는 `schema-${platform}.sql`
  * `data.sql` 또는 `data-${platform}.sql`
  * `${platform}`은 `spring.datasource.platform` 으로 설정 가능
  * 이 설정을 이용하면, `ddl-auto=validate`로 설정되어있더라도, sql 파일을 참조하여 스키마를 생성한다.