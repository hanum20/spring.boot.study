# SPRING BOOT 07 - 내장 웹 서버 응용 2부 : HTTPS와 HTTP2

### HTTPS

> 공인된 인증서에 대한 공개키(public key)는 브라우저가 기본적으로 가지고있다.

> 기존의 HTTP 커넥터에 HTTPS를 적용하는 것이기 때문에, 적용 후에는 HTTP로 접속을 할 수 없다.

1. Keystore 생성

   ```
   keytool -genkey 
     -alias tomcat 
     -storetype PKCS12 
     -keyalg RSA 
     -keysize 2048 
     -keystore keystore.p12 
     -validity 4000
   ```

2. 생성한 keystore 정보를 `application.properties`에 설정

   ```
   server.ssl.key-store: keystore.p12
   server.ssl.key-store-password: 123456
   server.ssl.keyStoreType: PKCS12
   server.ssl.keyAlias: tomcat
   ```

3. 커넥터 추가는 다음과 같이 할 수 있다.

   ```java
   @SpringBootApplication
   @RestController
   public class Application {
   
   	@GetMapping("/hello")
   	public String hello(){
   		return "Hello spring";
   	}
   
   	public static void main(String[] args) {
   		SpringApplication.run(Application.class, args);
   	}
   
   	@Bean
   	public ServletWebServerFactory serverFactory(){
   		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(());
   		tomcat.addAdditionalTomcatConnectors(createStandardConnect());
   	}
   
   	private Connector createStandardConnect() {
   		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocal");
   		connector.setPort(8080);
   
   		return connector;
   	}
   
   }
   ```

   



### HTTP2

1. 프로퍼티에 다음과 같이 설정한다.

   ```
   server.http2.enabled=true
   ```

2. 제약 사항이 서버마다 다르다.

   * Undertow

     * https가 적용되어있으면, 추가로 설정할 필요가 없다.

   * Tomcat

     * 정말 복잡하다.

     * 8.5.x에서 HTTP2를 사용하려면 복잡해진다.

     * 9.0.x와 JDK9가 있으면 추가 설정 없이 사용할 수 있다.

       ```
       <properties>
       ...
       <java.version>9</java.version>
       <tomcat.version>9.0.10</tomcat.version>
       </properties>
       ```

       * 인텔리제이 프로젝트 구성 변경
         * 프로젝트 JDK 9로 설정
         * Module도 9로 설정



