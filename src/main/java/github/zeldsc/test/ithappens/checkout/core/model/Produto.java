package github.zeldsc.test.ithappens.checkout.core.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@NotEmpty
	@Column(name = "descricao", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private String descricao;
	
	@NotNull
	@Min(0)
	@Column(name = "preco", nullable = false, precision = 10, scale = 2)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Double preco;
	
	@NotNull
	@Min(0)
	@Column(name = "quantidade", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long quantidade;
	
	private String banner;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	public boolean isDisponivel() {
		return (Objects.nonNull(quantidade) && quantidade > 0) 
				? true : false;
	}

	public String getBanner() {
		return banner;
	}
	
	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
