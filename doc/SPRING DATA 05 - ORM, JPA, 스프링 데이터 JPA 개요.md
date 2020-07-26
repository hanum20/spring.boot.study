# SPRING DATA 05: ORM, JPA, 스프링 데이터 JPA 개요

* ORM(Object-Relational Mapping)과 JPA (Java Persistence API)

  * 객체와 릴레이션을 매핑할 때 발생하는 개념적 불일치를 해결하는 프레임워크
    * 테이블은 테이블과 컬럼만 존재한다.
    * 테이블은 크기가 한정되어있다.
    * 테이블은 상속이 없다.
  * http://hibernate.org/orm/what-is-an-orm
  * JPA: ORM을 위한 자바 (EE) 표준

* 스프링 데이터 JPA

  * Repository 빈 자동 생성
  * 쿼리 메소드 자동 구현
  * @EnableJpaRepositories (스프링 부트가 자동으로 설정 해줌)
  * 자바의 JPA 표준이 Hibernate에 맞추어져있음
    * Hibernate 개발자가 자바 JPA 표준을 만듬
  * JPA 표준 스펙을 아주 쉽게 사용하게끔 Spring Data로 추상화를 시켜둔 것이다.
  * SDJ(Spring Data Jpa) -> JPA -> Hibernate -> Datasource

* 스프링 데이터 JPA 의존성 추가

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  ```