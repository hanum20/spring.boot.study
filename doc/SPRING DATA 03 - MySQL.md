# SPRING DATA 03: MySQL(and DBCP)

> 스프링 부트가 지원하는 dbcp 기능과 mysql을 살펴본다.

* dbcp

  * database connection pool
  * 데이터베이스 커넥션을 만드는 과정 자체가 많은 과정을 거친다.
  * 따라서 커넥션을 미리 만들어두고 만들어둔 커넥션을 가져다 쓰는 것이 리소스 낭비를 줄일 수 있다.
  * dbcp에서는 이러한 커넥션의 수를 설정하는 것부터 여러 설정을 할 수 있다.
  * 디비 성능에 있어서 매우 큰 영향을 끼친다.
  * 문제가 되면 어플리케이션의 성능에 큰 영향을 끼친다.
    * 따라서 신중히 공부하고 사용해야 한다.

* 스프링부트는 HikariCP라는 dbcp를 선택했다.

  * ` https://github.com/brettwooldridge/HikariCP#frequently-used`
  * autoCommit
    * default: false
    * true일 경우 commit을 안해도 자동으로 commit한다.
  * connectionTimeout
    * default: 30000(30 sec)
    * dpcp pool에서 connection 객체를 어플리케이션에 어느 시간동안 못보내야 오류 처리를 할 것인지
  * maximumPollSize
    * 유지할 수 있는 커넥션의 최대 수
    * 무조건 크다고 좋은 것이 아니다.
    * 한 번에 최대 실행할 수 있는 커넥션은 cpu의 코어 수와 같다.

* application.properties에서 dbcp 설정이 가능하다.

  * `HikariCP`의 `HikariConfig`에 설정이 되어있다.

    ```
    spring.datasource.hikari.maxumum-pool-size=4
    ```

    

### MySQL

* 일정 버전 이상부터는 SSL을 사용하는 것을 거의 강제하고있음

  ```
  spring.datasource.url=jdbc:mysql://localhost:3306/springboot?useSSL=false
  ```

  

* 의존성 추가

  ```xml
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
  </dependency>
  ```

* docker mysql 실행

  ```bash
  # container 실행
  docker run -p 3306:3306 --name mysql_boot -e MYSQL_ROOT_PASSWORD=1 -e MYSQL_DATABASE=springboot -e MYSQL_USER=hanum -e MYSQL_PASSWORD=pass -d mysql
  
  # container 접속
  docker exec -i -t mysql_boot bash
  
  # db 접속
  mysql -u hanum -p
  
  # db 변경
  mysql> use springboot
  
  # 조회
  mysql> select * from USER;
  
  ```

* mysql은 라이센스 문제에 의해 소스코드를 공개해야하면서 비용을 지불해야 한다.

* 따라서 이럴때는 **mariadb**를 사용하는 것을 추천

  * 하지만 여전히 소스코드 공개 의무는 발생할 수 있음

* application.properties 설정

  ```
  spring.datasource.hikari.maxumum-pool-size=4
  
  spring.datasource.url=jdbc:postgresql://localhost:3306/springboot
  spring.datasource.username=hanum
  spring.datasource.password=pass
  ```

* 예제

  ```java
  @Component
  public class MySQLRunner implements ApplicationRunner {
  
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
              String sql = "CREATE TABLE USER(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY(id))";
              statement.executeUpdate(sql);
          }
  
          jdbcTemplate.execute("INSERT INTO USER VALUES(1, 'hanum')");
      }
  }
  ```

  