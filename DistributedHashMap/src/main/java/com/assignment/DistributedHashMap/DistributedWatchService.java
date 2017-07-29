package com.assignment.DistributedHashMap;

import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DistributedWatchService extends Thread{
	private WatchService watchService;
	private DistributedProperties distributedProperties;
	private boolean stop;
	public DistributedWatchService(DistributedProperties properties) throws IOException {
		watchService = FileSystems.getDefault().newWatchService();
		distributedProperties = properties;
		stop = false;
	}
	
	public void run() {
		try {
			Path directory = FileSystems.getDefault().getPath(System.getProperty(DistributedProperties.USER_HOME), "");
			directory.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		    while (!stop) {
		        final WatchKey watchKey = watchService.take();
		        for (WatchEvent<?> event : watchKey.pollEvents()) {
		            final Path changed = (Path) event.context();
		            if (changed.endsWith(DistributedProperties.PERSISTENT_PROPERTIES)) 
		            	distributedProperties.load();
		        }
		        if (!watchKey.reset()) 
		            System.out.println("Key has been unregistered");
		    }
		}
		catch(ClosedWatchServiceException e) {
			System.out.println("Exit success");	
		}
		catch (Exception e) {
			System.out.println("Exception in watch service");
			e.printStackTrace();
		}
	}

	public void shutdown() throws IOException {
		watchService.close();
		stop = true;
	}
}
