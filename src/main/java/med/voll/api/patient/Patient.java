package med.voll.api.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Boolean ativo;

    @Embedded
    private Address endereco;

    public Patient(CreatePatientDTO data) {
        this.nome = data.nome();
        this.email = data.email();
        this.cpf = data.cpf();
        this.telefone = data.telefone();
        this.endereco = new Address(data.endereco());
        this.ativo = true;
    }

    public void update(UpdatePatientDTO data) {
        this.nome = data.nome() != null ? data.nome() : this.nome;
        this.telefone = data.telefone() != null ? data.telefone() : this.telefone;
        this.endereco = data.endereco() != null ? this.endereco.update(data.endereco()) : this.endereco;
    }

    public void delete() {
        this.ativo = false;
    }
}
