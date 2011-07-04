package com.mpety.rssreader.common.util;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UrlDownloaderTest {

	private String testUrl;
	
	@BeforeTest
	public void setUp() {
		testUrl = "http://index.hu/";
	}
	
	/*
	 *TODO Megjegyzések:
	 * - a download metódus lehetne static
	 * - StringBuilder használata
	 * - kódolás?
	 */
	
	/**
	 * Az {@link UrlDownloader#download(String)} metódus tesztelése
	 * (Megjegyzés: felerészben az internetkapcsolat meglétét teszteli,
	 *  így nem az igazi)
	 */
	@Test
	public void download() throws Exception {
		
		String content = UrlDownloader.download(testUrl);
		
		assert content.contains("<title>Index</title>") : "Index címlap nem található!";
	}
}
