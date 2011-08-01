package com.mpety.rssreader.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.mpety.rssreader.common.exception.RssException;

public class UrlDownloader {
	
	/**
	 * Letölti a megadott url-en lévő tartalmat.
	 * 
	 * @param www - ez az URL
	 * @return az URL-en lévő tartalom
	 * @throws RssException saját kivételkezelő által kezelt kivételek: WRONG_URL v. IO_ERROR 
	 */
	public static String download(String www) throws RssException {

		String xmlString;
		
		try {
			URL url = new URL(www);
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;

			xmlString = "";
			while ((inputLine = in.readLine()) != null) {
				xmlString = xmlString + inputLine;
			}
			
			in.close();
			return xmlString;
			
		} catch (MalformedURLException e) {
			RssException re = new RssException("URL is incorrect!", RssException.WRONG_URL, e);
			throw re;
		} catch (IOException e) {
			RssException re = new RssException("Unexpected IO exception!", RssException.IO_ERROR, e);
			throw re;
		}
	}
}
