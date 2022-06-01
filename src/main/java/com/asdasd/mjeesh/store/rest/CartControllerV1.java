package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.entity.cart.Cart;
import com.asdasd.mjeesh.store.entity_dto.CartDto;
import com.asdasd.mjeesh.store.entity.order.OrderItem;
import com.asdasd.mjeesh.store.mapper.CartMapper;
import com.asdasd.mjeesh.store.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class
CartControllerV1 {
    private final CartService cartService;
    private final CartMapper cartMapper;

    @Autowired
    public CartControllerV1(CartService cartService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    @GetMapping("/{accountId}")
    @PreAuthorize("hasAuthority('cart:read')")
    public CartDto findByAccountId(@PathVariable("accountId") Long accountId) {
        Cart cart = cartService.findByAccountId(accountId).orElse(new Cart());
        return cartMapper.map(cart);
    }

    @PostMapping("/{accountId}")
    @PreAuthorize("hasAuthority('cart:edit')")
    public void addItem(@PathVariable("accountId") Long accountId,
                        @RequestBody OrderItem item) {
        cartService.addItem(item, accountId);
    }

    @DeleteMapping("/{accountId}")
    @PreAuthorize("hasAuthority('cart:edit')")
    public void removeItem(@PathVariable("accountId") Long accountId,
                           @RequestParam("DELETE_ITEM_ID") Long itemId) {
        cartService.removeItem(itemId, accountId);
    }

    @PostMapping("/buy/{accountId}")
    @PreAuthorize("hasAuthority('cart:edit')")
    public void buy(@PathVariable("accountId") Long accountId) {
        cartService.buy(accountId);
    }
}
