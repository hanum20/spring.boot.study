# SPRING DATA 02 : 인메모리 데이터베이스

* Spring-JDBC를 의존성에 추가하면 자동설정이 필요한 빈을 설정해준다.

  * DataSource
  * JdbcTemplate

* H2 데이터베이스를 의존성에 추가하고 별다른 설정을 하지 않으면,

  * 스프링 부트는 인메모리 데이터베이스로 설정을 한다.

* connection 객체에는 커넥션에 대한 정보가 있고 해당 정보로 접속할 디비에 대한 정보를 얻을 수 있다.

  * 사실`DataSourceProperties.class`에서 찾을 수 있다.
  * URL: jdbc:h2:mem:testdb
  * username: SA

* 인메모리 데이터베이스 기본 연결 정보를 확인하는 방법

  ```java
  @Component
  public class H2Runner implements ApplicationRunner {
  
      @Autowired
      DataSource dataSource;
  
      @Override
      public void run(ApplicationArguments args) throws Exception {
          try(Connection connection = dataSource.getConnection()){
              /**
               * Connection 정보를 얻는다.
               */
              System.out.println(connection.getMetaData().getURL());
              System.out.println(connection.getMetaData().getUserName());
  
              Statement statement = connection.createStatement();
              String sql = "CREATE TABLE USER(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY(id))";
              statement.executeUpdate(sql);
          }
      }
  }
  ```

* H2 콘솔을 사용하는 방법

  * spring-boot-devtools를 추가하거나...
  * spring.h2.console.enabled=true 만 추가
  * /h2-console로 접속