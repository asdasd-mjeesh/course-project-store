package com.asdasd.mjeesh.store.service.account;

import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.entity.cart.Cart;
import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.entity.order.Status;
import com.asdasd.mjeesh.store.repository.account.AccountRepository;
import com.asdasd.mjeesh.store.repository.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CartRepository cartRepository;
    private final Integer PAGE_SIZE = 10;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CartRepository cartRepository) {
        this.accountRepository = accountRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Account save(Account account) {
        Account createdAccount = accountRepository.save(account);

        Cart cart = new Cart(account);
        cartRepository.save(cart);

        return createdAccount;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> findAll(Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE, Sort.unsorted());
        Page<Account> requestResult = accountRepository.findAll(paging);

        if (requestResult.hasContent()) {
            return requestResult.getContent();
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
