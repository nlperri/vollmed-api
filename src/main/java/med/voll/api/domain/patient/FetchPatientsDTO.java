package med.voll.api.domain.patient;

public record FetchPatientsDTO (
        Long id,
        String name,
        String email,
        String cpf
) {
    public FetchPatientsDTO(Patient patient) {
        this(patient.getId() ,patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
