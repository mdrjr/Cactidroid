package net.mdrjr.cactidroid;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class GetXMLConfig {

	public Properties getXML() {
		URL u;
		InputStream is = null;		
		final Properties xml = new Properties();

		try {
			u = new URL("http://full.path.to.your/config.xml");
			is = u.openStream();
			xml.loadFromXML(is);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return xml;
	}
}
