package com.asdasd.mjeesh.store.entity.order;

import com.asdasd.mjeesh.store.entity.BaseEntity;
import com.asdasd.mjeesh.store.entity.account.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
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

    public Order(Account account, List<OrderItem> items) {
        this.account = account;
        this.items = items;
        this.isCart = false;
        this.date = LocalDate.now();
        this.status = Status.IN_PROCESS;
    }
}
