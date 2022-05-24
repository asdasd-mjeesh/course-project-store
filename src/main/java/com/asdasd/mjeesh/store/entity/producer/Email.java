package com.asdasd.mjeesh.store.entity.producer;

import com.asdasd.mjeesh.store.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "email")
public class Email extends BaseEntity {

    @Column(name = "address")
    private String address;
}
