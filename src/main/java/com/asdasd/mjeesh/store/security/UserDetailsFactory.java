package com.asdasd.mjeesh.store.security;

import com.asdasd.mjeesh.store.entity.account.Account;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class UserDetailsFactory {

    public static UserDetails fromAccount(Account account) {
        return new User(
                account.getEmail(),
                account.getPassword(),
                true,
                true,
                true,
                true,
                account.getRole().getAuthorities()
        );
    }
}
