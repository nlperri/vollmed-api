package med.voll.api.patient;

public record FetchPatientsDTO (
        String nome,
        String email,
        String cpf
) {
    public FetchPatientsDTO(Patient patient) {
        this(patient.getNome(), patient.getEmail(), patient.getCpf());
    }
}
