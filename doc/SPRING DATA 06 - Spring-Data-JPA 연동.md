# SPRING DATA 06 - Spring-Data-JPA 연동

* 원래는 `@EnableJpaRepositories`에 대한 설정을 해주어야 한다.
  * 하지만, 스프링 부트에서는 자동으로 설정을 해준다.

* 스프링 데이터 JPA 의존성 추가

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  ```

* 테스트시, Embedded Database가 필요하다.

  ```
  Caused by: java.lang.IllegalStateException: Failed to replace DataSource with an embedded 
  ```

  ```xml
  # 의존성 추가
  <dependency>
      <groupId>com.h2database</groupId>
      <artifactId>h2</artifactId>
      <scope>test</scope>
  </dependency>
  ```

* DB 설정

  * 의존성 추가

    ```xml
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```

  * DBMS 추가(도커)

    ```bash
    # container 실행
    docker run -p 5432:5432 --name postgres_boot -e POSTGRES_DB=springboot -e POSTGRES_USER=hanum -e POSTGRES_PASSWORD=pass -d postgres
    
    # container 접속
    docker exec -i -t postgres_boot bash
    
    # db 접속
    su - postgres
    psql -U hanum springboot
    ```

  * application.properties 설정

    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/springboot
    spring.datasource.username=hanum
    spring.datasource.password=pass
    ```

  * 경고

    ```
    org.postgresql.jdbc.PgConnection.createClob() is not yet implemented
    ```

    ```
    # application.properties 설정
    spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
    ```

  * `@SpringBootTest`같은 통합테스트는 속도가 느리기 때문에 추천하지 않음...

    * `@SpringBootApplication`를 찾아서 모든 빈을 등록해버림... 당연히 속도.. 성능 느려진다.

* **추가로 필요한 메소드 만들기**

  ```java
  public interface AccountRepository extends JpaRepository<Account, Long> {
      Account findByUsername(String username);
  }
  ```

  * 키워드로 메소드 내용을 자동적으로 만들 수 있다.

* **native 쿼리 작성 방법**

  ```java
  public interface AccountRepository extends JpaRepository<Account, Long> {
  
      @Query(nativeQuery = true, value = "select * from account where username = '{0}'")
      Account findByUsername(String username);
  }
  ```

* **Optional**을 리턴 받을 수 있다.

  ```java
  public interface AccountRepository extends JpaRepository<Account, Long> {
      
      Optional<Account> findByUsername(String username);
  }
  ```

  * null일 경우, Optional.empty를 리턴한다.

* **테스트**

  ```java
  @RunWith(SpringRunner.class)
  @DataJpaTest // Repository 관련된 bean만 가져와서 테스트(슬라이싱 테스트)
  // @SpringBootTest는 통합테스트이다. @SpringBoot
  public class AccountRepositoryTest {
  
      @Autowired
      DataSource dataSource;
  
      @Autowired
      JdbcTemplate jdbcTemplate;
  
      @Autowired
      AccountRepository accountRepository;
  
      @Test
      public void di() throws Exception {
          try(Connection connection = dataSource.getConnection()){
              DatabaseMetaData metaData = connection.getMetaData();
              System.out.println(metaData.getURL());
              System.out.println(metaData.getDriverName());
              System.out.println(metaData.getUserName());
          }
      }
  
      @Test
      public void account() throws Exception {
          Account account = new Account();
          account.setPassword("pass");
          account.setUsername("hanum");
  
          Account newAccount = accountRepository.save(account);
  
          assertThat(newAccount).isNotNull();
  
          Account existingAccount = accountRepository.findByUsername(newAccount.getUsername());
          assertThat(existingAccount).isNotNull();
  
          // null
          Account nonExistionAccount = accountRepository.findByUsername("hhhh");
          assertThat(nonExistionAccount).isNotNull();
  
      }
  }
  ```