package com.asdasd.mjeesh.store.filter_dto;

import com.asdasd.mjeesh.store.entity.account.Role;

public record AccountFilter(String initials,
                            String email,
                            String contact,
                            Role role) {
}
