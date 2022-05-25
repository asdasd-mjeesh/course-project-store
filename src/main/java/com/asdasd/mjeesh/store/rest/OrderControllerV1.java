package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.dto.OrderDto;
import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.mapper.OrderFactory;
import com.asdasd.mjeesh.store.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public OrderDto findById(@PathVariable("id") Long id) throws Exception {
        Order order = orderService.findById(id).orElseThrow(Exception::new);
        return orderFactory.map(order);
    }

    // localhost:1337/api/v1/orders/?PAGE=0
    @GetMapping("/")
    public List<OrderDto> findAll(@RequestParam("PAGE") Integer pageNo) {
        List<Order> orders = orderService.findAll(pageNo);
        return orderFactory.map(orders);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }
}
