package github.zeldsc.test.ithappens.checkout.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
public class Entrega {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "frete")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Frete frete;
	
	@ManyToOne
	@JoinColumn(name = "destino")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Endereco destino;
	
	@ManyToOne
	@JoinColumn(name = "compra")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Compra compra;

	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long pedido;

	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private String codigoRastreio;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Frete getFrete() {
		return frete;
	}
	
	public void setFrete(Frete frete) {
		this.frete = frete;
	}
	
	public Endereco getDestino() {
		return destino;
	}
	
	public void setDestino(Endereco destino) {
		this.destino = destino;
	}
	
	public Compra getCompra() {
		return compra;
	}
	
	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Long getPedido() {
		return pedido;
	}
	
	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}
	
	public String getCodigoRastreio() {
		return codigoRastreio;
	}
	
	public void setCodigoRastreio(String codigoRastreio) {
		this.codigoRastreio = codigoRastreio;
	}
	
}
