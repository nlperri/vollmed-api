package med.voll.api.doctor;

public record FetchDoctorsDTO (
  String nome,
  String email,
  String crm,
  Specialty especialidade
){
    public FetchDoctorsDTO(Doctor doctor) {
        this(doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getEspecialidade());
    }

}
