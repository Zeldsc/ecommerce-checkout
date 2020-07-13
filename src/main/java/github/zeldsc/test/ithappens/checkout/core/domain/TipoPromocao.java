package github.zeldsc.test.ithappens.checkout.core.domain;

import java.util.Objects;

public enum TipoPromocao {

	PERCENTUAL_DESCONTO(0L,"Percentual de desconto"),
	VALOR_DESCONTO(1L,"Valor de desconto");
	
	private Long codigo;
	
	private String descricao;

	private TipoPromocao(Long codigo, String descricao) {
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
	
	public static TipoPromocao valueOf(Long codigo) {
		for(TipoPromocao type: TipoPromocao.values()) {
			if(Objects.equals(type.codigo, codigo)) 
				return type;
		}
		throw new IllegalArgumentException(String.format("Invalid codigo: %d", codigo));
	}
}
