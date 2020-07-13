package github.zeldsc.test.ithappens.checkout.core.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
public class ItemCompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "produto")
	@JsonIgnore
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Produto produto;
	
	@Min(0)
	@NotNull
	@Column(name = "quantidade")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long quantidade;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "compra")
	@JsonIgnore
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Compra compra;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	public Compra getCompra() {
		return compra;
	}
	
	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Double getTotal() {
		return (Objects.nonNull(produto) && Objects.nonNull(produto.getPreco()))
				? (Objects.nonNull(quantidade) ? quantidade : 0) * produto.getPreco()
				: 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemCompra other = (ItemCompra) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}
	
}
