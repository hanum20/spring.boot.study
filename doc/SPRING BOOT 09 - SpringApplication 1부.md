# SPRING BOOT 09 - SpringApplication 1부

* Log level 설정
  * run configuration에서 현재 application의 vm 옵션에 `-Ddebug`을 추가하여, log level을 debug로 설정한다.

* `/resource/banner.txt`

  * 프로그램 실행 시, 출력될 banner을 설정한다.

  * `${application.version}` 같은 변수들을 입력하여 사용할 수 있다.

  * 다만, `manifast`가 생성되어야 호출할 수 있는 변수들도 있다.

  * gif, jpg, png도 사용 가능하다.

    * 각 확장자에 따라서 `application.properties`에서 추가할 수 있는 설정들도 다르다.

  * banner를 off 하고싶을 경우

    ```
    SpringApplication app = new SpringApplication(SpringinitApplication.class);
    app.setBannerMod(Banner.Mode.OFF);
    app.run(args);
    ```

  * `banner.txt` 같은 리소스가 java 내부에서 정의된 banner 내용보다 우선순위가 높다. 

* `SpringApplicationBuilder()`로 빌더 패턴 사용 가능

