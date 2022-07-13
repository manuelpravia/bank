package com.nttdata.bank.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SolicitudDto {
    private String id;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private String idModedero;
    private BigDecimal amount;
    private String modoPago;
}
