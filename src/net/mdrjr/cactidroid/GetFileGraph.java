package net.mdrjr.cactidroid;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class GetFileGraph {

	public Bitmap getGraphbyGraphBMP(String graph) {
		Bitmap bmp = null;
		String url = "http://server.where.cacti.is.running.address/path/to/your/graph/folder/" + graph + ".png";
		// note, must finish with a /
		try { 
			bmp = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return bmp;
	}
}
