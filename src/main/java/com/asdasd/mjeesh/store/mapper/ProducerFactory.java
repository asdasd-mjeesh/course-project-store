package com.asdasd.mjeesh.store.mapper;

import com.asdasd.mjeesh.store.entity.producer.Producer;
import com.asdasd.mjeesh.store.entity_dto.ProducerDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProducerFactory implements MapperFactory<ProducerDto, Producer> {

    @Override
    public ProducerDto map(Producer producer) {
        return new ProducerDto(
                producer.getId(),
                producer.getName(),
                producer.getContacts(),
                producer.getEmails()
        );
    }

    @Override
    public List<ProducerDto> map(List<Producer> producers) {
        return producers.stream()
                .map(producer -> new ProducerDto(
                        producer.getId(),
                        producer.getName(),
                        producer.getContacts(),
                        producer.getEmails()
                ))
                .collect(Collectors.toList());
    }
}
