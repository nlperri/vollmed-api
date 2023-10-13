package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record PatientDetailsDTO(
        String nome,
        String email,
        String telefone,
        String cpf,
        Address endereco
) {
    public PatientDetailsDTO(Patient patient) {
        this(patient.getNome(), patient.getEmail(), patient.getTelefone(), patient.getCpf(), patient.getEndereco());
    }
}