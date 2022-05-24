package com.asdasd.mjeesh.store.service.account;

import com.asdasd.mjeesh.store.entity.account.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account save(Account account);

    Optional<Account> findById(Long id);

    List<Account> findAll(Integer pageNo);

    void delete(Long id);
}
