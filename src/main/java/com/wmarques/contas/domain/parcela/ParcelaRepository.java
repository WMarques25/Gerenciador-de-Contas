package com.wmarques.contas.domain.parcela;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.constraints.NotNull;

public interface ParcelaRepository extends JpaRepository<Parcela, Long>{

    Page<Parcela> findAll(Pageable pageable);

    Page<Parcela> findAllByContaId(@NotNull Long idConta, Pageable pageable);

    Page<Parcela> findAllByIcPagoIsTrue(Pageable pageable);

    Page<Parcela> findAllByIcPagoIsFalse(Pageable pageable);

    Page<Parcela> findAllByContaBeneficiarioNmFantasiaContaining(String nmFantasia, Pageable pageable);
}
