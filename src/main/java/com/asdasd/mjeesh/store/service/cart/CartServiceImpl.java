package com.asdasd.mjeesh.store.service.cart;

import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.entity.cart.Cart;
import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.entity.order.OrderItem;
import com.asdasd.mjeesh.store.repository.account.AccountRepository;
import com.asdasd.mjeesh.store.repository.cart.CartRepository;
import com.asdasd.mjeesh.store.repository.cart_item.CartItemRepository;
import com.asdasd.mjeesh.store.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           OrderRepository orderRepository,
                           AccountRepository accountRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Cart save(Cart cart) {
        cart.setCart(true);
        return cartRepository.save(cart);
    }

    public Optional<Cart> findByAccountId(Long accountId) {
        return cartRepository.findByAccountId(accountId);
    }

    @Override
    public void addItem(OrderItem item, Long accountId) {
        Cart cart = cartRepository.findByAccountId(accountId).orElse(new Cart());
        cart.addItem(item);
        save(cart);
    }

    @Override
    public void removeItem(Long itemId, Long accountId) {
        Cart cart = cartRepository.findByAccountId(accountId).get();
        cart.removeItem(itemId);
        cartItemRepository.deleteById(itemId);
        save(cart);
    }

    @Override
    public void buy(Long accountId) {
        Account customerAccount = accountRepository.findById(accountId).get();
        Cart customerCart = cartRepository.findByAccountId(accountId).get();
        Order order = new Order(customerAccount, List.copyOf(customerCart.getItems()));

        orderRepository.save(order);
    }
}
