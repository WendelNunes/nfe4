package br.com.inloc.nfe4.classes;

public enum Ambiente {

	PRODUCAO("1"), HOMOLOGACAO("2");

	private String id;

	private Ambiente(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
