package com.asdasd.mjeesh.store.entity.order;

import com.asdasd.mjeesh.store.entity.BaseEntity;
import com.asdasd.mjeesh.store.entity.item.Item;
import com.asdasd.mjeesh.store.entity.item.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = "order", callSuper = true)
@ToString(exclude = "order")
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @Column(name = "count")
    private Integer count;
}
