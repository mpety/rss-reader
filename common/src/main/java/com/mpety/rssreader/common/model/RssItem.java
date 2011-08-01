package com.mpety.rssreader.common.model;

import java.util.Date;

/**
 * Az RSS-t tartalmazó XML fájl, item tag-ek között lévő adatainak osztályba szervezése (title, link, description, category, pubDate).
 * Csak getter és setter metódusai vannak.
 * @author Peti
 *
 */

public class RssItem {
	
	private String title;
	private String link; //Az adott itemhez tartozó (cikk) link
	private String description;
	private String category;
	private Date pubDate;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
}
