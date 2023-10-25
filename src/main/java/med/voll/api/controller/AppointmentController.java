package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private ScheduleAppointmentService scheduleAppointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailsDTO> schedule(@RequestBody @Valid ScheduleAppointmentDTO data) {

        var appointmentResponse = scheduleAppointmentService.execute(data);

        return ResponseEntity.ok(appointmentResponse);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancel(@RequestBody @Valid CancelAppointmentDTO data) {
        scheduleAppointmentService.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
