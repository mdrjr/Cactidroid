package net.mdrjr.cactidroid;

import java.util.Properties;

import android.graphics.Bitmap;

public class GetGraph {

	public Bitmap getGraph(String host, String opt, Properties xml) {
		String graph = null;
		graph = xml.getProperty(host + "-" + opt);
		GetFileGraph gfg = new GetFileGraph();
		Bitmap b = gfg.getGraphbyGraphBMP(graph);
		
		return b;
	}
	
}
