package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.entity.cart.Cart;
import com.asdasd.mjeesh.store.entity_dto.CartDto;
import com.asdasd.mjeesh.store.entity.order.OrderItem;
import com.asdasd.mjeesh.store.exception.EntityNotFoundException;
import com.asdasd.mjeesh.store.mapper.CartFactory;
import com.asdasd.mjeesh.store.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartControllerV1 {
    private final CartService cartService;
    private final CartFactory cartFactory;

    @Autowired
    public CartControllerV1(CartService cartService, CartFactory cartFactory) {
        this.cartService = cartService;
        this.cartFactory = cartFactory;
    }

    @GetMapping("/{accountId}")
    public CartDto findByAccountId(@PathVariable("accountId") Long accountId) {
        Cart cart = cartService.findByAccountId(accountId).orElseThrow(
                ()-> new EntityNotFoundException(Cart.class, "accountId=" + accountId));

        return cartFactory.map(cart);
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

    @PostMapping("/buy/{accountId}")
    public void buy(@PathVariable("accountId") Long accountId) {
        cartService.buy(accountId);
    }
}
