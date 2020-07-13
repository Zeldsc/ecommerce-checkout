package github.zeldsc.test.ithappens.checkout.api.produces;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import github.zeldsc.test.ithappens.checkout.core.domain.StatusCompra;
import github.zeldsc.test.ithappens.checkout.core.model.Compra;
import github.zeldsc.test.ithappens.checkout.core.model.ItemCompra;

public class CompraDto {
	
	private Long id;
	
	private List<ItemCompra> itens;
	
	private String promocao;
	
	private Long freteId;
	
	private StatusCompra status;
	
	private LocalDate dataInicio;
	
	private LocalDate dataUltimaAtualizacao;
	
	private Double valorTotal;
	
	private Double valorDesconto;
	
	private Double valor;
	
	public CompraDto(Compra compra) {
		this.id = compra.getId();
		this.itens = compra.getItens();
		this.promocao = Objects.isNull(compra.getPromocao()) ? null : compra.getPromocao().getCodigo();
		this.freteId = Objects.isNull(compra.getFrete()) ? null : compra.getFrete().getId();
		this.status = compra.getStatus();
		this.dataInicio = compra.getDataInicio();
		this.dataUltimaAtualizacao = compra.getDataUltimaAtualizacao();
		this.valorTotal = compra.getValorTotal();
		this.valorDesconto = compra.getValorDesconto();
		this.valor = compra.getValor();
	}
	
	public Long getId() {
		return id;
	}
	
	public List<ItemCompra> getItens() {
		return itens;
	}
	
	public String getPromocao() {
		return promocao;
	}
	
	public Long getFreteId() {
		return freteId;
	}
	
	public String getStatus() {
		return Objects.isNull(status) ? "" : status.getDescricao();
	}
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	
	public LocalDate getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public Double getValorDesconto() {
		return valorDesconto;
	}
	
	public Double getValor() {
		return valor;
	}
	
}
