package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain._errors.ValidationCheckException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithAppointmentAtSameDay implements ScheduleAppointmentValidator {

    @Autowired
    private AppointmentRepository repository;

    public void validate(ScheduleAppointmentDTO data) {
        var firstHour = data.date().withHour(7);
        var lastHour = data.date().withHour(18);
        var patientHasAnotherAppointmentThatDay = repository.existsByPatientIdAndDateBetween(data.patientId(), firstHour, lastHour);

        if(patientHasAnotherAppointmentThatDay) {
            throw new ValidationCheckException("Paciente já possui uma consulta agendada nesse dia.");
        }
    }

}
