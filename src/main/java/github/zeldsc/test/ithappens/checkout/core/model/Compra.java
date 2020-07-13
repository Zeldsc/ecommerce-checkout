package github.zeldsc.test.ithappens.checkout.core.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import github.zeldsc.test.ithappens.checkout.core.domain.StatusCompra;
import github.zeldsc.test.ithappens.checkout.core.domain.TipoPromocao;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@NotNull
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "compra")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private List<ItemCompra> itens;
	
	@ManyToOne
	@JoinColumn(name = "promocao")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Promocao promocao;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	@JoinColumn(name = "frete")
	private Frete frete;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	@JoinColumn(name = "entrega")
	private Endereco entrega;
	
	@ManyToOne
	@JoinColumn(name = "cliente", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Cliente cliente;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private StatusCompra status;
	
	@NotNull
	@Column(nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private LocalDate dataInicio;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private LocalDate dataUltimaAtualizacao;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<ItemCompra> getItens() {
		return itens;
	}
	
	public void setItens(List<ItemCompra> itens) {
		this.itens = itens;
	}
	
	public Promocao getPromocao() {
		return promocao;
	}
	
	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}
	
	public Frete getFrete() {
		return frete;
	}
	
	public void setFrete(Frete frete) {
		this.frete = frete;
	}	
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatusCompra getStatus() {
		return status;
	}
	
	public void setStatus(StatusCompra status) {
		this.status = status;
	}
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	public LocalDate getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	
	public void setDataUltimaAtualizacao(LocalDate dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
	
	public Endereco getEntrega() {
		return entrega;
	}

	public void setEntrega(Endereco entrega) {
		this.entrega = entrega;
	}

	public Double getValorTotal() {
		return itens.stream()
			.mapToDouble(ItemCompra::getTotal)
			.sum();
	}
	
	public Double getValorDesconto() {
		Double valor = this.getValorTotal();
		if(valor > 0 && Objects.nonNull(promocao)) {
			Double valorDesconto = promocao.getTipo()
					.equals(TipoPromocao.PERCENTUAL_DESCONTO) 
					? valor * promocao.getValor() /100
					: promocao.getValor();
			return valorDesconto > valor
					? valorDesconto - (valorDesconto - valor)
					: valorDesconto;
		}
		return Double.valueOf(0);
	}
	
	public Double getValor() {
		Double total = this.getValorTotal() - this.getValorDesconto();
		return Objects.nonNull(frete)
				? total + frete.getPreco()
				: total;
	}
}
