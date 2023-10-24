package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain._errors.ValidationCheckException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithAppointmentAtSameTime implements ScheduleAppointmentValidator {

    @Autowired
    private AppointmentRepository repository;

    public void validate(ScheduleAppointmentDTO data) {
        var doctorHasAnotherAppointmentAtTheSameTime = repository.existsByDoctorIdAndDateAndReasonCancellationIsNull(data.doctorId(), data.date());

        if(doctorHasAnotherAppointmentAtTheSameTime) {
            throw new ValidationCheckException("Médico já possui outra consulta agendada nesse mesmo horário.");
        }
    }
}
