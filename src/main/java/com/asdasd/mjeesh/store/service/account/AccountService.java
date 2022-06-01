package com.asdasd.mjeesh.store.service.account;

import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.filter_dto.AccountFilter;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account saveOrUpdate(Account account);

    Optional<Account> findById(Long id);

    List<Account> findAll();

    List<Account> findAllByFilter(AccountFilter filter, Integer pageNo);

    void delete(Long id);
}
