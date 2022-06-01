package com.asdasd.mjeesh.store.service.producer;

import com.asdasd.mjeesh.store.entity.producer.Producer;
import com.asdasd.mjeesh.store.filter_dto.ProducerFilter;

import java.util.List;
import java.util.Optional;

public interface ProducerService {

    Producer saveOrUpdate(Producer producer);

    Optional<Producer> findById(Long id);

    List<Producer> findAll();

    List<Producer> findAllByFilter(ProducerFilter filter, Integer pageNo);

    void delete(Long id);
}
