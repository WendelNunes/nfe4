package br.com.inloc.nfe4.statusservico;

import java.net.URL;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.inloc.nfe4.classes.Autorizador;
import br.com.inloc.nfe4.classes.Configuracao;
import br.com.inloc.nfe4.classes.ConfiguracaoGoyaco;
import br.com.inloc.nfe4.statusservico.NFeStatusServico4Stub.NfeResultMsg;
import br.com.inloc.nfe4.util.CertificadoDigital;

public class NFeStatusServico {

	private final Configuracao configuracao;

	public NFeStatusServico(Configuracao configuracao) {
		super();
		this.configuracao = configuracao;
	}

	public TRetConsStatServ getStatus() throws Exception {
		CertificadoDigital.geraInformacoesCertificadoDigital(this.configuracao);
		URL url = new URL(Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa())
				.getNfeStatusServico(this.configuracao.getAmbiente()));
		ObjectFactory objectFactory = new ObjectFactory();
		TConsStatServ consStatServ = objectFactory.createTConsStatServ();
		consStatServ.setCUF(this.configuracao.getUnidadeFederativa().getCodigo());
		consStatServ.setTpAmb(this.configuracao.getAmbiente().getId());
		consStatServ.setVersao("4.00");
		consStatServ.setXServ("STATUS");
		OMElement ome = AXIOMUtil.stringToOM(consStatServ.getXML());
		NFeStatusServico4Stub.NfeDadosMsg dadosMsg = new NFeStatusServico4Stub.NfeDadosMsg();
		dadosMsg.setExtraElement(ome);
		NFeStatusServico4Stub nFeStatusServico4Stub = new NFeStatusServico4Stub(url.toString());
		NfeResultMsg nfeStatusServicoNF = nFeStatusServico4Stub.nfeStatusServicoNF(dadosMsg);
		return TRetConsStatServ.xmlToObject(nfeStatusServicoNF.getExtraElement().toString());
	}

	public static void main(String[] args) {
		try {
			NFeStatusServico nFeStatusServico = new NFeStatusServico(new ConfiguracaoGoyaco());
			TRetConsStatServ tRetConsStatServ = nFeStatusServico.getStatus();
			System.out.println(tRetConsStatServ.getCStat());
			System.out.println(tRetConsStatServ.getXMotivo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}