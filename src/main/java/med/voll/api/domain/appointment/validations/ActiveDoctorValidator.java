package med.voll.api.domain.appointment.validations;

import med.voll.api.domain._errors.ValidationCheckException;
import med.voll.api.domain.appointment.ScheduleAppointmentDTO;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements ScheduleAppointmentValidator {

    @Autowired
    private DoctorRepository repository;

    public void validate(ScheduleAppointmentDTO data) {

        if(data.doctorId() == null) {
            return;
        }

        var isDoctorActive = repository.findActiveById(data.doctorId());

        if(!isDoctorActive) {
            throw new ValidationCheckException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
