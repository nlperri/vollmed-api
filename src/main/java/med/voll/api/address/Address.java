package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Address(AddressDTO data) {
        this.logradouro = data.logradouro();
        this.bairro = data.bairro();
        this.cep = data.cep();
        this.numero = data.numero();
        this.complemento = data.complemento();
        this.cidade = data.cidade();
        this.uf = data.uf();
    }

    public Address update(AddressDTO data) {
        return new Address(
                data.logradouro() != null ? data.logradouro() : this.logradouro,
                data.bairro() != null ? data.bairro() : this.bairro,
                data.cep() != null ? data.cep() : this.cep,
                data.numero() != null ? data.numero() : this.numero,
                data.complemento() != null ? data.complemento() : this.complemento,
                data.cidade() != null ? data.cidade() : this.cidade,
                data.uf() != null ? data.uf() : this.uf
        );
    }
}
