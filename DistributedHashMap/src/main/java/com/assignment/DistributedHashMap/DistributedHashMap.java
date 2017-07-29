package com.assignment.DistributedHashMap;

import java.io.IOException;

public class DistributedHashMap {
	private DistributedProperties distributedProperties;
	private DistributedWatchService distributedWatchService;
	
	public DistributedHashMap() throws IOException {
		distributedProperties = new DistributedProperties();
		distributedWatchService = new DistributedWatchService(distributedProperties);
		distributedWatchService.start();
	}
	
	public void put(String key, String value) {
		distributedProperties.put(key, value);
	}

	public String get(String key) {
		return distributedProperties.get(key);
	}
	
	public void clear() throws IOException {
		distributedProperties.clear();
	}
	
	public String toString() {
		return distributedProperties.toString();
	}
	
	public void close () throws IOException {
		distributedWatchService.shutdown();
	}
}
