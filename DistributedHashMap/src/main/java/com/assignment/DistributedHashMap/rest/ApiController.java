package com.assignment.DistributedHashMap.rest;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.DistributedHashMap.DistributedHashMap;

@RestController
public class ApiController {

	DistributedHashMap distributedHashMap;
	
	public ApiController() throws IOException {
		distributedHashMap = new DistributedHashMap();
	}
	
    @RequestMapping("/get")
    public String get(@RequestParam(value="key", defaultValue="key") String key) {
    	return distributedHashMap.get(key);
    }
    
    @RequestMapping("/put")
    public void put(@RequestParam(value="key", defaultValue="key") String key, @RequestParam(value="value", defaultValue="value") String value) {
    	distributedHashMap.put(key,value);
    }
    
    @RequestMapping("/display")
    public String display() {
    	return distributedHashMap.toString();
    }
    
    @RequestMapping("/clear")
    public void clear() throws IOException {
    	distributedHashMap.clear();
    }
}
