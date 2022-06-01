package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.entity_dto.ItemDto;
import com.asdasd.mjeesh.store.entity.item.Item;
import com.asdasd.mjeesh.store.filter_dto.ItemFilter;
import com.asdasd.mjeesh.store.mapper.ItemMapper;
import com.asdasd.mjeesh.store.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemControllerV1 {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemControllerV1(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('item:save')")
    public ItemDto saveOrUpdate(@RequestBody Item item) {
        return itemMapper.map(itemService.save(item));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('item:read')")
    public ItemDto findById(@PathVariable("id") Long id) {
        Item item = itemService.findById(id).orElse(new Item());
        return itemMapper.map(item);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('item:read')")
    public List<ItemDto> findAll() {
        List<Item> items = itemService.findAll();
        return itemMapper.map(items);
    }

    @GetMapping("/producerId/{producerId}")
    @PreAuthorize("hasAuthority('item:read')")
    public List<ItemDto> findAllByProducerId(@PathVariable("producerId") Long producerId,
                                             @RequestParam(value = "PAGE", defaultValue = "0") Integer pageNo) {
        List<Item> items = itemService.findAllByProducerId(producerId, pageNo);
        return itemMapper.map(items);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('item:read')")
    public List<ItemDto> findAllByFilter(@RequestParam(value = "PAGE", defaultValue = "0") Integer pageNo,
                                         @RequestBody ItemFilter filter) {
        List<Item> items = itemService.findAllByFilter(filter, pageNo);
        return itemMapper.map(items);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('item:delete')")
    public void delete(@PathVariable("id") Long id) {
        itemService.delete(id);
    }
}
