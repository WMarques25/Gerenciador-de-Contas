package com.wmarques.contas.domain.conta;

import java.util.ArrayList;
import java.util.List;

import com.wmarques.contas.domain.beneficiario.Beneficiario;
import com.wmarques.contas.domain.beneficiario.BeneficiarioRepository;
import com.wmarques.contas.domain.parcela.DadosParcelaConta;
import com.wmarques.contas.domain.parcela.Parcela;
import com.wmarques.contas.domain.parcela.ParcelaRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    private double vlConta;
    
    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Parcela> listaParcela = new ArrayList<>();

    @Column(name = "dsTipoPagamento")
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;

    private double vlPago;

    public Conta(DadosCadastroConta dados, BeneficiarioRepository beneficiarioRepository, ParcelaRepository parcelaRepository) {
        this.beneficiario = beneficiarioRepository.findById(dados.idBeneficiario()).
                orElseThrow(() -> new RuntimeException(
                    "Beneficiário não encontrado com ID: " + dados.idBeneficiario()));
        this.tipoPagamento = dados.tipoPagamento();
        this.vlConta = 0;
        for (DadosParcelaConta p : dados.listaParcela()) {
            var parcela = new Parcela(this, p);
            this.listaParcela.add(parcela);
            this.vlConta += parcela.getVlParcela();
            parcelaRepository.save(parcela);
        }
    }
    
    public void attValorPago(){
        this.vlPago = 0;
        for (Parcela parcela : listaParcela) {
            if(parcela.isPago()){
                this.vlPago += parcela.getVlPago();
            }
        }
    }
}
