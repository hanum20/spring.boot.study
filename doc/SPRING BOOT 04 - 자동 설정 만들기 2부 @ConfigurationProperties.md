# SPRING BOOT 04 - 자동 설정 만들기 2부: @ConfigurationProperties

**빈 덮어씌우기 방지**

`@ConditionalOnMissingBean`

* 자동 설정 된 빈이 우선시되어 내가 다시 정의한 빈이 무시되는 경우가 있다.  이때, 이 애노테이션을 사용할 수 있다.
  * spring이 @Component를 먼저 읽어오고 나중에 AutoConfiguiration의 빈들을 읽어오기 때문에 발생한 문제.
  * spring boot 버전이 올라가면서 동작 방식이 달라졌다. 따라서 기존에는 빈이 오버라이딩 되었었다면, 이제는 Exception이 발생하게 된다.



**빈 재정의 수고 덜기**

* 불편하게 bean으로 재정의 하는 것이 아니라, properties를 이용해서 재정의 하는 방법을 사용한다.
* `@ConfigurationProperties("holoman")`
* `@EnableConfigurationProperties(HolomanProperties.class)`
* 프로퍼티 키값 자동 완성



**예제**

**자동 설정 프로젝트**

`HolomanProperties.java`

```java
@ConfigurationProperties("holoman")
public class HolomanProperties {
    private String name;
    private int howlong;

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
}
```

* holoman은 application.properties에서 참조할 이름이 된다.



`HolomanConfiguration.java`

```java
@Configuration
@EnableConfigurationProperties(HolomanProperties.class)
public class HolomanConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Holoman holoman(HolomanProperties properties) {
        Holoman holoman = new Holoman();
        holoman.setHowlong(properties.getHowlong());
        holoman.setName(properties.getName());
        return holoman;
    }
}
```

* `@EnableConfigurationProperties(HolomanProperties.class)`을 추가
  * properties의 값을 주입받아서 자동 설정을 진행한다.



**A Project**

`application.properties`

```
holoman.name=hanum
holoman.howlong=7
```

* 위와 같이 빈을 설정할 수 있게 된다.