package com.nttdata.bank.domain.service;



import com.nttdata.bank.domain.dto.SolicitudDto;
import com.nttdata.bank.events.Event;
import com.nttdata.bank.events.SolicitudCreateEvent;
import com.nttdata.bank.infraestructure.data.document.Solicitud;
import com.nttdata.bank.infraestructure.data.repository.SolicitudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegistrarSolicitud {

    @Autowired
    SolicitudRepository solicitudRepository;

    @KafkaListener(
            topics = "${topic.solicitud.name:solicitudes}",
            containerFactory = "kafkaListenerContainerFactory",
            groupId = "grupo1")
    public void consumer(Event<?> event) {
        if (event.getClass().isAssignableFrom(SolicitudCreateEvent.class)) {
            SolicitudCreateEvent customerCreatedEvent = (SolicitudCreateEvent) event;
            SolicitudDto solicitudDto = customerCreatedEvent.getData();
            Solicitud solicitud = new Solicitud();
            solicitud.setNombre(solicitudDto.getNombre());
            solicitud.setDni(solicitudDto.getDni());
            solicitud.setEmail(solicitudDto.getEmail());
            solicitud.setTelefono(solicitudDto.getTelefono());
            solicitud.setIdModedero(solicitudDto.getIdModedero());
            solicitud.setAmount(solicitudDto.getAmount());
            solicitud.setModoPago(solicitudDto.getModoPago());
            solicitud.setEstado("pendiente");
            solicitudRepository.save(solicitud);
            log.info("registro:" + solicitudDto.getDni());
            log.info("Recibido la solicitud Id={}, data={}",
                    customerCreatedEvent.getId(),
                    customerCreatedEvent.getData().toString());
        }

    }
}
