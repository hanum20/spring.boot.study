# SPRING DATA 10 - MongoDB

* 특징

  * 스키마가 없음
  * 클러스터 만들기도 편함

* 의존성 추가

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-mongodb</artifactId>
  </dependency>
  ```

* DB 구성(Docker)

  ```bash
  # container 추가
  docker run -p 27017:27017 --name mongo_boot -d mongo
  
  # container 접속
  docker exec -i -t mongo_boot bash
  
  # db 접속
  mongo
  
  # db 조회
  > db
  test
  
  # db 사용
  > use test
  switched to db test
  
  # collection 조회
  > db.accounts.find({})
  ```

  * 기본 설정을 사용할 경우, spring boot에서 별다른 설정을 할 필요 없음

* Collection

  ```java
  @Document(collection = "accounts")
  public class Account {
  
      @Id
      private String id;
  
      private String username;
  
      private String email;
  
      public String getId() {
          return id;
      }
  
      public void setId(String id) {
          this.id = id;
      }
  
      public String getUsername() {
          return username;
      }
  
      public void setUsername(String username) {
          this.username = username;
      }
  
      public String getEmail() {
          return email;
      }
  
      public void setEmail(String email) {
          this.email = email;
      }
  }
  ```

  

* MongoTemplate 사용 예제

  ```java
  @SpringBootApplication
  public class Springdata10Application {
  
  	@Autowired
  	MongoTemplate mongoTemplate;
  
  	public static void main(String[] args) {
  		SpringApplication.run(Springdata10Application.class, args);
  	}
  
  	@Bean
  	public ApplicationRunner applicationRunner() {
  		return args -> {
  			Account account = new Account();
  			account.setEmail("hanum@example.com");
  			account.setUsername("hanum");
  
  			mongoTemplate.insert(account);
  
  			
  		};
  	}
  }
  ```

* Repository 사용 예제

  * Repository

    ```java
    public interface AccountRepository extends MongoRepository<Account, String> {
    }
    ```

  * 사용 예제

    ```java
    @SpringBootApplication
    public class Springdata10Application {
    
    	@Autowired
    	AccountRepository accountRepository;
    
    	public static void main(String[] args) {
    		SpringApplication.run(Springdata10Application.class, args);
    	}
    
    	@Bean
    	public ApplicationRunner applicationRunner() {
    		return args -> {
    			Account account = new Account();
    			account.setEmail("hanumrepo@example.com");
    			account.setUsername("hanum111");
    
    			accountRepository.insert(account);
    
    
    		};
    	}
    }
    ```

* Mongo Test

  * Spring boot는 내장형 Mongo db를 지원한다.

  * 의존성 추가 

    ```xml
    <dependency>
        <groupId>de.flapdoodle.embed</groupId>
        <artifactId>de.flapdoodle.embed.mongo</artifactId>
    </dependency>
    ```

    * 이제 슬라이싱 테스트를 작성할 수 있다.

  * 테스트 코드

    ```java
    @RunWith(SpringRunner.class)
    @DataMongoTest
    public class AccountRepositoryTest {
        @Autowired
        AccountRepository accountRepository;
    
        @Test
        public void findByEmail() {
            Account account = new Account();
            account.setUsername("hanum");
            account.setEmail("susu@mail.com");
    
            accountRepository.save(account);
    
            Optional<Account> byId =  accountRepository.findById(account.getId());
            assertThat(byId).isNotEmpty();
    
            Optional<Account> byEmail = accountRepository.findByEmail(account.getEmail());
            assertThat(byEmail).isNotEmpty();
        }
    }
    ```

    * 내장형 MongoDB를 사용하기 때문에, 운영용 Mongo DB에 영향을 주지 않는다.