package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain._errors.ValidationCheckException;
import med.voll.api.domain.appointment.ScheduleAppointmentDTO;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements ScheduleAppointmentValidator {

    @Autowired
    private PatientRepository repository;

    public void validate(ScheduleAppointmentDTO data) {
        var isPatientActive = repository.findActiveById(data.patientId());

        if(!isPatientActive) {
            throw new ValidationCheckException("Consulta não pode ser agendada com paciente excluído.");
        }
    }
}
