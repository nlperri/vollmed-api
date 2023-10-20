package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record PatientDetailsDTO(
        String name,
        String email,
        String phone,
        String cpf,
        Address address
) {
    public PatientDetailsDTO(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getPhone(), patient.getCpf(), patient.getAddress());
    }
}