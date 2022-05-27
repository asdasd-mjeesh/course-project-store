package com.asdasd.mjeesh.store.service.order;

import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.filter_dto.OrderFilter;
import com.asdasd.mjeesh.store.repository.order.OrderRepository;
import com.asdasd.mjeesh.store.util.QPredicates;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final OrderRepository orderRepository;
    private final Integer PAGE_SIZE = 10;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findAllByAccountId(Long accountId, Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE, Sort.unsorted());
        Page<Order> requestResult = orderRepository.findByAccountId(accountId, paging);

        if (requestResult.hasContent()) {
            return requestResult.getContent();
        }
        return new ArrayList<>();
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
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
