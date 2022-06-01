package com.asdasd.mjeesh.store.service.producer;

import com.asdasd.mjeesh.store.entity.producer.Producer;
import com.asdasd.mjeesh.store.filter_dto.ProducerFilter;
import com.asdasd.mjeesh.store.repository.producer.ProducerRepository;
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

import static com.asdasd.mjeesh.store.entity.producer.QProducer.*;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Value("${page.size}")
    private Integer PAGE_SIZE;
    private final ProducerRepository producerRepository;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Producer saveOrUpdate(Producer producer) {
        return producerRepository.save(producer);
    }

    @Override
    public Optional<Producer> findById(Long id) {
        return producerRepository.findById(id);
    }

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public List<Producer> findAllByFilter(ProducerFilter filter, Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, PAGE_SIZE, Sort.unsorted());

        Predicate predicate = QPredicates.builder()
                .add(filter.name(), producer.name::containsIgnoreCase)
                .buildAnd();

        Page<Producer> requestResult = producerRepository.findAll(predicate, paging);

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
