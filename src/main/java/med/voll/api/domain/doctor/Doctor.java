package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty especialidade;

    @Embedded
    private Address endereco;

    private Boolean ativo;

    public Doctor(CreateDoctorDTO data) {
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.crm = data.crm();
        this.especialidade = data.especialidade();
        this.endereco = new Address(data.endereco());
        this.ativo = true;
    }

    public void update(UpdateDoctorDTO data) {
        this.nome = data.nome() != null ? data.nome() : this.nome;
        this.telefone = data.telefone() != null ? data.telefone() : this.telefone;
        this.endereco = data.endereco() != null ? this.endereco.update(data.endereco()) : this.endereco;
    }

    public void delete() {
        this.ativo = false;
    }
}
