package com.mpety.rssreader.common.util;

import java.util.List;

import com.mpety.rssreader.common.exception.RssException;
import com.mpety.rssreader.common.model.RssChannel;

public interface RssManager {
	
	/**
	 * Letölti a legutolsó RSS feedet a megadott url-ről, és visszaadja feed-et egy {@link RssChannel} objektumban.
	 * Az eredmeny objektum tartalmazza az eddigi változatokat is (ergo csak bővíti az itemList-et a channel objektumban).
	 * 
	 * @param url a forrás url
	 * @return a frissített RssChannel objektum
	 */
	RssChannel loadRss(String url) throws RssException; 
	
	List<RssChannel> loadRssList(List<String> urlList); //ugyanaz mint a fenti csak a lista minden elemére végrehajtja.

}
