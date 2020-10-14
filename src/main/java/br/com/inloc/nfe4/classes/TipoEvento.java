package br.com.inloc.nfe4.classes;

public enum TipoEvento {

	CONFIRMACAO_OPERACAO("210200", "Confirmacao da Operacao"), //
	CIENCIA_EMISSAO("210210", "Ciencia da Emissao"), //
	DESCONHECIMENTO_OPERACAO("210220", "Desconhecimento da Operacao"), //
	OPERACAO_NAO_REALIZADA("210240", "Operacao nao Realizada"); //

	private String id;
	private String descricao;

	private TipoEvento(String id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
}