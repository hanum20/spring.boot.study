# SPRING BOOT 03 - 자동 설정 만들기 1부 : Starter와 AutoConfigure

* Xxx-Spring-Boot-Autoconfigure 모듈: 자동 설정
  * 보통 자동 설정은 위 프로젝트에다 넣는 것이 일반적
* Xxx-Spring-Boot-Starter 모듈: 필요한 의존성 정의
  * pom.xml이 핵심
*  그냥 하나로 만들고 싶을 때는?
*  Xxx-Spring-Boot-Starter



**예제**

**Holoman project**

> 자동 설정 프로젝트

**`me.hanum/holoman.java`**

```java
package me.hanum;

public class Holoman {
    String name;

    int howlong;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHowlong() {
        return howlong;
    }

    public void setHowlong(int howlong) {
        this.howlong = howlong;
    }

    @Override
    public String toString() {
        return "Holoman{" +
                "name='" + name + '\'' +
                ", howlong=" + howlong +
                '}';
    }
}
```



**`me.hanum/holomanConfiguration.java`**

```java
package me.hanum;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HolomanConfiguration {

    @Bean
    public Holoman holoman() {
        Holoman holoman = new Holoman();
        holoman.setHowlong(5);
        holoman.setName("hanum");
        return holoman;
    }
}
```



**`resources/META-INF/spring.factories`**

```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  me.hanum.HolomanConfiguration
```



**A Project**

> 자동 설정 프로젝트를 의존성에 추가하여 이용하는 프로젝트

**`pom.xml`**

```xml
<dependencies>
    ...
    <dependency>
        <groupId>me.hanum</groupId>
        <artifactId>hanum-spring-boot-starter</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```



**`HolomanRunner.java`**

```java
@Component
public class HolomanRunner implements ApplicationRunner {

    @Autowired
    Holoman holoman;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(holoman);
    }
}
```



**`Application.java`**

```java
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Application.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

}
```

