package com.mpety.rssreader.common.model;

import java.util.Date;
import java.util.List;

public class RssChannel {
	
	private String title;
	private String description;
	private String link; //Az RSS-hez tartozó oldal linkje
	private Date lastBuildDate;
	private String source; //RSS forrása
	
	private List<RssItem> itemList; //TODO item osztályt definiálni
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
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

	public Date getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(Date lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public List<RssItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<RssItem> itemList) {
		this.itemList = itemList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lastBuildDate == null) ? 0 : lastBuildDate.hashCode());
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
		if (!(obj instanceof RssChannel)) {
			return false;
		}
		RssChannel other = (RssChannel) obj;
		if (lastBuildDate == null) {
			if (other.lastBuildDate != null) {
				return false;
			}
		} else if (!lastBuildDate.equals(other.lastBuildDate)) {
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
