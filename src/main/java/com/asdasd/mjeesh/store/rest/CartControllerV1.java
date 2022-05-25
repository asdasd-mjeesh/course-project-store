package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.dto.OrderDto;
import com.asdasd.mjeesh.store.entity.order.OrderItem;
import com.asdasd.mjeesh.store.mapper.OrderFactory;
import com.asdasd.mjeesh.store.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartControllerV1 {
    private final CartService cartService;
    private final OrderFactory orderFactory;

    @Autowired
    public CartControllerV1(CartService cartService, OrderFactory orderFactory) {
        this.cartService = cartService;
        this.orderFactory = orderFactory;
    }

    @GetMapping("/{accountId}")
    public OrderDto findByAccountId(@PathVariable("accountId") Long accountId) {
        return orderFactory.map(cartService.findByAccountId(accountId).get());
    }

    @PostMapping("/{accountId}")
    public void addItem(@PathVariable("accountId") Long accountId,
                        @RequestBody OrderItem item) {
        cartService.addItem(item, accountId);
    }

    @DeleteMapping("/{accountId}")
    public void removeItem(@PathVariable("accountId") Long accountId,
                           @RequestParam("DELETE_ITEM_ID") Long itemId) {
        cartService.removeItem(itemId, accountId);
    }
}
