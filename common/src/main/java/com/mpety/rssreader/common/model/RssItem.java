package com.mpety.rssreader.common.model;

import java.util.Date;

/**
 * Az RSS-t tartalmazó XML fájl, item tag-ek között lévő adatainak osztályba szervezése (title, link, description, category, pubDate).
 * Csak getter és setter metódusai vannak (mindegyik adathoz).
 * @author Peti
 *
 */

public class RssItem {
	
	private String title;
	private String link; //Az adott itemhez tartozó (adott 'cikk'-re mutató) link
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RssItem)) {
			return false;
		}
		RssItem other = (RssItem) obj;
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}
}
