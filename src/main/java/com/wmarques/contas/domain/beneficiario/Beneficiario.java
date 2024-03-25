package com.wmarques.contas.domain.beneficiario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "beneficiarios")
@Entity(name = "Beneficiario")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Beneficiario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_Beneficiario")
    private String nmBeneficiario;

    @Column(name = "nm_Fantasia")
    private String nmFantasia;

    public Beneficiario(DadosCadastroBeneficiario dados) {
        this.nmBeneficiario = dados.nmBeneficiario();
        this.nmFantasia = dados.nmFantasia();
    }
}
