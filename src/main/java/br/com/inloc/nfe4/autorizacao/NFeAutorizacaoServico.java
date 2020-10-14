package br.com.inloc.nfe4.autorizacao;

import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMText;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.inloc.nfe4.autorizacao.NFeAutorizacao4Stub.NfeDadosMsg;
import br.com.inloc.nfe4.classes.Autorizador;
import br.com.inloc.nfe4.classes.Configuracao;
import br.com.inloc.nfe4.util.AssinaturaDigital;
import br.com.inloc.nfe4.util.CertificadoDigital;
import br.com.inloc.nfe4.util.QrCode;

public class NFeAutorizacaoServico {

	private final Configuracao configuracao;

	public NFeAutorizacaoServico(Configuracao configuracao) {
		super();
		this.configuracao = configuracao;
	}

	@SuppressWarnings("unchecked")
	public TRetEnviNFe autoriza(List<TNFe> nfe, String idLote) throws Exception {
		CertificadoDigital.geraInformacoesCertificadoDigital(this.configuracao);
		URL url = new URL(Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa())
				.getNfceAutorizacao(this.configuracao.getAmbiente()));
		ObjectFactory objectFactory = new ObjectFactory();
		boolean nfce = nfe.stream().anyMatch(i -> i.getInfNFe().getIde().getMod().equals("65"));
		TEnviNFe tEnviNFe = objectFactory.createTEnviNFe();
		tEnviNFe.setIdLote(idLote);
		tEnviNFe.setIndSinc("1");
		tEnviNFe.setVersao("4.00");
		tEnviNFe.getNFe().addAll(nfe);
		String xml = tEnviNFe.getXML();
		xml = xml.replace("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		String xmlAssinado = new AssinaturaDigital().assinaEnviNFe(xml, this.configuracao.getUrlCertificado(),
				this.configuracao.getSenhaCertificado());
		OMElement ome;
		if (nfce) {
			tEnviNFe = TEnviNFe.xmlToObject(xmlAssinado);
			List<String> qrCodes = new ArrayList<String>();
			for (TNFe n : tEnviNFe.getNFe()) {
				qrCodes.add(QrCode.getCodeQRCode(n.getInfNFe().getId().substring(3), "100",
						this.configuracao.getAmbiente().getId(),
						n.getInfNFe().getDest() == null ? null
								: n.getInfNFe().getDest().getCNPJ() != null ? n.getInfNFe().getDest().getCNPJ()
										: n.getInfNFe().getDest().getCPF(),
						n.getInfNFe().getIde().getDhEmi(), n.getInfNFe().getTotal().getICMSTot().getVNF(),
						n.getInfNFe().getTotal().getICMSTot().getVICMS(),
						Base64.getEncoder()
								.encodeToString(n.getSignature().getSignedInfo().getReference().getDigestValue()),
						this.configuracao.getIdToken(), this.configuracao.getCSC(),
						Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa())
								.getNfceUrlQrcode(this.configuracao.getAmbiente())));
				n.setInfNFeSupl(objectFactory.createTNFeInfNFeSupl());
				n.getInfNFeSupl().setQrCode("");
				n.getInfNFeSupl()
						.setUrlChave(Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa())
								.getNfceUrlQrcode(this.configuracao.getAmbiente()));
			}
			xml = tEnviNFe.getXML();
			xml = xml.replace("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
			xml = xml.replace("<ns2:Signature>", "<Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\">");
			xml = xml.replace("<ns2:", "<");
			xml = xml.replace("</ns2:", "</");
			ome = AXIOMUtil.stringToOM(xml);
			List<Object> nfeElements = new ArrayList<>();
			ome.getChildrenWithLocalName("NFe").forEachRemaining(nfeElements::add);
			OMFactory omf = OMAbstractFactory.getOMFactory();
			for (int i = 0; i < tEnviNFe.getNFe().size(); i++) {
				OMText omtQrcode = omf.createOMText(qrCodes.get(i), OMElement.CDATA_SECTION_NODE);
				List<Object> infNFeSuplElements = new ArrayList<>();
				((OMElement) nfeElements.get(i)).getChildrenWithLocalName("infNFeSupl")
						.forEachRemaining(infNFeSuplElements::add);
				List<Object> qrCodeElements = new ArrayList<>();
				((OMElement) infNFeSuplElements.get(0)).getChildrenWithLocalName("qrCode")
						.forEachRemaining(qrCodeElements::add);
				((OMElement) qrCodeElements.get(0)).addChild(omtQrcode);
				tEnviNFe.getNFe().get(i).getInfNFeSupl().setQrCode("<![CDATA[" + qrCodes.get(i) + "]]>");
			}
		} else {
			ome = AXIOMUtil.stringToOM(xmlAssinado);
		}
		NfeDadosMsg nfeDadosMsg = new NFeAutorizacao4Stub.NfeDadosMsg();
		nfeDadosMsg.setExtraElement(ome);
		NFeAutorizacao4Stub nFeAutorizacao4Stub = new NFeAutorizacao4Stub(url.toString());
		return TRetEnviNFe
				.xmlToObject(nFeAutorizacao4Stub.nfeAutorizacaoLote(nfeDadosMsg).getExtraElement().toString());
	}
}