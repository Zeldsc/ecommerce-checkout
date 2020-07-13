package github.zeldsc.test.ithappens.checkout.core.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
public class PagamentoCartao extends Pagamento {
	
	@NotNull
	@Min(100000000)
	@Column(nullable = false)
	private Long numero;
	
	@NotEmpty
	@Column(nullable = false)
	private String nomeProprietario;
	
	@NotEmpty
	@Column(nullable = false)
	private String cpfProprietario;
	
	@NotNull
	@Min(100)
	@Max(999)
	@Column(nullable = false)
	private Long csv;
	
	@NotNull
	@Min(1)
	@Column(nullable = false)
	private Long parcela;
	
	@Min(0)
	@Max(100)
	@Column(precision = 3, scale = 2)
	private Double juros;
	
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private String codigoTransacao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private LocalDate dataConfirmacao;
	
	@DateTimeFormat(pattern = "HH:mm:ss")
	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private LocalDateTime horaConfirmacao;
	
	public Long getNumero() {
		return numero;
	}
	
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
	public String getNomeProprietario() {
		return nomeProprietario;
	}
	
	public void setNomeProprietario(String nomeProprietario) {
		this.nomeProprietario = nomeProprietario;
	}
	
	public String getCpfProprietario() {
		return cpfProprietario;
	}
	
	public void setCpfProprietario(String cpfProprietario) {
		this.cpfProprietario = cpfProprietario;
	}
	
	public Long getCsv() {
		return csv;
	}
	
	public void setCsv(Long csv) {
		this.csv = csv;
	}
	
	public Long getParcela() {
		return parcela;
	}
	
	public void setParcela(Long parcela) {
		this.parcela = parcela;
	}
	
	public Double getJuros() {
		return juros;
	}
	
	public void setJuros(Double juros) {
		this.juros = juros;
	}
	
	public String getCodigoTransacao() {
		return codigoTransacao;
	}
	
	public void setCodigoTransacao(String codigoTransacao) {
		this.codigoTransacao = codigoTransacao;
	}
	
	public LocalDate getDataConfirmacao() {
		return dataConfirmacao;
	}
	
	public void setDataConfirmacao(LocalDate dataConfirmacao) {
		this.dataConfirmacao = dataConfirmacao;
	}
	
	public LocalDateTime getHoraConfirmacao() {
		return horaConfirmacao;
	}
	
	public void setHoraConfirmacao(LocalDateTime horaConfirmacao) {
		this.horaConfirmacao = horaConfirmacao;
	}
	
}
