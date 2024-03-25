package com.wmarques.contas.domain.conta;

import com.wmarques.contas.domain.parcela.Parcela;

public record DadosListagemConta(
    Long id,
    String nmBeneficiario,
    double vlConta,
    double vlFinal,
    int qtParcelas,
    int qtParcelasPendentes,
    double vlPendente
) {
    public static DadosListagemConta fromConta(Conta conta) {
        int qtParcelasPendentes = 0;
        for(Parcela p : conta.getListaParcela()) {
            if (!p.isIcPago()) {
                qtParcelasPendentes++;
            }
        }
        
        return new DadosListagemConta(
            conta.getId(),
            conta.getBeneficiario().getNmBeneficiario(),
            conta.getVlConta(),
            conta.getVlPago(),
            conta.getListaParcela().size(),
            qtParcelasPendentes,
            (conta.getVlConta() - conta.getVlPago())
        );
    }
}
