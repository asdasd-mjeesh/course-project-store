package com.asdasd.mjeesh.store.service.cart;

import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.entity.order.OrderItem;
import com.asdasd.mjeesh.store.entity.order.Status;
import com.asdasd.mjeesh.store.repository.account.AccountRepository;
import com.asdasd.mjeesh.store.repository.cart.CartRepository;
import com.asdasd.mjeesh.store.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, OrderRepository orderRepository, AccountRepository accountRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Order save(Order cart) {
        cart.setCart(true);
        return cartRepository.save(cart);
    }

    public Optional<Order> findByAccountId(Long accountId) {
        return cartRepository.findByAccountId(accountId);
    }

    @Override
    public void addItem(OrderItem item, Long accountId) {
        Order cart = cartRepository.findByAccountId(accountId).orElse(new Order());
        cart.addItem(item);
        save(cart);
    }

    @Override
    public void removeItem(Long itemId, Long accountId) {
        Order cart = cartRepository.findByAccountId(accountId).get();
        cart.removeItem(itemId);
        save(cart);
    }

    @Override
    public void buy(Long accountId) {
        Order cart = cartRepository.findByAccountId(accountId).get();
        Order order = new Order(
                accountRepository.findById(accountId).get(),
                Status.IN_PROCESS,
                LocalDate.now(),
                false,
                cart.getItems()
        );

        orderRepository.save(order);
    }
}
