package com.wmarques.contas.domain.beneficiario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroBeneficiario(
    @NotBlank
    String nmBeneficiario,
    @NotBlank
    String nmFantasia
) {
}
