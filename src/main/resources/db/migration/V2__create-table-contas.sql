create table contas(
    id bigint not null auto_increment,
    id_Beneficiario bigint not null,
    vl_Conta DECIMAL(10, 2) not null,
    ds_Tipo_Pagamento VARCHAR(20) not null,
    vl_Pago DECIMAL(10, 2),
    vl_Final DECIMAL(10, 2),

    primary key(id),
    
    constraint fk_contas_beneficiario_id FOREIGN KEY (id_Beneficiario) REFERENCES beneficiarios(id)
);

create table parcelas(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_Conta BIGINT not null,
    dt_Vencimento DATE,
    vl_Parcela DECIMAL(10, 2),
    vl_Pago DECIMAL(10, 2),
    ic_Pago TINYINT(1),
    cd_Pagamento VARCHAR(60),

    constraint fk_parcelas_contas_id FOREIGN KEY (id_Conta) REFERENCES contas(id)
);