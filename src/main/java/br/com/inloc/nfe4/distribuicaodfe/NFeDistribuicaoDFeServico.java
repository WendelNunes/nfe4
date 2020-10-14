package br.com.inloc.nfe4.distribuicaodfe;

import java.net.URL;
import java.util.List;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.inloc.nfe4.classes.Autorizador;
import br.com.inloc.nfe4.classes.Configuracao;
import br.com.inloc.nfe4.classes.ConfiguracaoGoyaco;
import br.com.inloc.nfe4.distribuicaodfe.DistDFeInt.ConsChNFe;
import br.com.inloc.nfe4.distribuicaodfe.DistDFeInt.ConsNSU;
import br.com.inloc.nfe4.distribuicaodfe.DistDFeInt.DistNSU;
import br.com.inloc.nfe4.distribuicaodfe.NFeDistribuicaoDFeStub.NfeDadosMsg_type0;
import br.com.inloc.nfe4.distribuicaodfe.NFeDistribuicaoDFeStub.NfeDistDFeInteresse;
import br.com.inloc.nfe4.distribuicaodfe.NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse;
import br.com.inloc.nfe4.distribuicaodfe.RetDistDFeInt.LoteDistDFeInt.DocZip;
import br.com.inloc.nfe4.util.CertificadoDigital;
import br.com.inloc.nfe4.util.Xml;

public class NFeDistribuicaoDFeServico {

	private final Configuracao configuracao;

	public NFeDistribuicaoDFeServico(Configuracao configuracao) {
		super();
		this.configuracao = configuracao;
	}

	public RetDistDFeInt consulta(String cpf, String cnpj, String chaveNFe, String nsu, String nsuUnico)
			throws Exception {
		CertificadoDigital.geraInformacoesCertificadoDigital(this.configuracao);
		URL url = new URL(Autorizador.obterPorUnidadeFederativa(this.configuracao.getUnidadeFederativa())
				.getConsultaDistribuicaoDFe(this.configuracao.getAmbiente()));
		ObjectFactory objectFactory = new ObjectFactory();
		DistDFeInt distDFeInt = objectFactory.createDistDFeInt();
		distDFeInt.setVersao("1.01");
		distDFeInt.setTpAmb(this.configuracao.getAmbiente().getId());
		distDFeInt.setCUFAutor(this.configuracao.getUnidadeFederativa().getCodigo());
		if (cpf != null && !cpf.isEmpty()) {
			distDFeInt.setCPF(cpf);
		} else {
			distDFeInt.setCNPJ(cnpj);
		}
		if (chaveNFe != null && !chaveNFe.isEmpty()) {
			ConsChNFe consChNFe = new DistDFeInt.ConsChNFe();
			consChNFe.setChNFe(chaveNFe);
			distDFeInt.setConsChNFe(consChNFe);
		} else if (nsu != null && !nsu.isEmpty()) {
			DistNSU distNSU = new DistDFeInt.DistNSU();
			distNSU.setUltNSU(nsu);
			distDFeInt.setDistNSU(distNSU);
		} else if (nsuUnico != null && !nsuUnico.isEmpty()) {
			ConsNSU consNSU = new DistDFeInt.ConsNSU();
			consNSU.setNSU(nsuUnico);
			distDFeInt.setConsNSU(consNSU);
		}
		OMElement ome = AXIOMUtil.stringToOM(distDFeInt.getXML());
		NfeDadosMsg_type0 nfeDadosMsg_type0 = new NFeDistribuicaoDFeStub.NfeDadosMsg_type0();
		nfeDadosMsg_type0.setExtraElement(ome);
		NfeDistDFeInteresse nfeDistDFeInteresse = new NFeDistribuicaoDFeStub.NfeDistDFeInteresse();
		nfeDistDFeInteresse.setNfeDadosMsg(nfeDadosMsg_type0);
		NFeDistribuicaoDFeStub nFeDistribuicaoDFeStub = new NFeDistribuicaoDFeStub(url.toString());
		NfeDistDFeInteresseResponse nfeDistDFeInteresseResponse = nFeDistribuicaoDFeStub
				.nfeDistDFeInteresse(nfeDistDFeInteresse);
		return RetDistDFeInt
				.xmlToObject(nfeDistDFeInteresseResponse.getNfeDistDFeInteresseResult().getExtraElement().toString());
	}

	public static void main(String[] args) {
		try {
			NFeDistribuicaoDFeServico nFeDistribuicaoDFeServico = new NFeDistribuicaoDFeServico(
					new ConfiguracaoGoyaco());
			RetDistDFeInt retDistDFeInt = nFeDistribuicaoDFeServico.consulta(null, "XXXXXXXXXXXXXX", null,
					"000000000000000", null);
			if (retDistDFeInt.getCStat().equals("138")) {
				System.out.println("CStat: " + retDistDFeInt.getCStat());
				System.out.println("XMotivo: " + retDistDFeInt.getXMotivo());
				System.out.println("MaxNSU: " + retDistDFeInt.getMaxNSU());
				System.out.println("UltNSU: " + retDistDFeInt.getUltNSU());
				List<DocZip> listaDoc = retDistDFeInt.getLoteDistDFeInt().getDocZip();
				for (DocZip docZip : listaDoc) {
					System.out.println("Schema: " + docZip.getSchema());
					System.out.println("NSU: " + docZip.getNSU());
					switch (docZip.getSchema()) {
					case "resNFe_v1.01.xsd":
						System.out.println(
								"Este é o XML em resumo, deve ser feito a Manifestação para o Objeter o XML Completo.");
						break;
					case "procNFe_v4.00.xsd":
						System.out.println("XML Completo.");
						break;
					case "procEventoNFe_v1.00.xsd":
						System.out.println("XML Evento.");
						break;
					}
					String xml = Xml.gZipToXml(docZip.getValue());
					System.out.println("XML: " + xml);
					System.out.println();
				}
			} else {
				System.out.println("Status: " + retDistDFeInt.getCStat() + " - " + retDistDFeInt.getXMotivo());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}