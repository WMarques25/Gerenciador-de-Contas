package com.wmarques.contas.domain.conta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Page<Conta> findAll(Pageable pageable);

    Page<Conta> findByBeneficiarioNmFantasiaContaining(String nmFantasia, Pageable pageable);

    Page<Conta> findAllByBeneficiarioId(Long idBeneficiario, Pageable pageable);
}
