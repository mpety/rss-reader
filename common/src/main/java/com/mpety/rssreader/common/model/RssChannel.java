package com.mpety.rssreader.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Az RSS-t tartalmazó XML fájl, channel tag-ek között lévő adatainak osztályba szervezése, magába foglalja az RssItem-eket is,
 * ezeket egy listában tárolja.
 * Getter és setter metódusai vannak a channel adataihoz (title, description, link, lastBuildDate, source), valamint az RssItem 
 * listájához is, ehhez egy addItem metódussal lehet új item-eket hozzáadni.
 * Ezeken kívül van egy equals(Object obj) és egy hashCode() metódusa is.
 * @author Peti
 *
 */

public class RssChannel {
	
	private String title;
	private String description;
	private String link; //Az RSS-hez tartozó oldal linkje
	private Date lastBuildDate;
	private String source; //RSS forrása
	
	private List<RssItem> itemList;
	
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

	//itt azért nem úgy csináljuk mint a FileUrlManagernél mert akkor a lista elemeit is klónozni kellene, 
	//figyelni kell hogy a getItemList által visszaadott listán nem szabad módosítani
	public List<RssItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<RssItem> itemList) {
		this.itemList = itemList;
	}

	public void addItem(RssItem item) {
		if (itemList == null) {
			itemList = new ArrayList<RssItem>();
		}
		itemList.add(item);
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
