package com.wmarques.contas.domain.parcela;

import java.time.LocalDate;

import com.wmarques.contas.domain.conta.Conta;
import com.wmarques.contas.domain.conta.ContaRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Parcela")
@Table(name = "parcelas")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Parcela {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Conta")
    private Conta conta;

    @Column(name = "dt_Vencimento")
    private LocalDate dtVencimento;

    @Column(name = "vl_Parcela")
    private double vlParcela;

    @Column(name = "vl_Pago")
    private double vlPago;

    @Column(name = "cd_Pagamento")
    private String cdPagamento;

    @Column(name = "ic_Pago")
    private boolean icPago;

    public Parcela(DadosCadastroParcela dados, ContaRepository contaRepository) {
        this.conta = contaRepository.findById(dados.idConta()).orElseThrow(() -> new RuntimeException("Conta não encontrada com ID: " + dados.idConta()));
        this.dtVencimento = dados.dtVencimento();
        this.vlParcela = dados.vlParcela();
        this.vlPago = 0;
        this.cdPagamento = dados.cdPagamento();
        this.icPago = false;
    }

    public Parcela(Conta conta, DadosParcelaConta dados) {
        this.conta = conta;
        this.dtVencimento = dados.dtVencimento();
        this.vlParcela = dados.vlParcela();
        this.vlPago = 0;
        this.cdPagamento = dados.cdPagamento();
        this.icPago = false;
    }

    public void pagar(double vlPago){
        this.icPago = true;
        this.vlPago = vlPago;
        this.conta.attValorPago();
    }
}
