package com.mpety.rssreader.common.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.mpety.rssreader.common.exception.RssException;
import com.mpety.rssreader.common.model.RssChannel;

public class RssParser {
	
	private static final Logger log = LoggerFactory.getLogger(RssParser.class);
	
	public static RssChannel parse(String xmlString) throws RssException{ //felfelé dobódik az exception 
																		  //és majd ahol meghívódik ez a metódus ott fogjuk lekezelni ezt az exepctiont
		
		if(xmlString == null || !xmlString.toLowerCase().contains("channel")){
			log.error("Xml string seems invalid!");
			log.info("{}", xmlString);
			throw new RssException("Invalid Xml String!", RssException.XML_ERROR); //belső feltétel miatt csinálunk saját exceptiont,
																				   //azért nincs throwable paramétere
		}
		
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
			RssException re = new RssException("Parsing error!", RssException.XML_ERROR , e);
			throw re;
		} catch (SAXException e) {
			RssException re = new RssException("Sax error!", RssException.XML_ERROR , e);
			throw re;
		} catch (IOException e) {
			RssException re = new RssException("IO error!", RssException.IO_ERROR , e);
			throw re;
		}
		
	}
}
