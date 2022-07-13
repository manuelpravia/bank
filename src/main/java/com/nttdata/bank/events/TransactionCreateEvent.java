package com.nttdata.bank.events;

import com.nttdata.bank.domain.dto.TransactionDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransactionCreateEvent extends Event<TransactionDto>{
}
