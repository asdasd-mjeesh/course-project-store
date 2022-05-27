package com.asdasd.mjeesh.store.repository.item;

import com.asdasd.mjeesh.store.entity.item.Item;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<Item> {

    Item save(Item item);

    @Override
    Optional<Item> findById(Long id);

    Page<Item> findByProducerId(Long producerId, Pageable paging);

    @Override
    List<Item> findAll();

    @Override
    Page<Item> findAll(Predicate predicate, Pageable paging);

    @Override
    void deleteById(Long aLong);
}
