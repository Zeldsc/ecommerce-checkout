package github.zeldsc.test.ithappens.checkout.core.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FretePk {
	
	@NotNull
	@Column(name = "cep_origem")
	private Long cepOrigem;
	
	@NotNull
	@Column(name = "cep_destino")
	private Long cepDestino;
	
	public Long getCepOrigem() {
		return cepOrigem;
	}
	
	public void setCepOrigem(Long cepOrigem) {
		this.cepOrigem = cepOrigem;
	}
	
	public Long getCepDestino() {
		return cepDestino;
	}
	
	public void setCepDestino(Long cepDestino) {
		this.cepDestino = cepDestino;
	}
	
}
