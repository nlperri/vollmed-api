package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain._errors.ValidationCheckException;
import med.voll.api.domain.appointment.ScheduleAppointmentDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ClinicOpeningHoursValidator implements ScheduleAppointmentValidator {

    public void validate(ScheduleAppointmentDTO data) {
        var scheduleDate = data.date();

        var sunday = scheduleDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = scheduleDate.getHour() < 7;
        var afterOpening = scheduleDate.getHour() > 18;

        if(sunday || beforeOpening || afterOpening) {
            throw new ValidationCheckException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
