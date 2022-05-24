package com.asdasd.mjeesh.store.service.producer;

import com.asdasd.mjeesh.store.entity.producer.Producer;

import java.util.List;
import java.util.Optional;

public interface ProducerService {

    Producer save(Producer producer);

    Optional<Producer> findById(Long id);

    List<Producer> findAll(Integer pageNo);

    void delete(Long id);
}
