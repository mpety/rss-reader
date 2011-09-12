package com.mpety.rssreader.common.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.mpety.rssreader.common.model.RssChannel;

public class RssParser {
	
	public static RssChannel parse(String xmlString){
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			Reader reader = new StringReader(xmlString);
			InputSource is = new InputSource(reader);
			// is.setEncoding("UTF-8");
			RssXmlHandler handler = new RssXmlHandler();
			saxParser.parse(is, handler);
			
			return handler.getRssChannel();
			
		} catch (ParserConfigurationException e) {
			// TODO saját exception xmlerror
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO
			e.printStackTrace();
		} catch (IOException e) {
			// TODO
			e.printStackTrace();
		}
		return null; //TODO kitörölni a catch ágak kitöltése után
	}
}
