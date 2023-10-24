package med.voll.api.domain.appointment.validations.cancellation;

import med.voll.api.domain.appointment.CancelAppointmentDTO;

public interface CancelAppointmentValidator {

    void validate(CancelAppointmentDTO data);
}
