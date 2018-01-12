package br.com.inloc.nfe4.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class ChaveAcesso {

	public static String geraCodigoRandomico(Date dataHora) {
		final Random random = new Random(dataHora.getTime());
		return StringUtils.leftPad(String.valueOf(random.nextInt(100000000)), 8, "0");
	}

	public static String getChaveAcesso(String chaveAcessoSemDV, String digitoVerificador) {
		return String.format("%s%s", chaveAcessoSemDV, digitoVerificador);
	}

	public static Integer getDV(String chaveAcessoSemDV) {
		final char[] valores = chaveAcessoSemDV.toCharArray();
		final int[] valoresInt = {2, 3, 4, 5, 6, 7, 8, 9};
		int indice = 0;
		int soma = 0;
		int valorTemp;
		int multTemp;
		for (int i = valores.length; i > 0; i--) {
			if (indice >= valoresInt.length) {
				indice = 0;
			}

			valorTemp = Integer.parseInt(String.valueOf(valores[i - 1]));
			multTemp = valoresInt[indice++];
			soma += valorTemp * multTemp;
		}
		final int dv = 11 - (soma % 11);
		return ((dv == 11) || (dv == 10)) ? 0 : dv;
	}

	public static String geraChaveAcessoSemDV(String ufCodigoIBGE, Date dataHora, String cpf, String cnpj, String modelo, String serie, String numeroNota,
			String tipoEmissao, String codigoRandomico) {
		return StringUtils.leftPad(ufCodigoIBGE, 2, "0") //
				+ StringUtils.leftPad(new SimpleDateFormat("yyMM").format(dataHora), 4, "0") //
				+ StringUtils.leftPad(cnpj == null ? cpf : cnpj, 14, "0") //
				+ StringUtils.leftPad(modelo, 2, "0") //
				+ StringUtils.leftPad(serie, 3, "0") //
				+ StringUtils.leftPad(numeroNota, 9, "0") //
				+ StringUtils.leftPad(tipoEmissao, 1, "0") //
				+ StringUtils.leftPad(codigoRandomico, 8, "0");
	}
}
