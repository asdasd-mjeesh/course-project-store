package com.asdasd.mjeesh.store.rest;

import com.asdasd.mjeesh.store.dto.ProducerDto;
import com.asdasd.mjeesh.store.mapper.ProducerFactory;
import com.asdasd.mjeesh.store.entity.producer.Producer;
import com.asdasd.mjeesh.store.service.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producers")
public class ProducerControllerV1 {
    private final ProducerService producerService;
    private final ProducerFactory producerFactory;

    @Autowired
    public ProducerControllerV1(ProducerService producerService, ProducerFactory producerFactory) {
        this.producerService = producerService;
        this.producerFactory = producerFactory;
    }

    @PostMapping("")
    public ProducerDto save(@RequestBody Producer producer) {
        return producerFactory.map(producerService.save(producer));
    }

    @GetMapping("/{id}")
    public ProducerDto findById(@PathVariable("id") Long id) throws Exception {
        Producer producer = producerService.findById(id).orElseThrow(Exception::new);
        return producerFactory.map(producer);
    }

    // localhost:1337/api/v1/producers/?PAGE=0
    @GetMapping("/")
    public List<ProducerDto> findAll(@RequestParam("PAGE") Integer pageNo) {
        List<Producer> producers = producerService.findAll(pageNo);
        return producerFactory.map(producers);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        producerService.delete(id);
    }
}
