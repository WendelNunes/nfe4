package br.com.inloc.nfe4.autorizacao;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMText;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.inloc.nfe4.autorizacao.NFeAutorizacao4Stub.NfeDadosMsg;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Det;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Det.Imposto;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Det.Imposto.COFINS.COFINSOutr;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Det.Imposto.ICMS.ICMSSN500;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Det.Imposto.PIS.PISOutr;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Det.Prod;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Emit;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Ide;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Pag;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Pag.DetPag;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Total;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Total.ICMSTot;
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFe.Transp;
import br.com.inloc.nfe4.classes.Autorizador;
import br.com.inloc.nfe4.classes.Configuracao;
import br.com.inloc.nfe4.classes.ConfiguracaoJAO;
import br.com.inloc.nfe4.util.AssinaturaDigital;
import br.com.inloc.nfe4.util.CertificadoDigital;
import br.com.inloc.nfe4.util.ChaveAcesso;
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
		URL url = new URL(Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa()).getNfceAutorizacao(this.configuracao.getAmbiente()));
		ObjectFactory objectFactory = new ObjectFactory();
		boolean nfce = nfe.stream().anyMatch(i -> i.getInfNFe().getIde().getMod().equals("65"));
		TEnviNFe tEnviNFe = objectFactory.createTEnviNFe();
		tEnviNFe.setIdLote(idLote);
		tEnviNFe.setIndSinc("1");
		tEnviNFe.setVersao("4.00");
		tEnviNFe.getNFe().addAll(nfe);
		String xml = tEnviNFe.getXML();
		xml = xml.replace("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");
		String xmlAssinado = new AssinaturaDigital().assinaEnviNFe(xml, this.configuracao.getUrlCertificado(), this.configuracao.getSenhaCertificado());
		OMElement ome;
		if (nfce) {
			tEnviNFe = TEnviNFe.xmlToObject(xmlAssinado);
			List<String> qrCodes = new ArrayList<String>();
			for (TNFe n : tEnviNFe.getNFe()) {
				qrCodes.add(QrCode.getCodeQRCode(n.getInfNFe().getId().substring(3), "100", this.configuracao.getAmbiente().getId(), n.getInfNFe().getDest() == null
						? null
						: n.getInfNFe().getDest().getCNPJ() != null ? n.getInfNFe().getDest().getCNPJ() : n.getInfNFe().getDest().getCPF(), n.getInfNFe().getIde()
						.getDhEmi(), n.getInfNFe().getTotal().getICMSTot().getVNF(), n.getInfNFe().getTotal().getICMSTot().getVICMS(), Base64.getEncoder()
						.encodeToString(n.getSignature().getSignedInfo().getReference().getDigestValue()), this.configuracao.getIdToken(), this.configuracao.getCSC(),
						Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa()).getNfceUrlQrcode(this.configuracao.getAmbiente())));
				n.setInfNFeSupl(objectFactory.createTNFeInfNFeSupl());
				n.getInfNFeSupl().setQrCode("");
				n.getInfNFeSupl().setUrlChave(
						Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa()).getNfceUrlQrcode(this.configuracao.getAmbiente()));
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
				((OMElement) nfeElements.get(i)).getChildrenWithLocalName("infNFeSupl").forEachRemaining(infNFeSuplElements::add);
				List<Object> qrCodeElements = new ArrayList<>();
				((OMElement) infNFeSuplElements.get(0)).getChildrenWithLocalName("qrCode").forEachRemaining(qrCodeElements::add);
				((OMElement) qrCodeElements.get(0)).addChild(omtQrcode);
				tEnviNFe.getNFe().get(i).getInfNFeSupl().setQrCode("<![CDATA[" + qrCodes.get(i) + "]]>");
			}
		} else {
			ome = AXIOMUtil.stringToOM(xmlAssinado);
		}
		NfeDadosMsg nfeDadosMsg = new NFeAutorizacao4Stub.NfeDadosMsg();
		nfeDadosMsg.setExtraElement(ome);
		NFeAutorizacao4Stub nFeAutorizacao4Stub = new NFeAutorizacao4Stub(url.toString());
		return TRetEnviNFe.xmlToObject(nFeAutorizacao4Stub.nfeAutorizacaoLote(nfeDadosMsg).getExtraElement().toString());
	}

	public static void main(String[] args) {
		try {
			ConfiguracaoJAO configuracaoJAO = new ConfiguracaoJAO();
			Date dataHora = new Date();
			String cnpjEmitente = "XXXXXXXXXXXXXX";
			String serie = "1";
			String numeroNotaFiscal = "11";
			String modelo = "65";
			String tipoEmissao = "1";
			String codigoRandomico = ChaveAcesso.geraCodigoRandomico(dataHora);
			String chaveAcessoSemDV = ChaveAcesso.geraChaveAcessoSemDV(configuracaoJAO.getUnidadeFederativa().getCodigo(), dataHora, null, cnpjEmitente, modelo, serie,
					numeroNotaFiscal, tipoEmissao, codigoRandomico);
			String digitoVerificador = ChaveAcesso.getDV(chaveAcessoSemDV).toString();
			String chave = ChaveAcesso.getChaveAcesso(chaveAcessoSemDV, digitoVerificador);
			String inscricaoEstadualEmitente = "XXXXXXXXX";
			String telefoneEmitente = "XXXXXXXXXX";
			NFeAutorizacaoServico nFeAutorizacaoServico = new NFeAutorizacaoServico(new ConfiguracaoJAO());
			ObjectFactory objectFactory = new ObjectFactory();
			TNFe nfe = objectFactory.createTNFe();
			InfNFe infNFe = objectFactory.createTNFeInfNFe();
			infNFe.setVersao("4.00");
			// IDENTIFICACAO
			Ide ide = objectFactory.createTNFeInfNFeIde();
			ide.setCUF(configuracaoJAO.getUnidadeFederativa().getCodigo());
			ide.setCNF(chave.substring(35, 43));
			ide.setNatOp("NFCE");
			ide.setMod(modelo);
			ide.setSerie(serie);
			ide.setNNF(numeroNotaFiscal);
			ide.setDhEmi(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(dataHora));
			ide.setTpNF("1");
			ide.setIdDest("1");
			ide.setCMunFG("5208707"); // GOIANIA
			ide.setTpImp("4");
			ide.setTpEmis(tipoEmissao);
			ide.setCDV(digitoVerificador);
			ide.setTpAmb(configuracaoJAO.getAmbiente().getId());
			ide.setFinNFe("1");
			ide.setIndFinal("1");
			ide.setIndPres("1");
			ide.setProcEmi("0");
			ide.setVerProc("1");
			infNFe.setIde(ide);
			// EMITENTE
			Emit emit = objectFactory.createTNFeInfNFeEmit();
			emit.setCNPJ(cnpjEmitente);
			emit.setXNome("JAO CONVENTION E RESTAURANTE LTDA");
			emit.setXFant("JAO CONVENTION");
			TEnderEmi enderEmi = objectFactory.createTEnderEmi();
			enderEmi.setXLgr("AV QUITANDINHA");
			enderEmi.setNro("600");
			enderEmi.setXBairro("JAO");
			enderEmi.setCMun("5208707");
			enderEmi.setXMun("GOIANIA");
			enderEmi.setUF(TUfEmi.GO);
			enderEmi.setCEP("74673060");
			enderEmi.setCPais("1058");
			enderEmi.setFone(telefoneEmitente);
			emit.setEnderEmit(enderEmi);
			emit.setIE(inscricaoEstadualEmitente);
			emit.setCRT("1");
			infNFe.setEmit(emit);
			// PRODUTOS
			Det det = objectFactory.createTNFeInfNFeDet();
			det.setNItem("1");
			Prod prod = objectFactory.createTNFeInfNFeDetProd();
			prod.setCProd("2050000002");
			prod.setXProd("NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
			prod.setCEAN("00000011111115");
			prod.setNCM("22021000");
			prod.setCEST("1709600");
			prod.setCFOP("5405");
			prod.setUCom("UN");
			prod.setQCom("1.0000");
			prod.setVUnCom("34.9900");
			prod.setVProd("34.99");
			prod.setUTrib("UN");
			prod.setCEANTrib("00000011111115");
			prod.setQTrib("1.0000");
			prod.setVUnTrib("34.9900");
			prod.setIndTot("1");
			det.setProd(prod);
			Imposto imposto = objectFactory.createTNFeInfNFeDetImposto();
			ICMS icms = objectFactory.createTNFeInfNFeDetImpostoICMS();
			ICMSSN500 icmssn500 = objectFactory.createTNFeInfNFeDetImpostoICMSICMSSN500();
			icmssn500.setOrig("0");
			icmssn500.setCSOSN("500");
			icms.setICMSSN500(icmssn500);
			imposto.getContent().add(objectFactory.createTNFeInfNFeDetImpostoICMS(icms));
			PIS pis = objectFactory.createTNFeInfNFeDetImpostoPIS();
			PISOutr pisOutr = objectFactory.createTNFeInfNFeDetImpostoPISPISOutr();
			pisOutr.setCST("99");
			pisOutr.setVBC("0.00");
			pisOutr.setPPIS("0.0000");
			pisOutr.setVPIS("0.00");
			pis.setPISOutr(pisOutr);
			imposto.getContent().add(objectFactory.createTNFeInfNFeDetImpostoPIS(pis));
			COFINS cofins = objectFactory.createTNFeInfNFeDetImpostoCOFINS();
			COFINSOutr cofinsOutr = objectFactory.createTNFeInfNFeDetImpostoCOFINSCOFINSOutr();
			cofinsOutr.setCST("99");
			cofinsOutr.setVBC("0.00");
			cofinsOutr.setPCOFINS("0.0000");
			cofinsOutr.setVCOFINS("0.00");
			cofins.setCOFINSOutr(cofinsOutr);
			imposto.getContent().add(objectFactory.createTNFeInfNFeDetImpostoCOFINS(cofins));
			det.setImposto(imposto);
			infNFe.getDet().add(det);
			// TOTAL
			Total total = objectFactory.createTNFeInfNFeTotal();
			ICMSTot icmsTot = objectFactory.createTNFeInfNFeTotalICMSTot();
			icmsTot.setVBC("0.00");
			icmsTot.setVICMS("0.00");
			icmsTot.setVICMSDeson("0.00");
			icmsTot.setVBCST("0.00");
			icmsTot.setVST("0.00");
			icmsTot.setVProd("34.99");
			icmsTot.setVFrete("0.00");
			icmsTot.setVSeg("0.00");
			icmsTot.setVDesc("0.00");
			icmsTot.setVII("0.00");
			icmsTot.setVIPI("0.00");
			icmsTot.setVPIS("0.00");
			icmsTot.setVCOFINS("0.00");
			icmsTot.setVOutro("0.00");
			icmsTot.setVNF("34.99");
			icmsTot.setVFCP("0.00");
			icmsTot.setVFCPST("0.00");
			icmsTot.setVFCPSTRet("0.00");
			icmsTot.setVIPIDevol("0.00");
			total.setICMSTot(icmsTot);
			infNFe.setTotal(total);
			// TRANSPORTE
			Transp transp = objectFactory.createTNFeInfNFeTransp();
			transp.setModFrete("9");
			infNFe.setTransp(transp);
			// PAGAMENTO
			Pag pag = objectFactory.createTNFeInfNFePag();
			DetPag detPag = objectFactory.createTNFeInfNFePagDetPag();
			detPag.setTPag("01");
			detPag.setVPag("34.99");
			pag.getDetPag().add(detPag);
			infNFe.setPag(pag);
			infNFe.setId("NFe" + chave);
			nfe.setInfNFe(infNFe);
			TRetEnviNFe retEnviNFe = nFeAutorizacaoServico.autoriza(Arrays.asList(nfe), "0000001");
			System.out.println(retEnviNFe.getCStat());
			System.out.println(retEnviNFe.getXMotivo());
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}