package br.com.inloc.nfe4.classes;

public enum Autorizador {

	GO(UnidadeFederativa.GO) {
		@Override
		public String getNfeStatusServico(Ambiente ambiente) {
			return ambiente.equals(Ambiente.HOMOLOGACAO)
					? "https://homolog.sefaz.go.gov.br/nfe/services/NFeStatusServico4?wsdl"
					: "https://nfe.sefaz.go.gov.br/nfe/services/NFeStatusServico4?wsdl";
		};

		@Override
		public String getNfceStatusServico(Ambiente ambiente) {
			return ambiente.equals(Ambiente.HOMOLOGACAO)
					? "https://homolog.sefaz.go.gov.br/nfe/services/NFeStatusServico4?wsdl"
					: "https://nfe.sefaz.go.gov.br/nfe/services/NFeStatusServico4?wsdl";
		}

		@Override
		public String getNfeConsultaProtocolo(Ambiente ambiente) {
			return ambiente.equals(Ambiente.HOMOLOGACAO)
					? "https://homolog.sefaz.go.gov.br/nfe/services/NFeConsultaProtocolo4?wsdl"
					: "https://nfe.sefaz.go.gov.br/nfe/services/NFeConsultaProtocolo4?wsdl";
		}

		@Override
		public String getNfceConsultaProtocolo(Ambiente ambiente) {
			return ambiente.equals(Ambiente.HOMOLOGACAO)
					? "https://homolog.sefaz.go.gov.br/nfe/services/NFeConsultaProtocolo4?wsdl"
					: "https://nfe.sefaz.go.gov.br/nfe/services/NFeConsultaProtocolo4?wsdl";
		}

		@Override
		public String getNfeAutorizacao(Ambiente ambiente) {
			return ambiente.equals(Ambiente.HOMOLOGACAO)
					? "https://homolog.sefaz.go.gov.br/nfe/services/NFeAutorizacao4?wsdl"
					: "https://nfe.sefaz.go.gov.br/nfe/services/NFeAutorizacao4?wsdl";
		}

		@Override
		public String getNfceAutorizacao(Ambiente ambiente) {
			return ambiente.equals(Ambiente.HOMOLOGACAO)
					? "https://homolog.sefaz.go.gov.br/nfe/services/NFeAutorizacao4?wsdl"
					: "https://nfe.sefaz.go.gov.br/nfe/services/NFeAutorizacao4?wsdl";
		}
	};

	private UnidadeFederativa unidadeFederativa;

	private Autorizador(UnidadeFederativa unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}

	public UnidadeFederativa getUnidadeFederativa() {
		return unidadeFederativa;
	}

	public abstract String getNfeStatusServico(Ambiente ambiente);

	public abstract String getNfceStatusServico(Ambiente ambiente);

	public abstract String getNfeConsultaProtocolo(Ambiente ambiente);

	public abstract String getNfceConsultaProtocolo(Ambiente ambiente);

	public abstract String getNfeAutorizacao(Ambiente ambiente);

	public abstract String getNfceAutorizacao(Ambiente ambiente);

	public static Autorizador obterPorUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
		for (Autorizador a : values()) {
			if (a.getUnidadeFederativa().equals(unidadeFederativa)) {
				return a;
			}
		}
		return null;
	}
}