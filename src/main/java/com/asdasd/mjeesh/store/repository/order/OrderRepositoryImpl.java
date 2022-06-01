package com.asdasd.mjeesh.store.repository.order;

import com.asdasd.mjeesh.store.entity.order.Order;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${page.size}")
    private Integer PAGE_SIZE;

    @Override
    public Optional<Order> find(Long id) {
        Order order = entityManager.createQuery(
                        """
                                SELECT o
                                FROM Order o
                                WHERE o.id = :id AND o.isCart = FALSE
                                """,
                        Order.class)
                .setParameter("id", id)
                .getSingleResult();

        return Optional.ofNullable(order);
    }

    @Override
    public List<Order> findAllByAccountId(Long accountId, Integer pageNo) {
        return entityManager.createQuery(
                        """
                                SELECT o
                                FROM Order o
                                WHERE o.account.id = :accountId AND o.isCart = FALSE
                                """,
                        Order.class)
                .setParameter("accountId", accountId)
                .setFirstResult(pageNo * PAGE_SIZE)
                .setMaxResults(PAGE_SIZE)
                .getResultList();
    }

    public List<Order> findAll() {
        return entityManager.createQuery(
                        """
                                SELECT o
                                FROM Order o
                                WHERE o.isCart = FALSE
                                """,
                        Order.class)
                .getResultList();
    }
}
