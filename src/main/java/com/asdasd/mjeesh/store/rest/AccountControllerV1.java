package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.entity_dto.AccountDto;
import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.filter_dto.AccountFilter;
import com.asdasd.mjeesh.store.mapper.AccountFactory;
import com.asdasd.mjeesh.store.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('account:save')")
    public AccountDto save(@RequestBody Account account) {
        return accountFactory.map(accountService.save(account));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('account:read')")
    public AccountDto findById(@PathVariable("id") Long id) {
        Account account = accountService.findById(id).orElse(new Account());
        return accountFactory.map(account);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('account:read')")
    public List<AccountDto> findAll() {
        List<Account> accounts = accountService.findAll();
        return accountFactory.map(accounts);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('account:read')")
    public List<AccountDto> findAllByFilter(@RequestParam(value = "PAGE", defaultValue = "0") Integer pageNo,
                                            @RequestBody AccountFilter filter) {
        List<Account> accounts = accountService.findAllByFilter(filter, pageNo);
        return accountFactory.map(accounts);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('account:delete')")
    public void delete(@PathVariable("id") Long id) {
        accountService.delete(id);
    }
}
