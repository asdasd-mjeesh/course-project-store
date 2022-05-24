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
@Table(name = "contact")
public class Contact extends BaseEntity {

    @Column(name = "phone")
    private String phone;
}
