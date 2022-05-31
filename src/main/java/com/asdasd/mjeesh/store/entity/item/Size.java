package com.asdasd.mjeesh.store.entity.item;

import com.asdasd.mjeesh.store.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "size")
public class Size extends BaseEntity {

    @Column(name = "title")
    private String title;
}
