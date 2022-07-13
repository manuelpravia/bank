package com.nttdata.bank.domain.service;

import com.nttdata.bank.domain.dto.TransactionDto;
import com.nttdata.bank.infraestructure.data.document.Solicitud;
import com.nttdata.bank.infraestructure.data.repository.SolicitudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
@Slf4j
@Service
public class BanckServiveImpl implements BankService {

    private final TransacctionEventService transacctionEventService;

    public BanckServiveImpl(TransacctionEventService transacctionEventService) {
        super();
        this.transacctionEventService = transacctionEventService;
    }

    @Autowired
    SolicitudRepository solicitudRepository;

    @Override
    public Flux<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }

    @Override
    public Mono<Solicitud> confirm(Solicitud solicitud) {
        log.info("Entrando al servicio: " + solicitud.toString());
        TransactionDto transactionDto= new TransactionDto();
        transactionDto.setAmountBootCoin(solicitud.getAmount());
        transactionDto.setIdMonedero(solicitud.getIdModedero());
        transactionDto.setEstado(solicitud.getEstado());
        transactionDto.setModoPago(solicitud.getModoPago());
        transactionDto.setIdSolicitud(solicitud.getId());

        transactionDto.setAmountBootCoin(solicitud.getAmount().multiply(BigDecimal.valueOf(2.30)));
        log.info("TransaccionDto " + transactionDto.toString());
        this.transacctionEventService.publish(transactionDto);
        log.info("transaccion realizada con exito: " + transactionDto.toString());
        return solicitudRepository.save(solicitud);
    }


    @Override
    public Mono<Solicitud> finById(String id) {
        return solicitudRepository.findById(id);
    }
}
