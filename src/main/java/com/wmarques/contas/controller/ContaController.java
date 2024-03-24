package com.wmarques.contas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wmarques.contas.domain.beneficiario.BeneficiarioRepository;
import com.wmarques.contas.domain.conta.Conta;
import com.wmarques.contas.domain.conta.ContaRepository;
import com.wmarques.contas.domain.conta.DadosCadastroConta;
import com.wmarques.contas.domain.conta.DadosListagemConta;
import com.wmarques.contas.domain.parcela.ParcelaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;
    
    // Cadastrar contas
    @PostMapping
    @Transactional
    public ResponseEntity<DadosListagemConta> cadastrar(@RequestBody @Valid DadosCadastroConta dados) {
        Conta conta = new Conta(dados, beneficiarioRepository, parcelaRepository);
        repository.save(conta);
        return ResponseEntity.ok(DadosListagemConta.fromConta(conta));
    }
    
    // Listar todas as contas
    @GetMapping
    public ResponseEntity<Page<DadosListagemConta>> listar(@PageableDefault(size = 20, sort = {"id"}) Pageable pageable) {
        var page = repository.findAll(pageable).map(DadosListagemConta::fromConta);
        return ResponseEntity.ok(page);
    }

    // Listar contas por ID
    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemConta> buscarPorId(@PathVariable Long id) {
        var conta = repository.getReferenceById(id);
        return ResponseEntity.ok(DadosListagemConta.fromConta(conta));
    }

    // Listar contas por beneficiário
    @GetMapping("/beneficiario/{idBeneficiario}")
    public ResponseEntity<Page<DadosListagemConta>> buscarPorBeneficiario(@PathVariable Long idBeneficiario, @PageableDefault(size = 20, sort = {"id"}) Pageable pageable) {
        var page = repository.findAllByBeneficiarioId(idBeneficiario, pageable).map(DadosListagemConta::fromConta);
        return ResponseEntity.ok(page);
    }
}
