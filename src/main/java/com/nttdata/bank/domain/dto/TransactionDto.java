package com.nttdata.bank.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionDto {
    private  String id;
    private String idSolicitud;
    private String idMonedero;
    private BigDecimal amountBootCoin;
    private String estado;
    private BigDecimal amount;
    private String modoPago;
}
