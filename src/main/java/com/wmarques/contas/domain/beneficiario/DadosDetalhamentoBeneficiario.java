package com.wmarques.contas.domain.beneficiario;

public record DadosDetalhamentoBeneficiario(
    Long id,
    String nmBeneficiario,
    String nmFantasia
) {

    public DadosDetalhamentoBeneficiario(Beneficiario beneficiario) {
        this(beneficiario.getId(), beneficiario.getNmBeneficiario(), beneficiario.getNmFantasia());
    }
}
