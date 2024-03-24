package com.wmarques.contas.domain.parcela;

import java.time.LocalDate;

import com.wmarques.contas.domain.conta.Conta;
import com.wmarques.contas.domain.conta.ContaRepository;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
public class Parcela {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConta")
    private Conta conta;

    private LocalDate dtVencimento;
    private double vlParcela;
    private double vlPago;
    private String cdPagamento;

    @Column(name = "icPago")
    private boolean isPago;

    public Parcela(DadosCadastroParcela dados, ContaRepository contaRepository) {
        this.conta = contaRepository.findById(dados.idConta()).orElseThrow(() -> new RuntimeException("Conta não encontrada com ID: " + dados.idConta()));
        this.dtVencimento = dados.dtVencimento();
        this.vlParcela = dados.vlParcela();
        this.vlPago = 0;
        this.cdPagamento = dados.cdPagamento();
        this.isPago = false;
    }

    public Parcela(Conta conta, DadosParcelaConta dados) {
        this.conta = conta;
        this.dtVencimento = dados.dtVencimento();
        this.vlParcela = dados.vlParcela();
        this.vlPago = 0;
        this.cdPagamento = dados.cdPagamento();
        this.isPago = false;
    }

    public void pagar(double vlPago){
        this.isPago = true;
        this.vlPago = vlPago;
        this.conta.attValorPago();
    }
}
