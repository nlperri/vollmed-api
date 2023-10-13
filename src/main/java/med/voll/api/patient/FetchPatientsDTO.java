package med.voll.api.patient;

public record FetchPatientsDTO (
        Long id,
        String nome,
        String email,
        String cpf
) {
    public FetchPatientsDTO(Patient patient) {
        this(patient.getId() ,patient.getNome(), patient.getEmail(), patient.getCpf());
    }
}
