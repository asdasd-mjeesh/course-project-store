package com.asdasd.mjeesh.store.repository.producer;

import com.asdasd.mjeesh.store.entity.producer.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Producer save(Producer producer);

    @Override
    Optional<Producer> findById(Long aLong);

    @Override
    List<Producer> findAll();

    Page<Producer> findAll(Pageable paging);

    @Override
    void deleteById(Long aLong);
}
