# SPRING BOOT 02 - 자동 설정 이해

> @EnableAutoConfiguration

* 빈은 두 단계로 만들어진다.
  * ComponentScan에 의해 만들어지는 빈
  * EnableAutoConfiguration이 읽어들어서 만드는 빈
* `@ComponentScan`
  * `@Component`
  * `@Configuration`, `@Repository`, `@Service`, `@Controller`, `@RestController`
  * AutoConfiguration은 제외하고 읽어들인다.

* `@EnableAutoConfiguration`
  *  spring.factories
    * org.springframework.boot.autoconfigure.EnableAutoConfigu
      ration
  *  `@Configuration`
  *  `@ConditionalOnXxxYyyZzz`
    * Classpath에 해당 클래스가 있을 경우

`