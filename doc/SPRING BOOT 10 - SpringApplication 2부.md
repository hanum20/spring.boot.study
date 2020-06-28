# SPRING BOOT 10 - SpringApplication 2부

* ApplicationEvent 등록

  * `ApplicationContext` 생성 후에 발생하는 Event가 bean일 경우에는 Listener는 호출될 수 있다.

  * 하지만,  `ApplicationContext` 가 생성 전에 발생하는 Event는 @Bean으로 등록할 수 없다.

    * 따라서 직접 설정해줄 필요가 있다.

    * ```
      @SpringBootApplication
      public class Application {
      
      	public static void main(String[] args) {
      
      		//SpringApplication.run(Application.class, args);
      		SpringApplication app = new SpringApplication(SpringinitApplication.class);
      		app.addListeners(new SampleListener());
      		app.run();
      	}
      
      }
      ```

      * `SpringinitApplication.class`를 classpath에 찾을 수 없다. 원인 파악 필요...

    * `ApplicationStartedEvent`는 bean으로 등록할 수 있다.

* WebApplicationType 설정

  * `app.setWebApplicationType(WebApplicationType.<???>)`

    * `WebApplicationType.NONE`

    * `WebApplicationType.SERVLET`

      * spring web mvc가 있으면 기본적으로

    * `WebApplicationType.REACTIVE`

      * servlet이 없고 reactive가 있으면, `web flux`가 있으면 기본적으로 동작한다.

    * SERVLET과 WEB FLUX가 있을 경우 무조건 SERVLET이 우선으로 설정됨

      * WEB FLUX를 하고싶을 경우는 별도로 설정이 필요함.

      * ```
        app.setWebApplicationType(WebApplicationType.REACTIVE)
        ```

* Application 아규먼트 사용하기

  * VM options

    * `-Dfoo`
      * `-D`로 시작

  * Program arguments

    * `--bar`
      * `--`로 시작

  * `bean`으로 등록하여 사용할 수 있다.

    ```java
    @Component
    public class ArgumentComponent {
        public ArgumentComponent(ApplicationArguments arguments){
            System.out.println("foo: " + arguments.containsOption("foo"));
            System.out.println("bar: " + arguments.containsOption("bar"));
        }
    }
    ```

    * `foo`는 false 결과를 리턴...
      * `foo`는 JVM 옵션이다.
    * `bar`는 true를 리턴
      * `bar`는 application 옵션이다.

  * console에서 수행 시, option 설정 방법

    ```
    # packaging
    mvn clean target -DskipTests
    
    # java -jar <app name> -Dfoo --bar
    ```

* 애플리케이션 실행 후, 무언가를 추가로 실행하고 싶을 때.

  * 스프링에서는 두 가지를 소개하고 있다.

  * `ApplicationRunner`(추천)

    ```java
    @Component
    public class SampleRunner implements ApplicationRunner {
        @Override
        public void run(ApplicationArguments args) throws Exception {
            System.out.println("foo: " + args.containsOption("foo"));
            System.out.println("bar: " + args.containsOption("bar"));
        }
    }
    ```

    * JVM option을 받지 못함.
    * `@Order(1)`으로 `ApplicationRunner`순서를 정할 수도 있다.

  * `CommandLineRunner`

    ```java
    @Component
    public class SampleCommand implements CommandLineRunner {
        @Override
        public void run(String... args) throws Exception {
            Arrays.stream(args).forEach(System.out::println);
        }
    }
    ```

    * JVM option을 받지 못함.