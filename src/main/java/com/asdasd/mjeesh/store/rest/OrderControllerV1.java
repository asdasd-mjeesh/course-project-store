package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.entity_dto.OrderDto;
import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.exception.EntityNotFoundException;
import com.asdasd.mjeesh.store.filter_dto.OrderFilter;
import com.asdasd.mjeesh.store.mapper.OrderFactory;
import com.asdasd.mjeesh.store.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderControllerV1 {
    private final OrderService orderService;
    private final OrderFactory orderFactory;

    @Autowired
    public OrderControllerV1(OrderService orderService, OrderFactory orderFactory) {
        this.orderService = orderService;
        this.orderFactory = orderFactory;
    }

    @PostMapping("")
    public OrderDto save(@RequestBody Order order) {
        return orderFactory.map(orderService.save(order));
    }

    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable("id") Long id) {
        try {
            Order order = orderService.findById(id).orElseThrow(
                    ()-> new EntityNotFoundException(Order.class, "id=" + id));
            return orderFactory.map(order);
        } catch (EmptyResultDataAccessException exception) {
            exception.printStackTrace();
            throw new EntityNotFoundException(Order.class, "id=" + id);
        }
    }

    @GetMapping("/account/{accountId}")
    public List<OrderDto> findAllByAccountId(@PathVariable("accountId") Long accountId,
                                             @RequestParam(value = "PAGE", defaultValue = "0") Integer pageNo) {
        List<Order> orders = orderService.findAllByAccountId(accountId, pageNo);
        return orderFactory.map(orders);
    }

    @GetMapping("/all")
    public List<OrderDto> findAll() {
        List<Order> orders = orderService.findAll();
        return orderFactory.map(orders);
    }

    // localhost:1337/api/v1/orders/?PAGE=0
    @GetMapping("/")
    public List<OrderDto> findAllByFilter(@RequestParam("PAGE") Integer pageNo,
                                          @RequestBody OrderFilter filter) {
        List<Order> orders = orderService.findAllByFilter(filter, pageNo);
        return orderFactory.map(orders);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }
}
