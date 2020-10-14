package br.com.inloc.nfe4.distribuicaodfe;

import java.net.URL;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;

import br.com.inloc.nfe4.classes.Autorizador;
import br.com.inloc.nfe4.classes.Configuracao;
import br.com.inloc.nfe4.distribuicaodfe.DistDFeInt.ConsChNFe;
import br.com.inloc.nfe4.distribuicaodfe.DistDFeInt.ConsNSU;
import br.com.inloc.nfe4.distribuicaodfe.DistDFeInt.DistNSU;
import br.com.inloc.nfe4.distribuicaodfe.NFeDistribuicaoDFeStub.NfeDadosMsg_type0;
import br.com.inloc.nfe4.distribuicaodfe.NFeDistribuicaoDFeStub.NfeDistDFeInteresse;
import br.com.inloc.nfe4.distribuicaodfe.NFeDistribuicaoDFeStub.NfeDistDFeInteresseResponse;
import br.com.inloc.nfe4.util.CertificadoDigital;

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
}