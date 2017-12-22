package br.com.inloc.nfe4.classes;

public enum UnidadeFederativa {

	GO("52");

	private String codigo;

	private UnidadeFederativa(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
}
