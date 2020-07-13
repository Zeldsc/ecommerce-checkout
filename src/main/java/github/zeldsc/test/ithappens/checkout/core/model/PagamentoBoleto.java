package github.zeldsc.test.ithappens.checkout.core.model;

import javax.persistence.Entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

@Entity
public class PagamentoBoleto extends Pagamento {

	@ApiModelProperty(accessMode = AccessMode.READ_ONLY)
	private Long codigoBarras;
	
	public Long getCodigoBarras() {
		return codigoBarras;
	}
	
	public void setCodigoBarras(Long codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
}
