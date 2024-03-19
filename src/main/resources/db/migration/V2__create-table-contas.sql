create table contas(
    id bigint not null auto_increment,
    idBeneficiario bigint not null,
    vlConta DECIMAL(10, 2) not null,
    dsTipoPagamento VARCHAR(20) not null,
    vlFinal DECIMAL(10, 2),

    primary key(id),
    
    constraint fk_contas_beneficiario_id FOREIGN KEY (idBeneficiario) REFERENCES beneficiarios(id)
);

create table parcelas(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    idConta BIGINT not null,
    vlParcela DECIMAL(10, 2),
    vlPago DECIMAL(10, 2),
    isPago TINYINT(1),
    cdPagamento VARCHAR(60)

    constraint fk_parcelas_contas_id FOREIGN KEY (idConta) REFERENCES contas(id)
);