package com.nttdata.bank.domain.service;


import com.nttdata.bank.infraestructure.data.document.Solicitud;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankService {

    Flux<Solicitud> findAll();

    Mono<Solicitud> confirm(Solicitud solicitud);

    Mono<Solicitud> finById(String id);
}
