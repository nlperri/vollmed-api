package med.voll.api.domain.appointment.validations.cancellation;

import med.voll.api.domain._errors.ValidationCheckException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.CancelAppointmentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("AdvanceTimeValidatorCancellation")
public class AdvanceTimeValidator implements  CancelAppointmentValidator{

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(CancelAppointmentDTO data) {
        var appointment = repository.getReferenceById(data.appointmentId());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getDate()).toHours();

        if(differenceInHours < 24) {
            throw new ValidationCheckException("Consulta somente pode ser cancelada com antecedência mínima de 24h.");
        }
    }
}
