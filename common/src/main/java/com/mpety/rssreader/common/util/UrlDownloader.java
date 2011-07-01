package com.mpety.rssreader.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlDownloader { 
	//TODO elolvasni: kellene ennek egy getURL metódus mint az RssLoadTest.java -ban is?
	//nem kell, az RssLoadTest-ben csak azért kellett, mert console-ról akartuk beolvasni
	//Akkor ez hogy fogja megkapni az URL-t?
	
	public String download(String www) throws Exception{ //TODO ezt a kivételkezelést még magyarázni kell!
		
		URL url = new URL(www);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine = in.readLine();
		
		String xmlString = "";
     while ((inputLine = in.readLine()) != null) {
             xmlString = xmlString + inputLine;
     }
     in.close();
     
     return xmlString;
     
	}
}
