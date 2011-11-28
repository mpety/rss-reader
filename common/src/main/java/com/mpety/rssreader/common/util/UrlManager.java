package com.mpety.rssreader.common.util;

import java.util.List;

public interface UrlManager {
	
	/**
	 * Betölti az adatbázisból az összes lementett Url-t.
	 * @return Url-ek listája.
	 */
	public List<String> loadUrls();
	
	
	public void saveUrl (String url); //elmenti az URL-t, a duplikáltakat nem adja hozzá
	public void deleteUrl (String url); //kitörli a megadott url-t a fájlból;
	public void open(); //betölti a konstruktorban megadott fájlból az url-eket
	public void close(); //elmenti a konstruktorban megadott fájlba az url-eket
	
}
