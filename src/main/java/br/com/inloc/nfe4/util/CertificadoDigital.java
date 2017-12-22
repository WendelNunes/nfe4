package br.com.inloc.nfe4.util;

import java.security.Security;

import br.com.inloc.nfe4.classes.Configuracao;

public class CertificadoDigital {

	public static void geraInformacoesCertificadoDigital(Configuracao configuracao) throws Exception {
		System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		System.setProperty("javax.net.ssl.keyStoreType", "PKCS12");
		System.clearProperty("javax.net.ssl.keyStore");
		System.clearProperty("javax.net.ssl.keyStorePassword");
		System.clearProperty("javax.net.ssl.trustStore");
		System.setProperty("javax.net.ssl.keyStore", configuracao.getUrlCertificado());
		System.setProperty("javax.net.ssl.keyStorePassword", configuracao.getSenhaCertificado());
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");
		System.setProperty("javax.net.ssl.trustStore", configuracao.getUrlCacerts());
	}
}
