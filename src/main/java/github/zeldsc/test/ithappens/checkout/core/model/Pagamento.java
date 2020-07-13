package github.zeldsc.test.ithappens.checkout.core.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import github.zeldsc.test.ithappens.checkout.core.domain.StatusPagamento;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long id;
	
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "pagador")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	@JsonIgnore
	private Cliente pagador;
	
	@ManyToOne
	@JoinColumn(name = "compra")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	@JsonIgnore
	private Compra compra;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private LocalDate data;
	
	@DateTimeFormat(pattern = "HH:mm:ss")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private LocalDateTime hora;
	
	@Enumerated(EnumType.ORDINAL)
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private StatusPagamento status;
	
	public Pagamento() {
		this.status = StatusPagamento.AGUARDANDO;
		this.data = LocalDate.now();
		this.hora = LocalDateTime.now();
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public Cliente getPagador() {
		return pagador;
	}
	
	public void setPagador(Cliente pagador) {
		this.pagador = pagador;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public LocalDateTime getHora() {
		return hora;
	}
	
	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}
	
	public StatusPagamento getStatus() {
		return status;
	}
	
	public void setStatus(StatusPagamento status) {
		this.status = status;
	}
	
	public Compra getCompra() {
		return compra;
	}
	
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
}
