package br.com.inloc.nfe4.classes;

import java.io.Serializable;

public class ConfiguracaoJAO implements Configuracao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5084267168144766284L;

	@Override
	public UnidadeFederativa getUnidadeFederativa() {
		return UnidadeFederativa.GO;
	}

	@Override
	public Ambiente getAmbiente() {
		return Ambiente.HOMOLOGACAO;
	}

	@Override
	public String getUrlCertificado() {
		return "C:/INLOC/certificados/JAO.pfx";
	}

	@Override
	public String getSenhaCertificado() {
		return "2b3p8ojul1D";
	}

	@Override
	public String getUrlCacerts() {
		return "homologacao.cacerts";
	}

	@Override
	public String getIdToken() {
		return this.getAmbiente().equals(Ambiente.HOMOLOGACAO) ? "000001" : "";
	}

	@Override
	public String getCSC() {
		return this.getAmbiente().equals(Ambiente.HOMOLOGACAO) ? "a7b9f6e0bcda9816" : "";
	}
}