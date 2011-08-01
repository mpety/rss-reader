package com.mpety.rssreader.test; //TODO Ennek az egész csomagnak a tartalmát csak meghagytam magamnak, (RssLoadTest, RssProcessTest)
								  //hogy lássam egészbe működni, meg console-ról tesztelni tudjam!
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mpety.rssreader.common.model.RssChannel;
import com.mpety.rssreader.common.model.RssItem;

public class RssProcessTest {
	
	private static final Logger log = Logger.getLogger(RssProcessTest.class.getName());
	
	public static void main(String[] args) {

		try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {

				StringBuilder sb = new StringBuilder();
				RssChannel rssChannel;
				RssItem rssItem;
				
				SimpleDateFormat date = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH); //TODO nah ezt hogy is kell kívülre írni vagy public static-nak? mindkettőt sejtem de nem biztos hogy jól...
				
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

					log.fine("Start Element :" + qName);
					
						if (qName.equalsIgnoreCase("channel")) {
							channel = true;
							rssChannel = new RssChannel();
							rssChannel.setItemList(null); //TODO ez lehet nem is muszáj ide ugye?
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

					log.fine("End Element :" + qName);

						if (qName.equalsIgnoreCase("item")) {
							item = false;
							rssChannel.addItem(rssItem);
							//log.config("item: " + sb.toString());
						}

						if (qName.equalsIgnoreCase("title")) {
							title = false;
							log.config("title: " + sb.toString());
							if(item){
								rssItem.setTitle(sb.toString());
							}
							else{
								rssChannel.setTitle(sb.toString());
							}
						}

						if (qName.equalsIgnoreCase("link")) {
							link = false;
							log.config("link: " + sb.toString());
							if(item){
								rssItem.setLink(sb.toString());
							}
							else{
								rssChannel.setLink(sb.toString());
							}
						}

						if (qName.equalsIgnoreCase("description")) {
							description = false;
							log.config("description: " + sb.toString());
							if(item){
								rssItem.setDescription(sb.toString());
							}
							else{
								rssChannel.setDescription(sb.toString());
							}
							//System.out.println();					//TODO ez nem tudom miért kellett, sztem csak a kiíratás miatt, de gondolom kitörölhetem már, vagy lesz jelentősége, hogy ide kellene egy új sor? - gondolom nem...
						}

						if (qName.equalsIgnoreCase("category")) {
							category = false;
							log.config("category: " + sb.toString());
							rssItem.setCategory(sb.toString()); //TODO mivel csak az itemnek van ilyen eleme ezért így csináltam - ezeket a todo-imat majd kitörölgetheted, ha jó amit írok, vagy átírhatod sima kommentre :D
						}
						
						if (qName.equalsIgnoreCase("pubDate")) {
							pubDate = false;
							log.config("pubDate: " + sb.toString());
							try { //TODO ezeket is a saját kivételkezelővel kellene megoldani?
								rssItem.setPubDate(date.parse(sb.toString()));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						if (qName.equalsIgnoreCase("lastBuildDate")) {
							lastBuildDate = false;
							log.config("lastBuildDate: " + sb.toString());
							try {
								rssChannel.setLastBuildDate(date.parse(sb.toString()));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
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

			Reader reader = new StringReader(RssLoadTest.read(RssLoadTest.getURL()));
			InputSource is = new InputSource(reader);
			// is.setEncoding("UTF-8");
			saxParser.parse(is, handler);
			
			System.out.println("vége"); //--------------------------------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}