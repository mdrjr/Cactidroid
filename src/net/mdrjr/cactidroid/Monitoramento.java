package net.mdrjr.cactidroid;

import java.util.Properties;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class Monitoramento extends Activity {
	/** Called when the activity is first created. */
	private String selectedhost, host = null;
	private String selectedopt, opt = null;
	private Handler handler = new Handler();
	private Properties xml = null;
	Runnable runnable = new Runnable() {
		public void run() {
			GetGraph gg = new GetGraph();
			Bitmap b = gg.getGraph(host, opt, xml);
			ImageView im = (ImageView) findViewById(R.id.imageView1);
			im.setImageBitmap(b);
			handler.postDelayed(this, 60000);
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		// Pegar o XML de Configuracao do Programa
		GetXMLConfig getxml = new GetXMLConfig();
		this.xml = getxml.getXML();
		
		Spinner cmbHost = (Spinner) findViewById(R.id.cmbHost);

		GetServidores servers = new GetServidores();
		String allhosts[] = servers.getServers(xml);
		
		ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
				android.R.layout.simple_spinner_item, allhosts);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbHost.setAdapter(adapter);

		cmbHost.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				selectedhost = arg0.getItemAtPosition(arg2).toString();
				hostselecionado(selectedhost);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		Spinner cmbOpts = (Spinner) findViewById(R.id.cmbGraphs);
		cmbOpts.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				selectedopt = arg0.getItemAtPosition(arg2).toString();
				optselecionada(selectedopt, selectedhost);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	private void hostselecionado(String host) {
		Spinner cmbOpts = (Spinner) findViewById(R.id.cmbGraphs);
		GetServerRes getres = new GetServerRes();
		String[] res = getres.getRes(host, xml);

		ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
				android.R.layout.simple_spinner_item, res);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cmbOpts.setAdapter(adapter);
	}

	private void optselecionada(final String opt, final String host) {
		this.opt = opt;
		this.host = host;
		handler.removeCallbacks(runnable);
		handler.postDelayed(runnable, 1);
	}
}
