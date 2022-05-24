package com.asdasd.mjeesh.store.entity.item;

import com.asdasd.mjeesh.store.entity.BaseEntity;
import com.asdasd.mjeesh.store.entity.producer.Producer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @ManyToMany
    @JoinTable(name = "item_size",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id"))
    private List<Size> sizes;
}
