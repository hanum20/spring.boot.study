package me.hanum.springdata09.account;

import org.springframework.data.repository.CrudRepository;

// CrudRepository는 스프링 데이터의 기본적인 최상위에 가까운 Repository 인터페이스 중 하나이다.
public interface AccountRepository extends CrudRepository<Account, String> {
}
