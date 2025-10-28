package com.ZidioIntern.Service;

import org.springframework.stereotype.Service;

@Service
public class chatSupportService {
	
	public void logConnection(String message) {
		System.out.println("WebSocket Log:"+message);
	}

}
