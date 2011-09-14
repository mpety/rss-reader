package com.mpety.rssreader.test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpety.rssreader.common.exception.RssException;
import com.mpety.rssreader.common.model.RssChannel;
import com.mpety.rssreader.common.util.RssParser;
import com.mpety.rssreader.common.util.UrlDownloader;

/**
 * 
 * Ez az osztály csak a tesztelés megkönnyítése miatt van itt nekem, összefogni a projektet!
 * Csak egy main metódust tartalmaz, futtatásra!
 * @author Peti
 *
 */

public class RssProcessTest {
	
	private static final Logger log = LoggerFactory.getLogger(RssProcessTest.class);
	
	public static void main(String[] args) {

		try {
			RssChannel channel = RssParser.parse(UrlDownloader.download(RssLoadTest.getURL()));
			
			System.out.println(channel.getTitle());
			
			System.out.println("vége");
		} catch (RssException e) {
			log.error("Ó jajj!", e);
		}

	}
}