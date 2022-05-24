package com.asdasd.mjeesh.store.entity.order;

import com.asdasd.mjeesh.store.entity.BaseEntity;
import com.asdasd.mjeesh.store.entity.account.Account;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "is_cart")
    private boolean isCart;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    public Order() {
        date = LocalDate.now();
    }
}
