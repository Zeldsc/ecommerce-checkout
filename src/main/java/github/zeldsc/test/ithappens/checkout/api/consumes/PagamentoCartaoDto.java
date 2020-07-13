package github.zeldsc.test.ithappens.checkout.api.consumes;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import github.zeldsc.test.ithappens.checkout.core.model.PagamentoCartao;


public class PagamentoCartaoDto {
	
	@NotNull
	@Min(100000000)
	private Long numero;
	
	@NotEmpty
	private String nomeProprietario;
	
	@NotEmpty
	private String cpfProprietario;
	
	@NotNull
	@Min(100)
	@Max(999)
	private Long csv;
	
	@NotNull
	@Min(1)
	private Long parcela;
	
	@Min(0)
	@Max(100)
	private Double juros;
	
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
	
	public PagamentoCartao parse() {
		PagamentoCartao pagamento = new PagamentoCartao();
		pagamento.setCpfProprietario(cpfProprietario);
		pagamento.setNomeProprietario(nomeProprietario);
		pagamento.setNumero(numero);
		pagamento.setCsv(csv);
		pagamento.setJuros(juros);
		pagamento.setParcela(parcela);
		return pagamento;
	}
}
