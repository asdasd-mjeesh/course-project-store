package com.asdasd.mjeesh.store.repository.item;

import com.asdasd.mjeesh.store.entity.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item save(Item item);

    @Override
    Optional<Item> findById(Long id);

    Page<Item> findByProducerId(Long producerId, Pageable paging);

    Page<Item> findAll(Pageable paging);

    @Override
    List<Item> findAll();

    @Override
    void deleteById(Long aLong);
}
