package com.nttdata.bank.infraestructure.data.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Data
@Document(collection = "solicitudes")
public class Solicitud {
    @Id
    private String id;
    private String nombre;
    private String dni;
    private String email;
    private String telefono;
    private String idModedero;
    private BigDecimal amount;
    private String modoPago;
    private String estado;
}
