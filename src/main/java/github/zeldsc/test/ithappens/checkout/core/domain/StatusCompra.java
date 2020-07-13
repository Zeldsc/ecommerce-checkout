package github.zeldsc.test.ithappens.checkout.core.domain;

import java.util.Objects;

public enum StatusCompra {
	
	EXPECTATIVA_COMPRA(0L,"Compra iniciada"),
	AGUARDANDO_PAGAMENTO(1L,"Aguardando Pagamento"),
	PAGAMENTO_CONFIRMADO(2L, "Pagamento Confirmado"),
	EM_SEPARACAO(3L, "Produtos em Separação"),
	ENVIADO(4L, "Postado"),
	FINALIZADO(5L, "Entregue");
	
	private Long codigo;
	
	private String descricao;

	private StatusCompra(Long codigo, String descricao) {
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
	
	public static StatusCompra valueOf(Long codigo) {
		for(StatusCompra status: StatusCompra.values()) {
			if(Objects.equals(status.codigo, codigo)) 
				return status;
		}
		throw new IllegalArgumentException(String.format("Invalid codigo: %d", codigo));
	}

}
