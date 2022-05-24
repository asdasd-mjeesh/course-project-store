package com.asdasd.mjeesh.store.repository.account;

import com.asdasd.mjeesh.store.entity.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Override
    Account save(Account account);

    @Override
    Optional<Account> findById(Long id);

    Optional<Account> findByEmail(String email);

    @Override
    List<Account> findAll();

    @Override
    Page<Account> findAll(Pageable paging);

    @Override
    void deleteById(Long aLong);
}
