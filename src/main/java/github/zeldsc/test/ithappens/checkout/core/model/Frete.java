package github.zeldsc.test.ithappens.checkout.core.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(columnNames = {"cep_origem", "cep_destino", "transportadora"})
}) 
public class Frete {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@Embedded
	private FretePk pk;
	
	@NotEmpty
	@Column(nullable = false)
	private String descricao;
	
	@NotNull
	@Min(0)
	@Column(nullable = false)
	private Long prazoEntrega;
	
	@NotNull
	@Min(0)
	@Column(name = "preco", nullable = false, precision = 10, scale = 2)
	private Double preco;
	
	@ManyToOne
	@JoinColumn(name = "transportadora")
	private Transportadora transportadora;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public FretePk getPk() {
		return pk;
	}
	
	public void setPk(FretePk pk) {
		this.pk = pk;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Long getPrazoEntrega() {
		return prazoEntrega;
	}
	
	public void setPrazoEntrega(Long prazoEntrega) {
		this.prazoEntrega = prazoEntrega;
	}
	
	public Transportadora getTransportadora() {
		return transportadora;
	}
	
	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}
	
}
