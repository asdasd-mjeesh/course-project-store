package com.asdasd.mjeesh.store.repository.order;

import com.asdasd.mjeesh.store.entity.order.Order;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>,
        QuerydslPredicateExecutor<Order> {

    @Override
    Order save(Order entity);

    @Override
    Optional<Order> findById(Long aLong);

    @Override
    List<Order> findAll();

    Page<Order> findByAccountId(Long accountId, Pageable paging);

    Page<Order> findAll(Predicate predicate, Pageable paging);

    @Override
    void deleteById(Long id);
}
