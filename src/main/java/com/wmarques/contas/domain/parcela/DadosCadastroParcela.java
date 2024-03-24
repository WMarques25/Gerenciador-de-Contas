package com.wmarques.contas.domain.parcela;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroParcela(
    @NotNull
    Long idConta,

    @NotNull
    LocalDate dtVencimento,

    @NotNull
    double vlParcela,

    @NotNull
    String cdPagamento
) {

}
