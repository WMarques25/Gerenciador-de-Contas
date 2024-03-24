package com.wmarques.contas.domain.conta;

import java.util.List;

import com.wmarques.contas.domain.parcela.DadosParcelaConta;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroConta(
    @NotNull
    Long idBeneficiario,

    @NotNull
    TipoPagamento tipoPagamento,

    @NotNull
    List<DadosParcelaConta> listaParcela
) {

}
