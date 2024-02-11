CREATE TABLE boletos (
    id BIGINT AUTO_INCREMENT NOT NULL,
    dtVencimento DATE NOT NULL,
    dtPagamento DATE,
    beneficiario BIGINT NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    valorPago DECIMAL(10, 2),
    codPagamento VARCHAR(255) NOT NULL UNIQUE,
    pago BOOLEAN DEFAULT false,
    PRIMARY KEY (id),
    FOREIGN KEY (beneficiario) REFERENCES beneficiarios(id)
);