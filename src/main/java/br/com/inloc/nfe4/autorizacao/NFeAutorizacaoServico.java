package br.com.inloc.nfe4.autorizacao;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.apache.axiom.om.OMElement;
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
import br.com.inloc.nfe4.autorizacao.TNFe.InfNFeSupl;
import br.com.inloc.nfe4.classes.Autorizador;
import br.com.inloc.nfe4.classes.Configuracao;
import br.com.inloc.nfe4.classes.ConfiguracaoJAO;
import br.com.inloc.nfe4.util.AssinaturaDigital;
import br.com.inloc.nfe4.util.QrCode;

public class NFeAutorizacaoServico {

	private final Configuracao configuracao;

	public NFeAutorizacaoServico(Configuracao configuracao) {
		super();
		this.configuracao = configuracao;
	}

	public TRetEnviNFe autoriza(List<TNFe> nfe, String idLote) throws Exception {
		ObjectFactory objectFactory = new ObjectFactory();
		TEnviNFe tEnviNFe = objectFactory.createTEnviNFe();
		tEnviNFe.setIdLote(idLote);
		tEnviNFe.setIndSinc("1");
		tEnviNFe.setVersao("4.00");
		tEnviNFe.getNFe().addAll(nfe);
		tEnviNFe = TEnviNFe.xmlToObject(new AssinaturaDigital().assinaEnviNFe(tEnviNFe.getXML(), this.configuracao.getUrlCertificado(),
				this.configuracao.getSenhaCertificado()));
		for (TNFe n : tEnviNFe.getNFe()) {
			String qrCode = QrCode.getCodeQRCode(n.getInfNFe().getId().substring(3), "100", this.configuracao.getAmbiente().getId(), n.getInfNFe().getDest() == null
					? null
					: n.getInfNFe().getDest().getCNPJ() != null ? n.getInfNFe().getDest().getCNPJ() : n.getInfNFe().getDest().getCPF(),
					n.getInfNFe().getIde().getDhEmi(), n.getInfNFe().getTotal().getICMSTot().getVNF(), n.getInfNFe().getTotal().getICMSTot().getVICMS(), Base64
							.getEncoder().encodeToString(n.getSignature().getSignedInfo().getReference().getDigestValue()), this.configuracao.getIdToken(),
					this.configuracao.getCSC(),
					Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa()).getNfceUrlQrcode(this.configuracao.getAmbiente()));
			InfNFeSupl infNFeSupl = objectFactory.createTNFeInfNFeSupl();
			infNFeSupl.setQrCode(qrCode);
			n.setInfNFeSupl(infNFeSupl);
		}
		OMElement ome = AXIOMUtil.stringToOM(tEnviNFe.getXML());
		NfeDadosMsg nfeDadosMsg = new NFeAutorizacao4Stub.NfeDadosMsg();
		nfeDadosMsg.setExtraElement(ome);
		NFeAutorizacao4Stub nFeAutorizacao4Stub = new NFeAutorizacao4Stub(Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa())
				.getNfceAutorizacao(this.configuracao.getAmbiente()));
		return TRetEnviNFe.xmlToObject(nFeAutorizacao4Stub.nfeAutorizacaoLote(nfeDadosMsg).getExtraElement().toString());
	}

	public static void main(String[] args) {
		try {
			String cnpjEmitente = "03619219000161";
			String inscricaoEstadualEmitente = "103243976";
			String telefoneEmitente = "6235410790";
			String serie = "1";
			String numeroNotaFiscal = "11";
			String chaveNotaFiscal = "12967733";
			String digitoVerificador = "0";
			String id = "NFe52171203619219000161650010000000111129677330";

			ConfiguracaoJAO configuracaoJAO = new ConfiguracaoJAO();
			NFeAutorizacaoServico nFeAutorizacaoServico = new NFeAutorizacaoServico(new ConfiguracaoJAO());
			ObjectFactory objectFactory = new ObjectFactory();
			TNFe nfe = objectFactory.createTNFe();
			InfNFe infNFe = objectFactory.createTNFeInfNFe();

			// IDENTIFICACAO
			Ide ide = objectFactory.createTNFeInfNFeIde();
			ide.setCUF(configuracaoJAO.getUnidadeFederativa().getCodigo());
			ide.setCNF(chaveNotaFiscal);
			ide.setNatOp("NFCE");
			ide.setMod("65");
			ide.setSerie(serie);
			ide.setNNF(numeroNotaFiscal);
			ide.setDhEmi("2017-12-29T16:24:28-02:00");
			ide.setTpNF("1");
			ide.setIdDest("1");
			ide.setCMunFG("5208707"); // GOIANIA
			ide.setTpImp("4");
			ide.setTpEmis("1");
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

			Prod prod = objectFactory.createTNFeInfNFeDetProd();
			prod.setCProd("2050000002");
			prod.setXProd("NOTA FISCAL EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
			prod.setNCM("21069005");
			prod.setCEST("1709600");
			prod.setCFOP("5405");
			prod.setUCom("UN");
			prod.setQCom("1.0000");
			prod.setVUnCom("34.9900");
			prod.setVProd("34.99");
			prod.setUTrib("UN");
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
			infNFe.setId(id);

			nfe.setInfNFe(infNFe);
			nFeAutorizacaoServico.autoriza(Arrays.asList(nfe), "0000001");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}