package com.asdasd.mjeesh.store.entity_dto;

import com.asdasd.mjeesh.store.entity.account.Role;

public record AccountDto(Long id,
                         String initials,
                         String email,
                         String contact,
                         String password,
                         Role role) {
}
