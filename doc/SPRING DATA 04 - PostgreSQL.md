# SPRING DATA 04: PostgreSQL

### PostgreSQL

* 의존성추가

  ```xml
  <dependency>
      <groupId>org.postgresql</groupId>
      <artifactId>postgresql</artifactId>
  </dependency>
  ```

* docker run

  ```bash
  # container 실행
  docker run -p 5432:5432 --name postgres_boot -e POSTGRES_DB=springboot -e POSTGRES_USER=hanum -e POSTGRES_PASSWORD=pass -d postgres
  
  # container 접속
  docker exec -i -t postgres_boot bash
  
  # db 접속
  su - postgres
  psql -U hanum springboot
  
  # 전체 데이터베이스 조회
  \l
  \list
  
  # Table 조회
  \dt
  
  # 조회
  springboot=# select * from account;
  ```

* postgresql에서는 USER가 키워드이다.

* 예제

  ```java
  @Component
  public class PgSQLRunner implements ApplicationRunner {
  
      @Autowired
      DataSource dataSource;
  
      @Autowired
      JdbcTemplate jdbcTemplate;
  
      @Override
      public void run(ApplicationArguments args) throws Exception {
          try(Connection connection = dataSource.getConnection()){
              /**
               * Connection 정보를 얻는다.
               */
              System.out.println(dataSource.getClass());
              System.out.println(connection.getMetaData().getURL());
              System.out.println(connection.getMetaData().getUserName());
  
              Statement statement = connection.createStatement();
              String sql = "CREATE TABLE account(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY(id))";
              statement.executeUpdate(sql);
          }
  
          jdbcTemplate.execute("INSERT INTO account VALUES(1, 'hanum')");
      }
  }
  ```

  

* 실행 결과

  ```
  class com.zaxxer.hikari.HikariDataSource
  jdbc:postgresql://localhost:5432/springboot
  hanum
  ```

  