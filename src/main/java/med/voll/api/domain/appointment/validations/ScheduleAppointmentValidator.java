package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.ScheduleAppointmentDTO;

public interface ScheduleAppointmentValidator {
    void validate(ScheduleAppointmentDTO data);
}