package com.wmarques.contas.domain.parcela;

import com.wmarques.contas.domain.conta.Conta;

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

    private double vlParcela;
    private double vlPago;
    private String cdPagamento;
    private boolean isPago;

    public Parcela(Conta conta, double vlParcela, String cdPagamento) {
        this.conta = conta;
        this.vlParcela = vlParcela;
        this.vlPago = 0;
        this.cdPagamento = cdPagamento;
        this.isPago = false;
    }

    public void pagar(double vlPago){
        this.isPago = true;
        this.vlPago = vlPago;
        // TODO adicionar chamada para att valor total da conta.
    }
}
