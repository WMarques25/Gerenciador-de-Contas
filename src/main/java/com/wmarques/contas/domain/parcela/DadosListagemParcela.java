package com.wmarques.contas.domain.parcela;

public record DadosListagemParcela(
    Long id,
    Long idConta,
    String nmBeneficiario,
    String cdPagamento,
    double vlParcela,
    double vlPago,
    boolean isPago
) {
    
        public DadosListagemParcela(Parcela parcela) {
            this(
                parcela.getId(),
                parcela.getConta().getId(),
                parcela.getConta().getBeneficiario().getNmBeneficiario(),
                parcela.getCdPagamento(),
                parcela.getVlParcela(),
                parcela.getVlPago(),
                parcela.isIcPago()
            );
        }
}
