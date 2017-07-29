package com.assignment.DistributedHashMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

public class DistributedProperties {
	private Path path;
	private Properties cache;
	public static final String PERSISTENT_PROPERTIES = "persistent.properties";
	public static final String USER_HOME = "user.home";
	
	public DistributedProperties() throws IOException {
		path = FileSystems.getDefault().getPath(System.getProperty(USER_HOME), PERSISTENT_PROPERTIES);
		cache = new Properties();
		if(path.toFile().exists())
			load();
		else 
			path.toFile().createNewFile();
	}

	public synchronized void store() {
		try {
			cache.store(new FileOutputStream(path.toFile()), "Updating file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public synchronized void load() throws IOException {
		try {
			cache.load(new FileInputStream(path.toFile()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void put(String key, String value) {
		cache.put(key, value);
		store();
	}

	public synchronized String get(String key) {
		return (String) cache.get(key);
	}

	public synchronized void clear() throws IOException {
		cache.clear();
		store();
	}
	
	public synchronized String toString() {
		return cache.toString();
	}
}
