package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.appointment.ScheduleAppointmentDTO;

public interface ScheduleAppointmentValidator {
    void validate(ScheduleAppointmentDTO data);
}