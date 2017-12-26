package br.com.inloc.nfe4.classes;

import java.security.KeyStore;

public interface Configuracao {

	public UnidadeFederativa getUnidadeFederativa();

	public Ambiente getAmbiente();

	public String getUrlCertificado();

	public String getSenhaCertificado();

	public String getUrlCacerts();
}
