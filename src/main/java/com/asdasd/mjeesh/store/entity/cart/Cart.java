package com.asdasd.mjeesh.store.entity.cart;

import com.asdasd.mjeesh.store.entity.BaseEntity;
import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.entity.order.OrderItem;
import com.asdasd.mjeesh.store.entity.order.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Cart extends BaseEntity {

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

    public Cart(Account account) {
        this.account = account;
        this.isCart = true;
        this.status = Status.IS_CART;
        this.date = LocalDate.now();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(Long itemId) {
        items.removeIf(item -> item.getId().equals(itemId));
    }

    public BigDecimal getPrice() {
        BigDecimal price = BigDecimal.ZERO;
        for (OrderItem item : items) {
            price = price.add(item.getItem().getCost().multiply(BigDecimal.valueOf(item.getCount())));
        }

        return price;
    }
}
