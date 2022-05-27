package com.asdasd.mjeesh.store.repository.account;

import com.asdasd.mjeesh.store.entity.account.Account;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>,
        QuerydslPredicateExecutor<Account> {

    @Override
    Account save(Account account);

    @Override
    Optional<Account> findById(Long id);

    Optional<Account> findByEmail(String email);

    @Override
    List<Account> findAll();

    @Override
    Page<Account> findAll(Predicate predicate, Pageable paging);

    @Override
    void deleteById(Long aLong);
}
