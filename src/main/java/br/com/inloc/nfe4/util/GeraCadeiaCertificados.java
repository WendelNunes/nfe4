package br.com.inloc.nfe4.util;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import br.com.inloc.nfe4.classes.Ambiente;
import br.com.inloc.nfe4.classes.Autorizador;

public class GeraCadeiaCertificados {

	private static final int PORT = 443;
	private static final String PROTOCOL = "TLS";
	private static final Logger LOGGER = Logger.getLogger(GeraCadeiaCertificados.class.getName());

	public static byte[] geraCadeiaCertificados(Ambiente ambiente, final String senha) throws Exception {
		final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
		keyStore.load(null, senha.toCharArray());
		try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			for (Autorizador autorizador : Autorizador.values()) {
				final String host = new URI(autorizador.getNfeStatusServico(ambiente)).getHost();
				GeraCadeiaCertificados.get(keyStore, host, PORT);

				final String hostNfce = new URI(autorizador.getNfceStatusServico(ambiente)).getHost();
				GeraCadeiaCertificados.get(keyStore, hostNfce, PORT);
			}
			keyStore.store(out, senha.toCharArray());
			return out.toByteArray();
		}
	}

	private static void get(final KeyStore keyStore, final String host, final int port) throws Exception {
		final TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(keyStore);

		final X509TrustManager defaultTrustManager = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];
		final SavingTrustManager savingTrustManager = new SavingTrustManager(defaultTrustManager);

		final SSLContext sslContext = SSLContext.getInstance(PROTOCOL);
		sslContext.init(null, new TrustManager[] { savingTrustManager }, null);

		LOGGER.info(String.format("Abrindo conexao para o servidor: %s:%s", host, port));
		try (SSLSocket sslSocket = (SSLSocket) sslContext.getSocketFactory().createSocket(host, port)) {
			sslSocket.setSoTimeout(10000);
			sslSocket.startHandshake();
		} catch (final Exception e) {
			LOGGER.severe(String.format("[%s] %s", host, e.toString()));
		}

		// se conseguir obter a cadeia de certificados, adiciona no keystore
		if (savingTrustManager.chain != null) {
			LOGGER.info(String.format("Certificados enviados pelo servidor: %s", savingTrustManager.chain.length));
			final MessageDigest sha1 = MessageDigest.getInstance("SHA1");
			final MessageDigest md5 = MessageDigest.getInstance("MD5");
			for (int i = 0; i < savingTrustManager.chain.length; i++) {
				final X509Certificate certificate = savingTrustManager.chain[i];
				sha1.update(certificate.getEncoded());
				md5.update(certificate.getEncoded());

				final String alias = String.format("%s.%s", host, i + 1);
				keyStore.setCertificateEntry(alias, certificate);
				LOGGER.info(String.format("Adicionado certificado no keystore com o alias: %s", alias));
			}
		}
	}

	private static class SavingTrustManager implements X509TrustManager {
		private final X509TrustManager trustManager;
		private X509Certificate[] chain;

		SavingTrustManager(final X509TrustManager trustManager) {
			this.trustManager = trustManager;
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return trustManager.getAcceptedIssuers();
		}

		@Override
		public void checkClientTrusted(final X509Certificate[] chain, final String authType)
				throws CertificateException {
			this.trustManager.checkClientTrusted(chain, authType);
		}

		@Override
		public void checkServerTrusted(final X509Certificate[] chain, final String authType)
				throws CertificateException {
			this.chain = chain;
			this.trustManager.checkServerTrusted(chain, authType);
		}
	}
}
