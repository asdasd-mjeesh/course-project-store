package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.entity_dto.ItemDto;
import com.asdasd.mjeesh.store.entity.item.Item;
import com.asdasd.mjeesh.store.filter_dto.ItemFilter;
import com.asdasd.mjeesh.store.mapper.ItemFactory;
import com.asdasd.mjeesh.store.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemControllerV1 {
    private final ItemService itemService;
    private final ItemFactory itemFactory;

    @Autowired
    public ItemControllerV1(ItemService itemService, ItemFactory itemFactory) {
        this.itemService = itemService;
        this.itemFactory = itemFactory;
    }

    @PostMapping("")
    public ItemDto save(@RequestBody Item item) {
        return itemFactory.map(itemService.save(item));
    }

    @GetMapping("/{id}")
    public ItemDto findById(@PathVariable("id") Long id) {
        Item item = itemService.findById(id).orElse(new Item());
        return itemFactory.map(item);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('item:read')")
    public List<ItemDto> findAll() {
        List<Item> items = itemService.findAll();
        return itemFactory.map(items);
    }

    @GetMapping("/producerId/{producerId}")
    public List<ItemDto> findAllByProducerId(@PathVariable("producerId") Long producerId,
                                             @RequestParam(value = "PAGE", defaultValue = "0") Integer pageNo) {
        List<Item> items = itemService.findAllByProducerId(producerId, pageNo);
        return itemFactory.map(items);
    }

    // localhost:1337/api/v1/items/?PAGE=0
    @GetMapping("/")
    public List<ItemDto> findAllByFilter(@RequestParam("PAGE") Integer pageNo,
                                         @RequestBody ItemFilter filter) {
        List<Item> items = itemService.findAllByFilter(filter, pageNo);
        return itemFactory.map(items);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        itemService.delete(id);
    }
}
