package com.wmarques.contas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wmarques.contas.domain.conta.ContaRepository;
import com.wmarques.contas.domain.parcela.DadosCadastroParcela;
import com.wmarques.contas.domain.parcela.DadosListagemParcela;
import com.wmarques.contas.domain.parcela.Parcela;
import com.wmarques.contas.domain.parcela.ParcelaRepository;

@RestController
@RequestMapping("/parcelas")
public class ParcelaController {

    @Autowired
    private ParcelaRepository repository;

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Parcela> cadastrar(@RequestBody DadosCadastroParcela dados) {
        Parcela parcela = new Parcela(dados, contaRepository);
        repository.save(parcela);
        return ResponseEntity.ok(parcela);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemParcela>> listarContasAPagar(@PageableDefault(size = 20, sort = {"dtVencimento"}) Pageable paginacao) {
        var page = repository.findAllByIcPagoIsFalse(paginacao).map(DadosListagemParcela::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemParcela> detalhar(@PathVariable Long id) {
        Parcela parcela = repository.findById(id).orElseThrow(() -> new RuntimeException("Parcela não encontrada com ID: " + id));
        return ResponseEntity.ok(new DadosListagemParcela(parcela));
    }
    
    @GetMapping("/pagas")
    public ResponseEntity<Page<DadosListagemParcela>> listarContasPagas(@PageableDefault(size = 20, sort = {"dtVencimento"}, direction = Sort.Direction.DESC) Pageable paginacao) {
        var page = repository.findAllByIcPagoIsTrue(paginacao).map(DadosListagemParcela::new);
        return ResponseEntity.ok(page);
    }
    
    @PutMapping("/{id}/pagar")
    @Transactional
    public ResponseEntity<DadosListagemParcela> pagar(@PathVariable Long id, @RequestBody double vlPago) {
        Parcela parcela = repository.getReferenceById(id);
        parcela.pagar(vlPago);
        return ResponseEntity.ok(new DadosListagemParcela(parcela));
    }
}
