package com.asdasd.mjeesh.store.service.item;

import com.asdasd.mjeesh.store.entity.item.Item;
import com.asdasd.mjeesh.store.filter_dto.ItemFilter;
import com.asdasd.mjeesh.store.repository.item.ItemRepository;
import com.asdasd.mjeesh.store.util.QPredicates;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asdasd.mjeesh.store.entity.item.QItem.*;

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
    public List<Item> findAllByProducerId(Long producerId, Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE, Sort.unsorted());
        Page<Item> requestResult = itemRepository.findByProducerId(producerId, paging);

        if (requestResult.hasContent()) {
            return requestResult.getContent();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> findAllByFilter(ItemFilter filter, Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE, Sort.unsorted());

        Predicate predicate = QPredicates.builder()
                .add(filter.title(), item.title::containsIgnoreCase)
                .add(filter.producerName(), item.producer.name::containsIgnoreCase)
                .add(filter.minCost(), item.cost::goe)
                .add(filter.maxCost(), item.cost::loe)
                .buildAnd();

        Page<Item> requestResult = itemRepository.findAll(predicate, paging);

        if (requestResult.hasContent()) {
            return requestResult.getContent();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
}
