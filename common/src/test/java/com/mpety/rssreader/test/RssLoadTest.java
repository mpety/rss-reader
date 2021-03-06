package com.mpety.rssreader.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Van egy getURL() és egy read() (static)metódusa, amik segítségével beolvashatunk egy tetszőleges URL-en lévő RSS-t egy String-be.
 * Ezt az osztályt csak a saját tesztelésre használom.
 * @author Peti
 *
 */
public class RssLoadTest{
	
	private static final String RssURL = "http://www.sg.hu/plain/rss.xml";

	/**
	 * 
	 * @return visszatér azzal az URL-el, amit megadtunk neki vagy - ha nem adunk meg semmit - az RssURL értékével
	 */
	public static String getURL() {
		   
		   String lineURL = "";
		   BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		   
		   System.out.print("Adj meg egy RSS feed-et: ");
		   try {  
			   
			   lineURL = reader.readLine();
			   
		   }
		   catch(IOException e){ }
		   
		   if(lineURL.equals("")){
			   return RssURL;
		   }
		   else{
			   return lineURL;
		   }
	}
	
	/**
	 * 
	 * @param vár egy xml-t tartalmazó (Rss-feed) URL-t
	 * @return visszatér egy String-el, amiben a megkapott URL-en lévő xml(RSS) tartalma van - soronként beolvasva
	 * @throws Exception
	 */
	public static String read(String xml) throws Exception {

		URL url = new URL(xml);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		
		String xmlString = "";
        while ((inputLine = in.readLine()) != null) {
                xmlString = xmlString + inputLine;
        }
        in.close();
        
        return xmlString;

	}	
}