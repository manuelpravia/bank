package com.nttdata.bank.events;

import com.nttdata.bank.domain.dto.SolicitudDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SolicitudCreateEvent extends Event<SolicitudDto>{
}
