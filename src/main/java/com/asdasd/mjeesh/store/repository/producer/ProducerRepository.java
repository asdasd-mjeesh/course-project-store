package com.asdasd.mjeesh.store.repository.producer;

import com.asdasd.mjeesh.store.entity.producer.Producer;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository extends JpaRepository<Producer, Long>,
        QuerydslPredicateExecutor<Producer> {

    Producer save(Producer producer);

    @Override
    Optional<Producer> findById(Long aLong);

    @Override
    List<Producer> findAll();

    @Override
    Page<Producer> findAll(Predicate predicate, Pageable paging);

    @Override
    void deleteById(Long aLong);
}
