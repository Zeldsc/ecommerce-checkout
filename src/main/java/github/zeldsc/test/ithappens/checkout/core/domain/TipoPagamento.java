package github.zeldsc.test.ithappens.checkout.core.domain;

import java.util.Objects;

public enum TipoPagamento {

	PAYMENT_SLIP(0L,"Boleto Bancário"),
	CREDIT_CARD(1L,"Cartão de Crédito");
	
	private Long codigo;
	
	private String descricao;

	private TipoPagamento(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
	
	public static TipoPagamento valueOf(Long codigo) {
		for(TipoPagamento payment: TipoPagamento.values()) {
			if(Objects.equals(payment.codigo, codigo)) 
				return payment;
		}
		throw new IllegalArgumentException(String.format("Invalid codigo: %d", codigo));
	}

}