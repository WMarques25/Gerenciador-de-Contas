package com.wmarques.contas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.wmarques.contas.domain.beneficiario.Beneficiario;
import com.wmarques.contas.domain.beneficiario.BeneficiarioRepository;
import com.wmarques.contas.domain.beneficiario.DadosCadastroBeneficiario;
import com.wmarques.contas.domain.beneficiario.DadosDetalhamentoBeneficiario;
import com.wmarques.contas.domain.beneficiario.DadosListagemBeneficiario;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody DadosCadastroBeneficiario dados){
        var beneficiario = new Beneficiario(dados);
        repository.save(beneficiario);

        var uri = UriComponentsBuilder.fromPath("/beneficiarios/{id}")
                .buildAndExpand(beneficiario.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoBeneficiario(beneficiario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemBeneficiario>> listar(Pageable pageable){
        var page = repository.findAll(pageable);
        var dtos = page.map(DadosListagemBeneficiario::new);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var benef = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoBeneficiario(benef));
    }
}
