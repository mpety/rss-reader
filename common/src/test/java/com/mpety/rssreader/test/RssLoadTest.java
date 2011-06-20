package com.mpety.rssreader.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RssLoadTest {

	/**
	 * @param args
	 */
	
	public static String getURL() {
		   
		   String lineURL = "";
		   BufferedReader reader = new BufferedReader(new InputStreamReader (System.in));
		   
		   System.out.print("Adj meg egy RSS feed-et: ");
		   try {  
			   
			   lineURL = reader.readLine();
			   
		   }
		   catch(IOException e){ }
		   
		return lineURL;
	}
	
	public static String read(String xml) throws Exception {

		/*if (args.length != 1) {
			System.out.println("The first parameter should be an url!");
			System.exit(-1);
		}*/

		URL url = new URL(xml);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine = in.readLine(); //?
		
		//return inputLine;
		
		String xmlString = "";
        while ((inputLine = in.readLine()) != null) {
                xmlString = xmlString + inputLine;
        }
        in.close();
        
        return xmlString;

		/*while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();*/
		
		//ez miért kell? ez létrehoz egy ilyen típusú objektumot és annak vesszük a metódusát?
		
		/*RSSProcessTest processzalo = new RSSProcessTest();
		processzalo.process(inputLine);*/

	}	
}