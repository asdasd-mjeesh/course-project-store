package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.entity_dto.ProducerDto;
import com.asdasd.mjeesh.store.filter_dto.ProducerFilter;
import com.asdasd.mjeesh.store.mapper.ProducerMapper;
import com.asdasd.mjeesh.store.entity.producer.Producer;
import com.asdasd.mjeesh.store.service.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producers")
public class ProducerControllerV1 {
    private final ProducerService producerService;
    private final ProducerMapper producerMapper;

    @Autowired
    public ProducerControllerV1(ProducerService producerService, ProducerMapper producerMapper) {
        this.producerService = producerService;
        this.producerMapper = producerMapper;
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('producer:save')")
    public ProducerDto saveOrUpdate(@RequestBody Producer producer) {
        return producerMapper.map(producerService.saveOrUpdate(producer));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('producer:read')")
    public ProducerDto findById(@PathVariable("id") Long id) {
        Producer producer = producerService.findById(id).orElse(new Producer());
        return producerMapper.map(producer);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('producer:read')")
    public List<ProducerDto> findAll() {
        List<Producer> producers = producerService.findAll();
        return producerMapper.map(producers);
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('producer:read')")
    public List<ProducerDto> findAllByFilter(@RequestParam(value = "PAGE", defaultValue = "0") Integer pageNo,
                                             @RequestBody ProducerFilter filter) {
        List<Producer> producers = producerService.findAllByFilter(filter, pageNo);
        return producerMapper.map(producers);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('producer:delete')")
    public void delete(@PathVariable("id") Long id) {
        producerService.delete(id);
    }
}
