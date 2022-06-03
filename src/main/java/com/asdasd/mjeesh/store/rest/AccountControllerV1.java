package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.entity_dto.AccountDto;
import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.filter_dto.AccountFilter;
import com.asdasd.mjeesh.store.mapper.AccountMapper;
import com.asdasd.mjeesh.store.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountControllerV1 {
    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final PasswordEncoder bcryptEncoder;

    @Autowired
    public AccountControllerV1(AccountService accountService, AccountMapper accountMapper, PasswordEncoder bcryptEncoder) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
        this.bcryptEncoder = bcryptEncoder;
    }

    @PostMapping("/new")
    public AccountDto saveOrUpdate(@RequestBody Account account) {
        account.setPassword(bcryptEncoder.encode(account.getPassword()));
        return accountMapper.map(accountService.saveOrUpdate(account));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('account:read')")
    public AccountDto findById(@PathVariable("id") Long id) {
        Account account = accountService.findById(id).orElse(new Account());
        return accountMapper.map(account);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('account:read')")
    public List<AccountDto> findAll() {
        List<Account> accounts = accountService.findAll();
        return accountMapper.map(accounts);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('account:read')")
    public List<AccountDto> findAllByFilter(@RequestParam(value = "PAGE", defaultValue = "0") Integer pageNo,
                                            @RequestBody AccountFilter filter) {
        List<Account> accounts = accountService.findAllByFilter(filter, pageNo);
        return accountMapper.map(accounts);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('account:delete')")
    public void delete(@PathVariable("id") Long id) {
        accountService.delete(id);
    }
}
