package br.com.inloc.nfe4.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class Xml {

	public static String gZipToXml(byte[] conteudo) throws IOException {
		if (conteudo == null || conteudo.length == 0) {
			return "";
		}
		GZIPInputStream gis;
		gis = new GZIPInputStream(new ByteArrayInputStream(conteudo));
		BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		StringBuilder outStr = new StringBuilder();
		String line;
		while ((line = bf.readLine()) != null) {
			outStr.append(line);
		}

		return outStr.toString();
	}
}
