package net.mdrjr.cactidroid;

import java.util.Properties;

public class GetServidores {
	
	public String[] getServers(Properties xml) {
		String a = xml.getProperty("servers");
		String servidores[] = a.split(",");
		return servidores;
	}

}
