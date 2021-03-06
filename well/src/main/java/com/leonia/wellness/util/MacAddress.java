package com.leonia.wellness.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class MacAddress {
	public static String getMACAddress() throws Exception {
		String ipAddress = Ipaddress.getIpAddress();
	    String str = ""; 
	    String macAddress = "";
	    try { 
	        Process p = Runtime.getRuntime().exec("nbtstat -A " + ipAddress); 
	        InputStreamReader ir = new InputStreamReader(p.getInputStream()); 
	        LineNumberReader input = new LineNumberReader(ir); 
	        for (int i = 1; i <100; i++) { 
	            str = input.readLine(); 
	            if (str != null) { 
		            if (str.indexOf("MAC Address") > 1) { 
		               macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length()); 
		               break; 
		            } 
	            } 
	        } 
	    } catch (IOException e) { 
	        e.printStackTrace(System.out); 
	    }
	    return macAddress;
	}
}
