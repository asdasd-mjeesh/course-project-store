package com.asdasd.mjeesh.store.service.item;

import com.asdasd.mjeesh.store.entity.item.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    Optional<Item> findById(Long id);

    List<Item> findAll(Integer pageNo);

    List<Item> findByProducerId(Long producerId, Integer pageNo);

    void delete(Long id);
}
