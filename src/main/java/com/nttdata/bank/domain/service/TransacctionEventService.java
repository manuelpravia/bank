package com.nttdata.bank.domain.service;

import com.nttdata.bank.domain.dto.SolicitudDto;
import com.nttdata.bank.domain.dto.TransactionDto;
import com.nttdata.bank.events.Event;
import com.nttdata.bank.events.EventType;
import com.nttdata.bank.events.SolicitudCreateEvent;
import com.nttdata.bank.events.TransactionCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
@Component
public class TransacctionEventService {

    @Autowired
    private KafkaTemplate<String, Event<?>> producer;

    @Value("${topic.transaccion.name:tansacciones}")
    private String topicTransaction;

    public void publish(TransactionDto transactionDto) {

        TransactionCreateEvent created = new TransactionCreateEvent();
        created.setData(transactionDto);
        created.setId(UUID.randomUUID().toString());
        created.setType(EventType.CREATED);
        created.setDate(new Date());

        this.producer.send(topicTransaction, created);
    }
}
