package com.mpety.rssreader.common.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssXmlHandler extends DefaultHandler {

	private boolean item = false;
	private boolean title = false;
	private boolean pubDate = false;
	private boolean lastBuildDate = false;
	private boolean link = false;
	private boolean description = false;
	private boolean category = false;

	public void startElement(String uri, String localName,
			String qName, Attributes attributes)
			throws SAXException {

		//System.out.println("Start Element :" + qName);

			if (qName.equalsIgnoreCase("item")) {
				item = true;
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
	}

	public void endElement(String uri, String localName,
			String qName) throws SAXException {

		//System.out.println("End Element :" + qName);

			if (qName.equalsIgnoreCase("item")) {
				item = false;
			}

			if (qName.equalsIgnoreCase("title")) {
				title = false;
			}

			if (qName.equalsIgnoreCase("pubDate")) {
				pubDate = false;
			}
			
			if (qName.equalsIgnoreCase("lastBuildDate")) {
				lastBuildDate = true;
			}

			if (qName.equalsIgnoreCase("link")) {
				link = false;
			}

			if (qName.equalsIgnoreCase("description")) {
				description = false;
				System.out.println();
			}

			if (qName.equalsIgnoreCase("category")) {
				category = false;
			}
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {

		// System.out.println(new String(ch, start, length) + "/n");

		if (item) {
			System.out.println("\n");
			item = false;
		}

		if (title) {
			System.out.println(new String(ch, start, length));
			title = false;
		}

		if (pubDate) {
			System.out.println(new String(ch, start, length));
			pubDate = false;
		}
		
		if(lastBuildDate){
			System.out.println(new String(ch, start, length));
			lastBuildDate = false;
		}

		if (link) {
			System.out.println(new String(ch, start, length));
			link = false;
		}

		if (description) {
			System.out.print(new String(ch, start, length));
			/*
			 * for(int i = 0; i < length; i++){ if (ch[i] == '<' ||
			 * ch[i] == '>'){ System.out.println("mi a picsa?"); } }
			 */
			description = false;
		}

		if (category) {
			System.out.println(new String(ch, start, length));
			category = false;
		}
	}
}
