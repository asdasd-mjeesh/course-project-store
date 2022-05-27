package com.asdasd.mjeesh.store.mapper;

import com.asdasd.mjeesh.store.entity_dto.AccountDto;
import com.asdasd.mjeesh.store.entity.account.Account;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountFactory implements MapperFactory<AccountDto, Account> {

    @Override
    public AccountDto map(Account account) {
        return new AccountDto(
                account.getId(),
                account.getInitials(),
                account.getEmail(),
                account.getContact(),
                account.getPassword(),
                account.getRole()
        );
    }

    @Override
    public List<AccountDto> map(List<Account> accounts) {
        return accounts.stream()
                .map(account -> new AccountDto(
                        account.getId(),
                        account.getInitials(),
                        account.getEmail(),
                        account.getContact(),
                        account.getPassword(),
                        account.getRole()
                ))
                .collect(Collectors.toList());
    }
}

