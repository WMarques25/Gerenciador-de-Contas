package com.wmarques.contas.domain.beneficiario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long>{

    Page<Beneficiario> findAll(Pageable pageable);

    Page<Beneficiario> findByNmBeneficiarioContaining(String nmBeneficiario, Pageable pageable);

    Page<Beneficiario> findByNmFantasiaContaining(String nmFantasia, Pageable pageable);
}
