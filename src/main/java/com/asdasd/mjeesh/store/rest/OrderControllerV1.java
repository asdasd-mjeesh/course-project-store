package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.entity_dto.OrderDto;
import com.asdasd.mjeesh.store.entity.order.Order;
import com.asdasd.mjeesh.store.filter_dto.OrderFilter;
import com.asdasd.mjeesh.store.mapper.OrderMapper;
import com.asdasd.mjeesh.store.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderControllerV1 {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderControllerV1(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PatchMapping("/update")
    @PreAuthorize("hasAuthority('order:update')")
    public OrderDto update(@RequestBody Order order) {
        return orderMapper.map(orderService.update(order));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('order:read')")
    public OrderDto findById(@PathVariable("id") Long id) {
        try {
            Order order = orderService.findById(id).orElse(new Order());
            return orderMapper.map(order);
        } catch (EmptyResultDataAccessException exception) {
            return new OrderDto(null, null, null, null);
        }
    }

    @GetMapping("/account/{accountId}")
    @PreAuthorize("hasAuthority('order:read')")
    public List<OrderDto> findAllByAccountId(@PathVariable("accountId") Long accountId,
                                             @RequestParam(value = "PAGE", defaultValue = "0") Integer pageNo) {
        List<Order> orders = orderService.findAllByAccountId(accountId, pageNo);
        return orderMapper.map(orders);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('order:read')")
    public List<OrderDto> findAll() {
        List<Order> orders = orderService.findAll();
        return orderMapper.map(orders);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('order:read')")
    public List<OrderDto> findAllByFilter(@RequestParam(value = "PAGE", defaultValue = "0") Integer pageNo,
                                          @RequestBody OrderFilter filter) {
        List<Order> orders = orderService.findAllByFilter(filter, pageNo);
        return orderMapper.map(orders);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('order:delete')")
    public void delete(@PathVariable("id") Long id) {
        orderService.delete(id);
    }
}
