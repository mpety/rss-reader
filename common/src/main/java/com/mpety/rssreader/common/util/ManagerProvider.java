package com.mpety.rssreader.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManagerProvider {

	public static final String URL_MANAGER = "urlManager";
	public static final String RSS_MANAGER = "rssManager";

	private static final Logger log = LoggerFactory.getLogger(ManagerProvider.class);

	private static final ManagerProvider instance = new ManagerProvider();

	private Properties props = new Properties();
	private Map<String, Object> managers = new HashMap<String, Object>(); //gyakorlatilag egy cache ahol tároljuk a manager objektumokat, így nem kell minden kérésnél újra létrehozni

	
	/**
	 * Konstruktor ami betölti a beállításokat az rss-reader.properties-ből
	 */
	private ManagerProvider() {
		try {
			InputStream is = ManagerProvider.class.getClassLoader().getResourceAsStream("rss-reader.properties"); //Az rss-reader.properties-ben van hogy melyik manager implementációt használjuk
			props.load(is);
		} catch (IOException e) {
			log.warn("Cannot load rss-reader.properties!", e);
		}
	}

	
	/**
	 * Szolgáltatja a megadott manager névhez tartozó implementációt
	 * @param name manager neve
	 * @return manager objektum
	 */
	public Object getManager(final String name) {

		Object service = managers.get(name);

		if (service == null) {
			String serviceClassName = props.getProperty(name);
			if (serviceClassName == null) {
				log.error("Property not exists {}", name);
				return null;
			}
			try {
				Class<?> serviceClass = Class.forName(serviceClassName);
				Constructor<?> serviceConstructor;
				Object[] initArgs = new Object[]{};
				try {
					serviceConstructor = serviceClass.getConstructor();
				} catch (NoSuchMethodException e) {
					serviceConstructor = serviceClass.getConstructor(Properties.class);
					initArgs = new Object[]{props};
				}
				service = serviceConstructor.newInstance(initArgs);
			} catch (Exception e) {
				log.error("Can't create service for class: {}", serviceClassName, e);
			}
			managers.put(name, service);
		}

		return service;
	}
	
	public static ManagerProvider getInstance() {
		return instance;
	}

	public static UrlManager getUrlManagerInstance() {
		return (UrlManager)instance.getManager(URL_MANAGER);
	}

	public static RssManager getRssManagerInstance() {
		return (RssManager)instance.getManager(RSS_MANAGER);
	}

}
