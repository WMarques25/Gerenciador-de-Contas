package com.wmarques.contas.domain.conta;

import com.wmarques.contas.domain.beneficiario.Beneficiario;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Conta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBeneficiario")
    private Beneficiario beneficiario; 
}
