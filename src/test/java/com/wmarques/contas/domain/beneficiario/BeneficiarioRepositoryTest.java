package com.wmarques.contas.domain.beneficiario;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class BeneficiarioRepositoryTest {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;
    
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria cadastrar um beneficiário")
    public void testDeveriaCadastrarUmBeneficiario(){
        // Given
        var beneficiario = new Beneficiario(
            new DadosCadastroBeneficiario("Nome", "Fantasia"));
        
        // When
        beneficiarioRepository.save(beneficiario);

        // Then
        assertThat(beneficiarioRepository.count()).isEqualTo(1);
    }
}
