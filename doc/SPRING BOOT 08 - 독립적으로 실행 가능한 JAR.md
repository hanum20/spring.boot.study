# SPRING BOOT 08 - 독립적으로 실행 가능한 JAR

* `mvn package`를 수행하면 JAR 파일이 생성.
  * clean은 target directory 내부를 지움
  * `-DskipTests` 로 test phase를 생략 가능
* spring-maven-plugin이 패키징을 수행한다.
* `java -jar <app name>`으로 jar 실행
  * `unzip -q <app name>`으로 jar 압축 해제 가능
  * `BOOT-INF`라는 Directory가 있음
    * 여기에 `lib`, `classes`가 포함되어있음.
* `org.springframework.boot.loader.jar.JarFile.class`를 사용해서 내장 JAR를 읽어들인다.
* `org.springframework.boot.loader.Launcher`로 실행한다.
* 독립적으로 실행 가능한 Application을 만들 수 있게 된다.