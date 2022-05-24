package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.dto.ItemDto;
import com.asdasd.mjeesh.store.entity.item.Item;
import com.asdasd.mjeesh.store.mapper.ItemFactory;
import com.asdasd.mjeesh.store.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ItemDto findById(@PathVariable("id") Long id) throws Exception {
        Item item = itemService.findById(id).orElseThrow(Exception::new);
        return itemFactory.map(item);
    }

    @GetMapping("/producerId/{producerId}/pageNo/{pageNo}")
    public List<ItemDto> findByProducerId(@PathVariable("producerId") Long producerId,
                                          @PathVariable("pageNo") Integer pageNo) {
        List<Item> items = itemService.findByProducerId(producerId, pageNo);
        return itemFactory.map(items);
    }

//    @GetMapping("/?PAGE={pageNo}")
    @GetMapping("/pageNo/{pageNo}")
    public List<ItemDto> findAll(@PathVariable("pageNo") Integer pageNo) {
        List<Item> items = itemService.findAll(pageNo);
        return itemFactory.map(items);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        itemService.delete(id);
    }
}
