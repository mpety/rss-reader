package com.mpety.rssreader.common.util;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mpety.rssreader.common.exception.RssException;

public class UrlDownloaderTest {

	private String testUrl;
	private String incorrectUrl;
	private String nonExistingUrl;
	
	@BeforeTest
	public void setUp() {
		testUrl = "http://index.hu/";
		incorrectUrl = "httx://blahblah.bla";
		nonExistingUrl = "http://nowhere.com";
	}

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
	
	/**
	 * Az {@link UrlDownloader#download(String)} metódus {@link RssException} hibával
	 * tér-e vissza, ha az url nem megfelelő
	 * @throws Exception
	 */
	@Test(expectedExceptions = {RssException.class})
	public void incorrectUrl() throws Exception {
		UrlDownloader.download(incorrectUrl);
	}
	
	/**
	 * Az {@link UrlDownloader#download(String)} metódus {@link RssException} hibával
	 * tér-e vissza, ha az url nem megfelelő, illetve a hibakód megfelelő-e (WRONG_URL)
	 * @throws Exception
	 */
	@Test(expectedExceptions = {RssException.class})
	public void wrongUrlErroCode() throws Exception {
		try {
			UrlDownloader.download(incorrectUrl);
		} catch (RssException e) {
			assert e.getErrorCode() == RssException.WRONG_URL : "Az errorCode nem WRONG_URL!";
			throw e;
		}
	}
	
	/**
	 * Az {@link UrlDownloader#download(String)} metódus {@link RssException} hibával
	 * tér-e vissza, ha az url nem elérhető
	 * @throws Exception
	 */
	@Test(expectedExceptions = {RssException.class})
	public void notExistingUrl() throws Exception {
		UrlDownloader.download(nonExistingUrl);
	}
	
	/**
	 * Az {@link UrlDownloader#download(String)} metódus {@link RssException} hibával
	 * tér-e vissza, ha az url nem elérhető, illetve a hibakód megfelelő-e (IO_ERROR)
	 * @throws Exception
	 */
	@Test(expectedExceptions = {RssException.class})
	public void ioErrorErrorCode() throws Exception {
		try {
			UrlDownloader.download(nonExistingUrl);
		} catch (RssException e) {
			assert e.getErrorCode() == RssException.IO_ERROR : "Az errorCode nem IO_ERROR!";
			throw e;
		}
	}
}
