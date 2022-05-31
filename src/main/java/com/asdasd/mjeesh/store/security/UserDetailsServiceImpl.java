package com.asdasd.mjeesh.store.security;

import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.exception.EntityNotFoundException;
import com.asdasd.mjeesh.store.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email).orElseThrow(
                ()-> new EntityNotFoundException(Account.class, "email=" + email)
        );

        return UserDetailsFactory.fromAccount(account);
    }
}
