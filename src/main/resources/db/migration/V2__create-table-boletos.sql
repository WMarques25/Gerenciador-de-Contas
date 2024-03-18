CREATE TABLE boletos (
    id BIGINT AUTO_INCREMENT NOT NULL,
    dtVencimento DATE NOT NULL,
    dtPagamento DATE,
    id_beneficiario BIGINT NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    valorPago DECIMAL(10, 2),
    codPagamento VARCHAR(255) NOT NULL UNIQUE,
    pago BOOLEAN DEFAULT false,
    PRIMARY KEY (id),
    constraint fk_boletos_id_beneficiario foreign key(beneficiario) references beneficiarios(id)
);