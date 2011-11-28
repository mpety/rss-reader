package com.mpety.rssreader.common.util;

import java.util.HashMap;
import java.util.List;

import com.mpety.rssreader.common.exception.RssException;
import com.mpety.rssreader.common.model.RssChannel;
import com.mpety.rssreader.common.model.RssItem;

public class RssManagerImpl implements RssManager {
	
	private HashMap<String, RssChannel> rssFeeds = new HashMap<String, RssChannel>(); //a mentett rss feedek tárolója

	@Override
	public RssChannel loadRss(String url) throws RssException {
		String rssXml = UrlDownloader.download(url);
		RssChannel currentRss = RssParser.parse(rssXml);
		
		RssChannel oldRss = rssFeeds.get(url);
		
		if(oldRss == null){
			rssFeeds.put(url, currentRss);
		}
		else {
			merge(oldRss, currentRss);
			currentRss = oldRss;
		}
		
		return currentRss;
	}

	private void merge(RssChannel oldRss, RssChannel currentRss) {
		List<RssItem> oldRssItems = oldRss.getItemList();
		List<RssItem> currentRssItems = currentRss.getItemList();
		
		for(RssItem item : currentRssItems){
			if(!oldRssItems.contains(item)){
				oldRss.addItem(item);
			}
		}
		oldRss.setLastBuildDate(currentRss.getLastBuildDate());
	}

	@Override
	public List<RssChannel> loadRssList(List<String> urlList) {
		// TODO Auto-generated method stub
		return null;
	}
}
