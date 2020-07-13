package github.zeldsc.test.ithappens.checkout.core.domain;

public enum StatusPagamento {

	AGUARDANDO(1L, "AGUARDANDO"), 
	CONFIRMADO(2L, "CONFIRMADO"), 
	ESTORNADO(3L, "ESTORNADO"), 
	NEGADO(4L, "NEGADO"),
	CANCELADO(5L, "CANCELADO");

	private Long codigo;

	private String descricao;

	private StatusPagamento(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}
