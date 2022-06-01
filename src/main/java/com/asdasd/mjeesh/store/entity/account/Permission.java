package com.asdasd.mjeesh.store.entity.account;

public enum Permission {

    ACCOUNT_SAVE("account:save"),
    ACCOUNT_READ("account:read"),
    ACCOUNT_DELETE("account:delete"),

    ITEM_SAVE("item:save"),
    ITEM_READ("item:read"),
    ITEM_DELETE("item:delete"),

    PRODUCER_SAVE("producer:save"),
    PRODUCER_READ("producer:read"),
    PRODUCER_DELETE("producer:delete"),

    ORDER_SAVE("order:save"),
    ORDER_READ("order:read"),
    ORDER_DELETE("order:delete"),

    CART_READ("cart:read"),
    CART_EDIT("cart:edit");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
