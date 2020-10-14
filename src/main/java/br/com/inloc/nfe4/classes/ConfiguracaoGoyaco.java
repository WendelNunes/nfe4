package br.com.inloc.nfe4.classes;

public class ConfiguracaoGoyaco implements Configuracao {

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
		return "C:/INLOC/certificados/GOYACO.pfx";
	}

	@Override
	public String getSenhaCertificado() {
		return "";
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
		return this.getAmbiente().equals(Ambiente.HOMOLOGACAO) ? "" : "";
	}
}