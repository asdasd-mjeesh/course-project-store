package com.asdasd.mjeesh.store.entity.account;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    USER(Set.of(
            Permission.ACCOUNT_READ, Permission.ACCOUNT_EDIT, Permission.ACCOUNT_DELETE,
            Permission.ORDER_SAVE, Permission.ORDER_READ,
            Permission.CART_READ, Permission.CART_EDIT,
            Permission.PRODUCT_READ,
            Permission.PRODUCER_READ
    )),

    EMPLOYEE(Set.of(
            Permission.ACCOUNT_READ, Permission.ACCOUNT_EDIT, Permission.ACCOUNT_DELETE,
            Permission.ORDER_SAVE, Permission.ORDER_READ, Permission.ORDER_EDIT,
            Permission.CART_READ, Permission.CART_EDIT,
            Permission.PRODUCT_READ,
            Permission.PRODUCER_READ
    )),

    ADMIN(Set.of(
            Permission.ACCOUNT_SAVE, Permission.ACCOUNT_READ, Permission.ACCOUNT_EDIT, Permission.ACCOUNT_DELETE,
            Permission.ORDER_SAVE, Permission.ORDER_READ, Permission.ORDER_EDIT, Permission.ORDER_DELETE,
            Permission.CART_READ, Permission.CART_EDIT,
            Permission.PRODUCT_SAVE, Permission.PRODUCT_READ, Permission.PRODUCT_DELETE,
            Permission.PRODUCER_SAVE, Permission.PRODUCER_READ, Permission.PRODUCER_DELETE
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
