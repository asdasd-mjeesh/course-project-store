package com.asdasd.mjeesh.store.repository.order;

import com.asdasd.mjeesh.store.entity.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    Order save(Order entity);

    @Override
    Optional<Order> findById(Long aLong);

    @Override
    List<Order> findAll();

    Page<Order> findByAccountId(Long accountId, Pageable paging);

    Page<Order> findAll(Pageable paging);

    @Override
    void deleteById(Long id);
}
