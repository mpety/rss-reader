package com.mpety.rssreader.common.util;

public class ManagerProvider {
	
	private static final UrlManager urlManagerInstance = new FileUrlManager("urls.txt");
	private static final RssManager rssManagerInstance = new RssManagerImpl();
	
	public static UrlManager getUrlManagerInstance(){
		return urlManagerInstance;
	}
	public static RssManager getRssManagerInstance(){
		return rssManagerInstance;
	}

}
