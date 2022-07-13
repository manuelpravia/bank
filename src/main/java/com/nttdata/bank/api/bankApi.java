package com.nttdata.bank.api;

import com.nttdata.bank.domain.service.BankService;
import com.nttdata.bank.infraestructure.data.document.Solicitud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/consulta")
public class bankApi {

    @Autowired
    BankService bankService;

    @GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Solicitud> getAccountAll(){
        return bankService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Solicitud> getMovement(@PathVariable String id){
        return bankService.finById(id);
    }

    @PostMapping()
    public Mono<Solicitud> save(@RequestBody Solicitud solicitud){
         return  bankService.confirm(solicitud);
    }

   /* @PatchMapping("/{id}")
    public Mono<Solicitud> updateAccount(@RequestBody Solicitud solicitud){
        return  bankService.confirm(solicitud);
    }*/
}
