package com.asdasd.mjeesh.store.service.producer;

import com.asdasd.mjeesh.store.entity.producer.Producer;
import com.asdasd.mjeesh.store.repository.producer.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;
    private final Integer PAGE_SIZE = 10;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return producerRepository.findById(id);
    }

    @Override
    public List<Producer> findAll(Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE, Sort.unsorted());
        Page<Producer> requestResult = producerRepository.findAll(paging);

        if (requestResult.hasContent()) {
            return requestResult.getContent();
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(Long id) {
        producerRepository.deleteById(id);
    }
}
