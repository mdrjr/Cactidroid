package net.mdrjr.cactidroid;

import java.util.Properties;

public class GetServerRes {

	public String[] getRes(String host, Properties xml) {
		
		String a = xml.getProperty(host + "-res");
		String res[] = a.split(",");
		return res;
		
	}
	
}
