package com.mpety.rssreader.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mpety.rssreader.common.model.RssChannel;
import com.mpety.rssreader.common.model.RssItem;
import com.mpety.rssreader.test.RssProcessTest;

/**
 * Az xml feldolgozását végző osztály, itt lesznek felbontva az xml tag-ek és "eltárolva" változókban, majd listába szervezve.
 * {@link RssXmlHandler#startElement(String, String, String, Attributes)}, 
 * {@link RssXmlHandler#endElement(String, String, String)}, {@link RssXmlHandler#characters(char[], int, int)} metódusokat tartalmaz...
 * @author Peti
 *
 */

public class RssXmlHandler extends DefaultHandler {

	private static final Logger log = LoggerFactory.getLogger(RssProcessTest.class);

	private StringBuilder sb = new StringBuilder();
	private RssChannel rssChannel;
	private RssItem rssItem;

	private static final SimpleDateFormat date = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

	boolean channel = false;
	boolean item = false;
	boolean title = false;
	boolean pubDate = false;
	boolean lastBuildDate = false;
	boolean link = false;
	boolean description = false;
	boolean category = false;

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		log.debug("Start Element: {}", qName); // a {} rész egy un. placeholder,
												// azaz helykijelölő, oda fog
												// kerülni a qName értéke (mint
												// C-ben a %s és társaik)

		if (qName.equalsIgnoreCase("channel")) {
			channel = true;
			rssChannel = new RssChannel();
			// rssChannel.setItemList(null); //nem szükséges, mert alapból null lesz
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

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		log.debug("End Element: {}", qName);

		if (qName.equalsIgnoreCase("item")) {
			item = false;
			rssChannel.addItem(rssItem);
			// log.config("item: " + sb.toString());
		}

		if (qName.equalsIgnoreCase("title")) {
			title = false;
			log.info("title: {}", sb.toString());
			if (item) {
				rssItem.setTitle(sb.toString());
			} else {
				rssChannel.setTitle(sb.toString());
			}
		}

		if (qName.equalsIgnoreCase("link")) {
			link = false;
			log.info("link: {}", sb.toString());
			if (item) {
				rssItem.setLink(sb.toString());
			} else {
				rssChannel.setLink(sb.toString());
			}
		}

		if (qName.equalsIgnoreCase("description")) {
			description = false;
			log.info("description: {}", sb.toString());
			if (item) {
				rssItem.setDescription(sb.toString());
			} else {
				rssChannel.setDescription(sb.toString());
			}
		}

		if (qName.equalsIgnoreCase("category")) {
			category = false;
			log.info("category: {}", sb.toString());
			rssItem.setCategory(sb.toString()); // mivel csak az itemnek van
												// category eleme
		}

		if (qName.equalsIgnoreCase("pubDate")) {
			pubDate = false;
			log.info("pubDate: {}", sb.toString());
			try { // TODO ezeket is a saját kivételkezelővel kellene megoldani?
					// - ezt végig kell gondolni, egyelőre elég egy error log a
					// catch ágban
				rssItem.setPubDate(date.parse(sb.toString()));
			} catch (ParseException e) {
				log.error("Pubdate {} nem értelmezhető!", sb.toString());
			}
		}

		if (qName.equalsIgnoreCase("lastBuildDate")) {
			lastBuildDate = false;
			log.info("lastBuildDate: {}", sb.toString());
			try {
				rssChannel.setLastBuildDate(date.parse(sb.toString()));
			} catch (ParseException e) {
				log.error("LastBuildDate {} nem értelmezhető!", sb.toString());
			}
		}
		sb.setLength(0);
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {

		sb.append(ch, start, length);

	}

	public RssChannel getRssChannel() {
		return rssChannel;
	}
}
