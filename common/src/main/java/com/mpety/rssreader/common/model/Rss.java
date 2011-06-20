package com.mpety.rssreader.common.model;

import java.util.Date;
import java.util.List;

public class Rss {
	
	private String source; //RSS forrása
	private Date pubDate;
	private String description;
	
	//private List<Item> itemList; //TODO item osztályt definiálni
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public Date getPubDate() {
		return pubDate;
	}
	
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pubDate == null) ? 0 : pubDate.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
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
		if (!(obj instanceof Rss)) {
			return false;
		}
		Rss other = (Rss) obj;
		if (pubDate == null) {
			if (other.pubDate != null) {
				return false;
			}
		} else if (!pubDate.equals(other.pubDate)) {
			return false;
		}
		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}
		return true;
	}
	
	
}
