package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.dto.AccountDto;
import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.mapper.AccountFactory;
import com.asdasd.mjeesh.store.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountControllerV1 {
    private final AccountService accountService;
    private final AccountFactory accountFactory;

    @Autowired
    public AccountControllerV1(AccountService accountService, AccountFactory accountFactory) {
        this.accountService = accountService;
        this.accountFactory = accountFactory;
    }

    @PostMapping("")
    public AccountDto save(@RequestBody Account account) {
        return accountFactory.map(accountService.save(account));
    }

    @GetMapping("/{id}")
    public AccountDto findById(@PathVariable("id") Long id) throws Exception {
        Account account = accountService.findById(id).orElseThrow(Exception::new);
        return accountFactory.map(account);
    }

    @GetMapping("/pageNo/{pageNo}")
    public List<AccountDto> findAll(@PathVariable("pageNo") Integer pageNo) {
        List<Account> accounts = accountService.findAll(pageNo);
        return accountFactory.map(accounts);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        accountService.delete(id);
    }
}
