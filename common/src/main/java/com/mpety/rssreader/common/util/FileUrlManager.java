package com.mpety.rssreader.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileUrlManager implements UrlManager{
	
	public static final String FILE_PATH_KEY = "urlManager.path";
	public static final String DEFAULT_PATH = "urls.txt";
	
	private String path;
	private ArrayList<String> urlList;
	
	public FileUrlManager(String path){
		this.path = path;
	}
	
	public FileUrlManager(Properties props) {
		this.path = props.getProperty(FILE_PATH_KEY, DEFAULT_PATH);
	}

	@Override
	public List<String> loadUrls() {
		ArrayList<String> tempList = new ArrayList<String>();
		if(urlList != null){
			tempList.addAll(urlList);
		}
		return tempList;
	}

	@Override
	public void saveUrl(String url){  //TODO Mi van ha az URL már a listában van???
		urlList.add(url);
	}

	@Override
	public void open() {
		File f = new File(path);
		urlList = new ArrayList<String>(); //A listát üresen inicializáljuk
		if (!f.exists()){
			return; //mivel nem létezik a fájl üres listával indítunk...
		}
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(f));
			String line = null;
			
			while((line = reader.readLine()) != null){
				urlList.add(line);
			}
		} catch (FileNotFoundException e) {
			//ilyen itt nem lehetséges
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // a finally mindig lefut ha van catch(exception) ha nincs
			try {
				if(reader != null){
					reader.close();
				}
			} catch (IOException e) {} //megnyalhatjuk ha ide jön valami
		}
	}

	@Override
	public void close() {
		
		if(urlList == null || urlList.isEmpty()){
			return; //nincs mit kiírni, ha üres
		}
		
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(path); //megnyitotta amibe írhatunk, vagy csinál újat is ha nincs
			
			for(String s : urlList){
				writer.println(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) { //csak azért kellett hogy ne legyen még nullpointer is ha valami gebasz exception bejön
				writer.close();
			}
		}
	}

	@Override
	public void deleteUrl(String url) {
		urlList.remove(url);
	}

}
