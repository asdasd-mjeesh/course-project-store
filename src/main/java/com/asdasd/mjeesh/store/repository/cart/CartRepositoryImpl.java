package com.asdasd.mjeesh.store.repository.cart;

import com.asdasd.mjeesh.store.entity.order.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class CartRepositoryImpl implements CartRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Order> findByAccountId(Long accountId) {
        Order cart = entityManager.createQuery("""
                        SELECT o
                        FROM Order o
                        WHERE o.isCart = TRUE AND o.account.id = :accountId
                        """,
                        Order.class)
                .setParameter("accountId", accountId)
                .getSingleResult();

        return Optional.ofNullable(cart);
    }
}
