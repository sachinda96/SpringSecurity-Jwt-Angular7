package com.example.demo.configure;

import java.security.Key;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class ReserveKey {

	private static HashMap<String, Key> keyList=new HashMap<String, Key>();

	public boolean addKey(String username,Key key) {
		if(!keyList.containsKey(username)) {
			keyList.put(username, key);
			return true;
		}
		return false;
	}
	
	public Key getKey(String username) {
		
		Key key=keyList.get(username);
		return key;
	}
	
	public boolean removeKey(String username) {
		if(keyList.containsKey(username)) {
			keyList.remove(username);
			return true;
		}
		return false;
	}
}
