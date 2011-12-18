package com.mpety.rssreader.common.util;

import org.testng.annotations.Test;

public class ManagerProviderTest {
	
	@Test
	public void getUrlManager() {
		UrlManager manager = ManagerProvider.getUrlManagerInstance();
		
		assert (manager instanceof UrlManager) : "nem UrlManager!";
	}
	
	@Test
	public void getRssManager() {
		RssManager manager = ManagerProvider.getRssManagerInstance();
		
		assert (manager instanceof RssManager) : "nem RssManager!";
	}

}
