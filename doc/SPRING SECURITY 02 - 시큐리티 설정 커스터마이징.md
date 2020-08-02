# SPRING SECURITY 02 - 시큐리티 설정 커스터마이징

* 의존성 추가

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  ```

  * 의존성만 추가한 상태에서는, 모든 요청에 대해 인증을 요구하게된다.

* 시큐리티 설정 커스터마이즈

  ```java
  package me.hanum.springsecurity02;
  
  import org.springframework.context.annotation.Configuration;
  import org.springframework.security.config.annotation.web.builders.HttpSecurity;
  import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
  
  @Configuration
  public class SecurityConfig extends WebSecurityConfigurerAdapter {
      @Override
      protected void configure(HttpSecurity http) throws Exception {
          http.authorizeRequests()
                  .antMatchers("/","/hello").permitAll()
                  .anyRequest().authenticated()
                  .and()
              .formLogin()
                  .and()
              .httpBasic();
      }
  }
  ```

  * `antMatchers("/","/hello").permitAll()` : 다음의 urlPattens에만, 인증없이 요청을 통과시킨다.
  * `anyRequest().authenticated()` : 이외의 모든 요청에 대해 인증이 필요하다.
  * `formLogin()` : 인증 방식으로는 form 로그인 방식과
  * `httpBasic()` : httpBasic 방식을 사용할 것이다.

* UserDetailsService 설정 커스터마이즈

  * Security는 Password 인코더를 반드시 필요로 한다.

  * 의존성 추가

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
    </dependency>
    ```

  * configure

    ```java
    @Configuration
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/","/hello").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .and()
                .httpBasic();
        }
    
        @Bean
        public PasswordEncoder passwordEncoder(){
            return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }
    }
    ```

  * Service

    ```java
    /**
     * 반드시 UserDetailsService 타입의 빈이 등록되어야 한다.
     */
    @Service
    public class AccountService implements UserDetailsService {
    
        @Autowired
        private AccountRepository accountRepository;
    
        @Autowired
        private PasswordEncoder passwordEncoder;
    
        public Account createAccount(String username, String password) {
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(passwordEncoder.encode(password));
            return accountRepository.save(account);
        }
    
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<Account> byUsername = accountRepository.findByUsername(username);
            Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));
    
            return new User(account.getUsername(), account.getPassword(), authorites());
        }
    
        private Collection<? extends GrantedAuthority> authorites() {
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }
    ```

    

  * JpaRepository

    * Entity

      ```java
      @Entity
      public class Account {
      
          @Id
          @GeneratedValue
          private Long id;
      
          private String username;
      
          private String password;
      
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
      public interface AccountRepository extends JpaRepository<Account, Long> {
          Optional<Account> findByUsername(String username);
      }
      ```

  * 