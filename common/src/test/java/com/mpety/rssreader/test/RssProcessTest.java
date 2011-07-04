package com.mpety.rssreader.test; //TODO Ennek az egész csomagnak a tartalmát csak meghagytam magamnak, (RssLoadTest, RssProcessTest)
								  //hogy lássam egészbe működni, meg console-ról tesztelni tudjam!
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mpety.rssreader.common.model.RssChannel;
import com.mpety.rssreader.common.model.RssItem;

public class RssProcessTest {

	public static void main(String[] args) {

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				StringBuilder sb = new StringBuilder();
				RssChannel rssChannel;
				RssItem rssItem;
				boolean channel = false;
				boolean item = false;
				boolean title = false;
				boolean pubDate = false;
				boolean lastBuildDate = false;
				boolean link = false;
				boolean description = false;
				boolean category = false;

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

					//System.out.println("Start Element :" + qName);
					
						if (qName.equalsIgnoreCase("channel")) {
							channel = true;
							rssChannel = new RssChannel();
						}

						if (qName.equalsIgnoreCase("item")) {
							item = true;
							channel = false;
							rssItem = new RssItem();
						}

						if (qName.equalsIgnoreCase("title")) {
							title = true;
						}

						if (qName.equalsIgnoreCase("pubDate")) {
							pubDate = true;
						}
						
						if (qName.equalsIgnoreCase("lastBuildDate")) {
							lastBuildDate = true;
						}

						if (qName.equalsIgnoreCase("link")) {
							link = true;
						}

						if (qName.equalsIgnoreCase("description")) {
							description = true;
						}

						if (qName.equalsIgnoreCase("category")) {
							category = true;
						}
						sb.setLength(0);
				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {

					//System.out.println("End Element :" + qName);

						if (qName.equalsIgnoreCase("item")) {
							item = false;
							//System.out.println("item: " + sb.toString());
						}

						if (qName.equalsIgnoreCase("title")) {
							title = false;
							System.out.println("title: " + sb.toString());
							if(item){
								rssItem.setTitle(sb.toString());
							}
							else{
								rssChannel.setTitle(sb.toString());
							}
						}

						if (qName.equalsIgnoreCase("pubDate")) {
							pubDate = false;
						}
						
						if (qName.equalsIgnoreCase("lastBuildDate")) {
							lastBuildDate = false;
						}

						if (qName.equalsIgnoreCase("link")) {
							link = false;
						}

						if (qName.equalsIgnoreCase("description")) {
							description = false;
							//System.out.println();					//-----------------------------
						}

						if (qName.equalsIgnoreCase("category")) {
							category = false;
						}
						sb.setLength(0);
				}

				public void characters(char ch[], int start, int length)
						throws SAXException {
					
					sb.append(ch, start, length);

				}

			};

			/*
			 * File file = new File("d:\\file.xml"); InputStream inputStream=
			 * new FileInputStream(file); Reader reader = new
			 * InputStreamReader(inputStream,"UTF-8");
			 * 
			 * InputSource is = new InputSource(reader);
			 * is.setEncoding("UTF-8");
			 */

			// mz kódja

			Reader reader = new StringReader(RssLoadTest.read(RssLoadTest.getURL()));
			InputSource is = new InputSource(reader);
			// is.setEncoding("UTF-8");
			saxParser.parse(is, handler);
			
			System.out.println("vége");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}