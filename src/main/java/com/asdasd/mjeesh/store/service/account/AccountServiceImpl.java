package com.asdasd.mjeesh.store.service.account;

import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.entity.cart.Cart;
import com.asdasd.mjeesh.store.filter_dto.AccountFilter;
import com.asdasd.mjeesh.store.repository.account.AccountRepository;
import com.asdasd.mjeesh.store.repository.cart.CartRepository;
import com.asdasd.mjeesh.store.util.QPredicates;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.asdasd.mjeesh.store.entity.account.QAccount.*;

@Service
public class AccountServiceImpl implements AccountService {

    @Value("${page.size}")
    private Integer PAGE_SIZE;
    private final AccountRepository accountRepository;
    private final CartRepository cartRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CartRepository cartRepository) {
        this.accountRepository = accountRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Account saveOrUpdate(Account account) {
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
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findAllByFilter(AccountFilter filter, Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE, Sort.unsorted());

        Predicate predicate = QPredicates.builder()
                .add(filter.initials(), account.initials::containsIgnoreCase)
                .add(filter.email(), account.email::containsIgnoreCase)
                .add(filter.contact(), account.email::containsIgnoreCase)
                .add(filter.role(), account.role::eq)
                .buildAnd();

        Page<Account> requestResult = accountRepository.findAll(predicate, paging);
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
