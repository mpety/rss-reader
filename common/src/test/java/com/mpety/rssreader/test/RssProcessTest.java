package com.mpety.rssreader.test; //TODO Ennek az egész csomagnak a tartalmát csak meghagytam magamnak, (RssLoadTest, RssProcessTest)
								  //hogy lássam egészbe működni, meg console-ról tesztelni tudjam! jgooodies ui
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mpety.rssreader.common.model.RssChannel;
import com.mpety.rssreader.common.util.RssParser;

public class RssProcessTest {
	
	//private static final Logger log = LoggerFactory.getLogger(RssProcessTest.class);
	
	public static void main(String[] args) {

		try {
			RssChannel channel = RssParser.parse(RssLoadTest.read(RssLoadTest.getURL()));
			
			System.out.println(channel.getTitle());
			
			System.out.println("vége");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}