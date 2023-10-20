package med.voll.api.domain.appointment.validations;

import med.voll.api.domain._errors.ValidationCheckException;
import med.voll.api.domain.appointment.ScheduleAppointmentDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceTimeValidator implements ScheduleAppointmentValidator{

    public void validate (ScheduleAppointmentDTO data) {
        var scheduleDate = data.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, scheduleDate).toMinutes();

        if(differenceInMinutes < 30) {
            throw new ValidationCheckException("Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
