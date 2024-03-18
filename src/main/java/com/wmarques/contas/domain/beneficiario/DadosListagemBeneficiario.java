package com.wmarques.contas.domain.beneficiario;

public record DadosListagemBeneficiario(
    Long id,
    String nmBeneficiario,
    String nmFantasia
) {

    public DadosListagemBeneficiario(Beneficiario beneficiario) {
        this(beneficiario.getId(), beneficiario.getNmBeneficiario(), beneficiario.getNmFantasia());
    }
}
