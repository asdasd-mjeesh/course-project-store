package com.asdasd.mjeesh.store.service.order;

import com.asdasd.mjeesh.store.entity.account.Account;
import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.exception.EntityNotFoundException;
import com.asdasd.mjeesh.store.filter_dto.OrderFilter;
import com.asdasd.mjeesh.store.repository.order.OrderRepository;
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

import static com.asdasd.mjeesh.store.entity.order.QOrder.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${page.size}")
    private Integer PAGE_SIZE;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.find(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByAccountId(Long accountId, Integer pageNo) {
        return orderRepository.findAllByAccountId(accountId, pageNo);
    }

    @Override
    public List<Order> findAllByFilter(OrderFilter filter, Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE, Sort.unsorted());

        Predicate predicate = QPredicates.builder()
                .add(filter.fromDate(), order.date::after)
                .add(filter.endDate(), order.date::before)
                .add(filter.status(), order.status::eq)
                .buildAnd();

        Page<Order> requestResult = orderRepository.findAll(predicate, paging);
        if (requestResult.hasContent()) {
            return requestResult.getContent();
        }
        return new ArrayList<>();
    }

    @Override
    public Order update(Order order) {
        Order orderWithAccountBeforeUpdating = orderRepository.find(order.getId()).orElseThrow(
                ()-> new EntityNotFoundException(Order.class, "id=" + order.getId())
        );

        Account account = orderWithAccountBeforeUpdating.getAccount();
        order.setAccount(account);

        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
