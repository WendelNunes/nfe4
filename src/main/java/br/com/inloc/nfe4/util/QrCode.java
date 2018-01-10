package br.com.inloc.nfe4.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class QrCode {

	private static String cHashQRCode;

	public static String getCodeQRCode(String chave, String versao, String ambiente, String cpfCnpj, String dhEmi, String valorNF, String valorICMS, String digVal,
			String idToken, String CSC, String urlConsulta) {
		StringBuilder value = new StringBuilder();
		value.append("chNFe=").append(chave);
		value.append("&nVersao=").append(versao);
		value.append("&tpAmb=").append(ambiente);
		value.append((cpfCnpj == null | "".equals(cpfCnpj)) ? "" : "&cDest=" + cpfCnpj);
		value.append("&dhEmi=").append(getHexa(dhEmi));
		value.append("&vNF=").append(valorNF);
		value.append("&vICMS=").append(valorICMS);
		value.append("&digVal=").append(getHexa(digVal));
		value.append("&cIdToken=").append(idToken);
		cHashQRCode = getHexa(getHash(value.toString() + CSC, "SHA-1")).toUpperCase();
		StringBuilder ret = new StringBuilder();
		ret.append(urlConsulta).append("?");
		ret.append(value);
		ret.append("&cHashQRCode=").append(cHashQRCode);
		return ret.toString();
	}

	private static byte[] getHash(String valor, String algoritmo) {
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(valor.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	private static String getHexa(String valor) {
		return getHexa(valor.getBytes());
	}

	private static String getHexa(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
			int parteBaixa = bytes[i] & 0xf;
			if (parteAlta == 0) {
				s.append('0');
			}
			s.append(Integer.toHexString(parteAlta | parteBaixa));
		}
		return s.toString();
	}
}
