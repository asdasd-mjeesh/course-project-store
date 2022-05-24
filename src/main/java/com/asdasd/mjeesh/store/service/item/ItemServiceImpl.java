package com.asdasd.mjeesh.store.service.item;

import com.asdasd.mjeesh.store.entity.item.Item;
import com.asdasd.mjeesh.store.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final Integer PAGE_SIZE = 10;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> findByProducerId(Long producerId, Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE, Sort.unsorted());
        Page<Item> requestResult = itemRepository.findByProducerId(producerId, paging);

        if (requestResult.hasContent()) {
            return requestResult.getContent();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> findAll(Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE, Sort.unsorted());
        Page<Item> requestResult = itemRepository.findAll(paging);

        if (requestResult.hasContent()) {
            return requestResult.getContent();
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
