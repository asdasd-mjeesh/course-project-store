package com.asdasd.mjeesh.store.repository.cart;

import com.asdasd.mjeesh.store.entity.cart.Cart;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class CartRepositoryImpl implements CartRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Cart> findByAccountId(Long accountId) {
        Cart cart = entityManager.createQuery(
                        """
                                SELECT c
                                FROM Cart c
                                WHERE c.isCart = TRUE AND c.account.id = :accountId
                                """,
                        Cart.class)
                .setParameter("accountId", accountId)
                .getSingleResult();

        return Optional.ofNullable(cart);
    }
}
