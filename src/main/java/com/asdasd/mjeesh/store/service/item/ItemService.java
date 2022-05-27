package com.asdasd.mjeesh.store.service.item;

import com.asdasd.mjeesh.store.entity.item.Item;
import com.asdasd.mjeesh.store.filter_dto.ItemFilter;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    Optional<Item> findById(Long id);

    List<Item> findAll();

    List<Item> findAllByProducerId(Long producerId, Integer pageNo);

    List<Item> findAllByFilter(ItemFilter filter, Integer pageNo);

    void delete(Long id);
}
