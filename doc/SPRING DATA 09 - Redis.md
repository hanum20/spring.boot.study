# SPRING DATA 09 - Redis

* 의존성 추가

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
  </dependency>
  ```

* DB 구성 (Docker)

  ```bash
  # redis container 시작
  docker run -p 6379:6379 --name redis_boot -d redis
  
  # redis 접속
  docker exec -i -t redis_boot redis-cli
  
  keys *
  ```

* 스프링 데이터 redis

  * StringRedisTemplate
    * 좀더 String에 특화되어있는 Template
  * RedisTemplate
  * extends CrudRepository

* 기본 설정으로 db를 구성했기 때문에 별다른 설정 없이도 동작할 수 있다.

  * 다른 설정을 사용할 경우 `application.properties`에서 설정한다.

* 예제

  * Runner

    ```java
    @Component
        public class RedisRunner  implements ApplicationRunner {
    
            @Autowired
            StringRedisTemplate redisTemplate;
    
            @Autowired
            AccountRepository accountRepository;
    
            @Override
            public void run(ApplicationArguments args) throws Exception {
                ValueOperations<String, String> values = redisTemplate.opsForValue();
                values.set("name", "hanum");
                values.set("springboot", "2.2");
                values.set("hello", "world");
    
                Account account = new Account();
                account.setUsername("hanum");
                account.setPassword("pass");
    
                accountRepository.save(account);
                Optional<Account> byId = accountRepository.findById(account.getId());
                System.out.println(byId.get().getUsername());
                System.out.println(byId.get().getPassword());
    
            }
    }
    
    ```

  * RedisHash

    ```java
    @RedisHash("account")
    public class Account {
    
        @Id
        private String id;
    
        private String username;
    
        private String password;
    
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
    
        public String getPassword() {
            return password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }
    }
    ```

  * Repository

    ```java
    // CrudRepository는 스프링 데이터의 기본적인 최상위에 가까운 Repository 인터페이스 중 하나이다.
    public interface AccountRepository extends CrudRepository<Account, String> {
    }
    ```

    

* Redis 조회

  ```
  # field 조회
  get hello
  
  # hash의 field 조회
  hget account:318e4d05-8cf8-4475-b948-91d77b9f8317 username
  
  # hash의 모든 field 조회
  hgetall account:318e4d05-8cf8-4475-b948-91d77b9f8317
  ```

  