package com.asdasd.mjeesh.store.mapper;

import com.asdasd.mjeesh.store.entity.item.Item;
import com.asdasd.mjeesh.store.entity_dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemFactory implements MapperFactory<ItemDto, Item> {

    private final ProducerFactory producerFactory;

    @Autowired
    public ItemFactory(ProducerFactory producerFactory) {
        this.producerFactory = producerFactory;
    }

    @Override
    public ItemDto map(Item item) {
        return new ItemDto(
                item.getId(),
                item.getTitle(),
                item.getCost(),
                item.getType(),
                producerFactory.map(item.getProducer()),
                item.getSizes()
        );
    }

    @Override
    public List<ItemDto> map(List<Item> items) {
        return items.stream()
                .map(item -> new ItemDto(
                        item.getId(),
                        item.getTitle(),
                        item.getCost(),
                        item.getType(),
                        producerFactory.map(item.getProducer()),
                        item.getSizes()
                ))
                .collect(Collectors.toList());
    }
}
