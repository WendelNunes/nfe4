package br.com.inloc.nfe4.consultaprotocolo;

import java.net.URL;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.inloc.nfe4.classes.Autorizador;
import br.com.inloc.nfe4.classes.Configuracao;
import br.com.inloc.nfe4.classes.ConfiguracaoJAO;
import br.com.inloc.nfe4.consultaprotocolo.NFeConsultaProtocolo4Stub.NfeResultMsg;
import br.com.inloc.nfe4.util.CertificadoDigital;

public class NFeConsultaProtocoloServico {

	private final Configuracao configuracao;

	public NFeConsultaProtocoloServico(Configuracao configuracao) {
		super();
		this.configuracao = configuracao;
	}

	public TRetConsSitNFe getSituacao(String chaveNFe) throws Exception {
		CertificadoDigital.geraInformacoesCertificadoDigital(this.configuracao);
		URL url = new URL(Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa()).getNfeConsultaProtocolo(this.configuracao.getAmbiente()));
		ObjectFactory objectFactory = new ObjectFactory();
		TConsSitNFe tConsSitNFe = objectFactory.createTConsSitNFe();
		tConsSitNFe.setChNFe(chaveNFe);
		tConsSitNFe.setTpAmb(this.configuracao.getAmbiente().getId());
		tConsSitNFe.setVersao("4.00");
		tConsSitNFe.setXServ("CONSULTAR");
		OMElement ome = AXIOMUtil.stringToOM(tConsSitNFe.getXML());
		NFeConsultaProtocolo4Stub.NfeDadosMsg dadosMsg = new NFeConsultaProtocolo4Stub.NfeDadosMsg();
		dadosMsg.setExtraElement(ome);
		NFeConsultaProtocolo4Stub nFeConsultaProtocolo4Stub = new NFeConsultaProtocolo4Stub(url.toString());
		NfeResultMsg nfeResultMsg = nFeConsultaProtocolo4Stub.nfeConsultaNF(dadosMsg);
		return TRetConsSitNFe.xmlToObject(nfeResultMsg.getExtraElement().toString());
	}

	public static void main(String[] args) {
		try {
			NFeConsultaProtocoloServico nFeConsultaProtocoloServico = new NFeConsultaProtocoloServico(new ConfiguracaoJAO());
			TRetConsSitNFe tRetConsSitNFe = nFeConsultaProtocoloServico.getSituacao("52171202662774000103650020000000551249315547");
			System.out.println(tRetConsSitNFe.getCStat());
			System.out.println(tRetConsSitNFe.getXMotivo());
			System.out.println(tRetConsSitNFe.getProtNFe().getInfProt().getNProt());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}