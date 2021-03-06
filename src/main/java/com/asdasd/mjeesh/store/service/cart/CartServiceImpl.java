package com.asdasd.mjeesh.store.service.cart;

import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.entity.cart.Cart;
import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.entity.order.OrderItem;
import com.asdasd.mjeesh.store.exception.EntityNotFoundException;
import com.asdasd.mjeesh.store.repository.account.AccountRepository;
import com.asdasd.mjeesh.store.repository.cart.CartRepository;
import com.asdasd.mjeesh.store.repository.cart_item.CartItemRepository;
import com.asdasd.mjeesh.store.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements com.asdasd.mjeesh.store.service.cart.CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;
    private final AccountRepository accountRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           CartItemRepository cartItemRepository,
                           OrderService orderRepository,
                           AccountRepository accountRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderService = orderRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Cart saveOrUpdate(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> findByAccountId(Long accountId) {
        return cartRepository.findByAccountId(accountId);
    }

    @Override
    public void addItem(OrderItem item, Long accountId) {
        Cart cart = cartRepository.findByAccountId(accountId).orElse(new Cart());
        cart.addItem(item);
        saveOrUpdate(cart);
    }

    @Override
    public void removeItem(Long itemId, Long accountId) {
        Cart cart = cartRepository.findByAccountId(accountId).orElseThrow(
                ()-> new EntityNotFoundException(Cart.class, "accountId=" + accountId));

        cart.removeItem(itemId);
        cartItemRepository.deleteById(itemId);
        saveOrUpdate(cart);
    }

    @Override
    public void buy(Long accountId) {
        Account customerAccount = accountRepository.findById(accountId).orElseThrow(
                ()-> new EntityNotFoundException(Account.class, "id=" + accountId));

        Cart customerCart = cartRepository.findByAccountId(accountId).orElseThrow(
                ()-> new EntityNotFoundException(Cart.class, "accountId=" + accountId));

        Order order = new Order(customerAccount, List.copyOf(customerCart.getItems()));

        orderService.saveOrUpdate(order);
    }
}
