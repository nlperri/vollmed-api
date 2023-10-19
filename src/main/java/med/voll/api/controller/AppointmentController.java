package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentDetailsDTO;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    AppointmentRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailsDTO> schedule(@RequestBody @Valid ScheduleAppointmentDTO data) {

        return ResponseEntity.ok(new AppointmentDetailsDTO(null, null, null, null));
    }
}
