package com.assignment.DistributedHashMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.assignment.DistributedHashMap.DistributedHashMap;

public class DistributedHashMapSpecification {

	private static final int waitTime = 800;
	private DistributedHashMap client1;
	private DistributedHashMap client2;
	private List<DistributedHashMap> clients;
	
	@After
	public void tearDown() throws IOException {
		if(client1!=null) { client1.clear(); client1.close(); }
		if(client2!=null) { client2.clear(); client2.close(); }
		if(clients!=null)
			for(DistributedHashMap client: clients) {
				client.clear();
				client.close();
			}
	}

	@Test
	public void test2KVPairFor2Clients() throws IOException, InterruptedException {
		client1 = new DistributedHashMap();
		client2 = new DistributedHashMap();
		Thread.sleep(waitTime);
		
		//act
		client1.put("key1","value1");
		Thread.sleep(waitTime);
		
		client2.put("key2","value2");
		Thread.sleep(waitTime);
		
		//assert
		Assert.assertNotNull(client1.get("key2"));
		Assert.assertNotNull(client2.get("key1"));
		Assert.assertTrue(client1.get("key2").equals("value2"));
		Assert.assertTrue(client2.get("key1").equals("value1"));
	}

	@Test
	public void testMultiplePairsfor2Clients() throws IOException, InterruptedException {
		//assign
		client1 = new DistributedHashMap();
		client2 = new DistributedHashMap();
		Thread.sleep(waitTime);
		
		//act
		for(int i=0; i<5; i++) {
			client1.put("key" + i,"value" + i);
			Thread.sleep(waitTime);
		}
		
		//assert
		for(int i=0; i<5; i++) {
			Assert.assertNotNull(client1.get("key"+i));
			Assert.assertTrue(client1.get("key"+i).equals("value"+i));
			Assert.assertNotNull(client2.get("key"+i));
			Assert.assertTrue(client2.get("key"+i).equals("value"+i));
		}
	}
	
	@Test
	public void testMultiplePairsforMultipleClients() throws IOException, InterruptedException {
		clients = new ArrayList<DistributedHashMap>();
		for(int i=0; i<5; i++)
			clients.add(new DistributedHashMap());

		Thread.sleep(waitTime);
		
		//act
		int i=0;
		for(DistributedHashMap client: clients) {
				client.put("key" + i,"value" + i);
				Thread.sleep(waitTime);
				i++;
		}
		
		//assert
		for(DistributedHashMap client: clients)
			for(i=0; i<5; i++) {
				Assert.assertNotNull(client.get("key"+i));
				Assert.assertTrue(client.get("key"+i).equals("value"+i));
			}
	}
}
