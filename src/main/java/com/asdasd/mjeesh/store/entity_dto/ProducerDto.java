package com.asdasd.mjeesh.store.entity_dto;

import com.asdasd.mjeesh.store.entity.producer.Contact;
import com.asdasd.mjeesh.store.entity.producer.Email;

import java.util.List;

public record ProducerDto(Long id,
                          String name,
                          List<Contact> contacts,
                          List<Email> emails) {
}
