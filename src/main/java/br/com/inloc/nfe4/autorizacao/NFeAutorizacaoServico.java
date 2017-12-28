package br.com.inloc.nfe4.autorizacao;

import br.com.inloc.nfe4.classes.Configuracao;
import br.com.inloc.nfe4.classes.ConfiguracaoJAO;

public class NFeAutorizacaoServico {

	private final Configuracao configuracao;

	public NFeAutorizacaoServico(Configuracao configuracao) {
		super();
		this.configuracao = configuracao;
	}

	public TRetEnviNFe autoriza(TNFe nfe) {
		return null;
	}

	public static void main(String[] args) {
		try {
			NFeAutorizacaoServico nFeAutorizacaoServico = new NFeAutorizacaoServico(new ConfiguracaoJAO());
			ObjectFactory objectFactory = new ObjectFactory();
			TNFe nfe = objectFactory.createTNFe();

			nFeAutorizacaoServico.autoriza(nfe);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}