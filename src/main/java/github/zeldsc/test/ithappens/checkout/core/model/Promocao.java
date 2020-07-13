package github.zeldsc.test.ithappens.checkout.core.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import github.zeldsc.test.ithappens.checkout.core.domain.TipoPromocao;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
public class Promocao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	@NotEmpty
	@Size(min = 4, max = 8)
	@Column(name = "codigo", nullable = false, length = 8, unique = true)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private String codigo;
	
	@Min(0)
	@NotNull
	@Column(name = "disponivel", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long disponivel;
	
	@NotNull
	@Column(name = "inicio", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private LocalDate inicio;
	
	@NotNull
	@Column(name = "fim", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private LocalDate fim;
	
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo", nullable = false)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private TipoPromocao tipo;
	
	@NotNull
	@Min(0)
	@Column(name = "valor", nullable = false, precision = 8, scale = 2)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Double valor;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Long getDisponivel() {
		return disponivel;
	}
	
	public void setDisponivel(Long disponivel) {
		this.disponivel = disponivel;
	}
	
	public LocalDate getInicio() {
		return inicio;
	}
	
	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}
	
	public LocalDate getFim() {
		return fim;
	}
	
	public void setFim(LocalDate fim) {
		this.fim = fim;
	}
	
	public TipoPromocao getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoPromocao tipo) {
		this.tipo = tipo;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
}
