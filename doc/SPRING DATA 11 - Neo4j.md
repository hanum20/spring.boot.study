# SPRING DATA 11 - Neo4j

- 노드간의 연관 관계를 영속화하는데 유리한 그래프 데이터베이스

- 의존성 추가

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-neo4j</artifactId>
  </dependency>
  ```

  * 최신 버전에서는 이전버전의 Neo4jTemplate 같은 것들을 지원하지 않는다.
  * 세션 펙토리만 지원된다.
  * 버전간 호환이 별로 좋지 않다.

- DB 구성 (Docker)

  ```bash
  # Container 실행
  docker run -p 7474:7474 -p 7687:7687 -d --name neo4j_boot neo4j
  ```

  * 7474는 neo4j의 자체 브라우저이다.
  * 기본 계정은 `neo4j/neo4j`이다.
    * 로그인 시, 반드시 비밀번호를 바꾸게 되어있다.

- `application.properties` 설정

  ```properties
  spring.data.neo4j.password=1111
  spring.data.neo4j.username=neo4j
  ```

- NodeEntity

  - Account

    ```java
    @NodeEntity
    public class Account {
    
        @Id @GeneratedValue
        private Long id;
    
        private String username;
    
        private String email;
    
        @Relationship(type = "has")
        private Set<Role> roles = new HashSet<>();
    
        public Set<Role> getRoles() {
            return roles;
        }
    
        public void setRoles(Set<Role> roles) {
            this.roles = roles;
        }
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
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

  - Role

    ```java
    @NodeEntity
    public class Role {
    
        @Id @GeneratedValue
        private Long id;
    
        private String name;
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    }
    
    ```

- SessionFactory 예제

  ```java
  @Component
  public class Neo4jRunner implements ApplicationRunner {
  
      @Autowired
      SessionFactory sessionFactory;
  
      @Override
      public void run(ApplicationArguments args) throws Exception {
  
          Account account = new Account();
          account.setEmail("hanum@mail.com");
          account.setUsername("hanum");
  
          Role role = new Role();
          role.setName("admin");
  
          account.getRoles().add(role);
  
          Session session = sessionFactory.openSession();
          session.save(account);
          sessionFactory.close();
      }
  }
  ```

- Repository 에제

  - Repository

    ```java
    public interface AccountRepository extends Neo4jRepository<Account, Long> {
    }
    ```

  - 사용 예제

    ```java
    @Component
    public class Neo4jRunner implements ApplicationRunner {
    
        @Autowired
        AccountRepository accountRepository;
    
        @Override
        public void run(ApplicationArguments args) throws Exception {
    
            Account account = new Account();
            account.setEmail("hanum@mail.com");
            account.setUsername("hanum");
    
            Role role = new Role();
            role.setName("admin");
    
            account.getRoles().add(role);
    
            accountRepository.save(account);
        }
    }
    ```

    