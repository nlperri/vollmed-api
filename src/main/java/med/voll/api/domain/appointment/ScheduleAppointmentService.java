package med.voll.api.domain.appointment;

import med.voll.api.domain.appointment.validations.ScheduleAppointmentValidator;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain._errors.ValidationCheckException;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ScheduleAppointmentValidator> validators;

    public void execute(ScheduleAppointmentDTO data) {

        if (!patientRepository.existsById(data.patientId())) {
            throw new ValidationCheckException("Id do paciente informado não existe.");
        }

        if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
            throw new ValidationCheckException("Id do médico informado não existe.");
        }

        validators.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(data.patientId());
        var doctor = chooseDoctor(data);

        var appointment = new Appointment(null, doctor, patient, data.date(), null);
        appointmentRepository.save(appointment);
    }

    private Doctor chooseDoctor(ScheduleAppointmentDTO data) {
        if (data.doctorId() != null) {
            return doctorRepository.getReferenceById(data.doctorId());
        }

        if (data.specialty() == null) {
            throw new ValidationCheckException("Especialidade é obrigatória quando médico não for informado.");
        }

        return doctorRepository.chooseRandomDoctorAvailableOnDate(data.specialty(), data.date());
    }

    public void cancel(CancelAppointmentDTO data) {
        if (!appointmentRepository.existsById(data.appointmentId())) {
            throw new ValidationCheckException("Id da consulta informado não existe.");
        }

        var appointment = appointmentRepository.getReferenceById(data.appointmentId());
        appointment.cancel(data.reason());
    }
}
