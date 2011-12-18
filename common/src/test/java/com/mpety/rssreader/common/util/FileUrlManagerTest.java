package com.mpety.rssreader.common.util;

import java.io.File;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FileUrlManagerTest {
	
	private FileUrlManager manager;
	
	private String indexhu = "http://index.hu/";
	private String sghu = "http://sg.hu";
	private String dummy = "dummy string";
	
	private String[] examples = {indexhu, sghu, dummy}; 
	
	@Parameters({"test-url-file"})
	@BeforeMethod
	public void setUp(String testFileName) {
		manager = new FileUrlManager(testFileName);
		manager.open();
	}
	
	@Test
	public void loadFromEmptyFile() {
		assert manager.loadUrls().isEmpty() : "Az elején nem üres az url lista!";
		
	}
	
	@Test
	public void saveUrlsAndLoadBackInSameSession() {
		manager.saveUrl(indexhu);
		
		assert manager.loadUrls().size() == 1 : "Nem csak az van az url listában amit beletettünk!";
		assert manager.loadUrls().contains(indexhu) : "Nem az van benne amit beleraktunk!";
	}
	
	@Test
	@Parameters({"test-url-file"})
	public void saveUrlsAndLoadBackBetweenSessions(String testFileName) {
		manager.saveUrl(indexhu);
		
		manager.close();
		
		assert new File(testFileName).exists() : "A mentett lista nem létezik close() után!";
		
		manager = new FileUrlManager(testFileName);
		manager.open();
		
		assert manager.loadUrls().size() == 1 : "Nem csak az van az url listában amit beletettünk!";
		assert manager.loadUrls().contains(indexhu) : "Nem az van benne amit beleraktunk!";
	}
	
	@Test
	public void deleteUrlWithinSameSession() {
		assert manager.loadUrls().isEmpty() : "Az elején nem üres az url lista!";
		
		manager.saveUrl(indexhu);
		
		assert manager.loadUrls().size() == 1 : "Nem csak az van az url listában amit beletettünk!";
		assert manager.loadUrls().contains(indexhu) : "Nem az van benne amit beleraktunk!";
		
		manager.deleteUrl(indexhu);
		
		assert manager.loadUrls().size() == 0 : "Nem csak az van az url listában amit beletettünk!";
		assert !manager.loadUrls().contains(indexhu) : "Még mindig benne van hiába töröltük ki!";
	}
	
	@Test
	@Parameters({"test-url-file"})
	public void deleteUrlBetweenSessions(String testFileName) {
		for (String s : examples) {
			manager.saveUrl(s);
		}
		
		assert manager.loadUrls().size() == examples.length : "Nem csak az van az url listában amit beletettünk!";
		
		for (String s : examples) {
			assert manager.loadUrls().contains(s) : "Az " + s + " hiányzik, pedig beleraktuk!";
		}
		
		manager.deleteUrl(examples[0]);
		
		assert !manager.loadUrls().contains(examples[0]) : "Az " + examples[0] + " még mindig benne van, pedig töröltük";
		
		manager.close();
		
		assert new File(testFileName).exists() : "A mentett lista nem létezik close() után!";
		
		manager = new FileUrlManager(testFileName);
		manager.open();
		
		for (int i = 0; i < examples.length; i++) {
			if (i == 0) {
				assert !manager.loadUrls().contains(examples[i]) : "Az " + examples[i] + " még mindig benne van, pedig töröltük";
			} 
			else {
				assert manager.loadUrls().contains(examples[i]) : "Az " + examples[i] + " hiányzik, pedig beleraktuk!";
			}
		}
		
	}
	
	@Parameters({"test-url-file"})
	@AfterMethod
	public void tearDown(String testFileName) {
		manager.close();
		new File(testFileName).delete();
	}

}
