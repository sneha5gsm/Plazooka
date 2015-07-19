package com.hack.plazooka;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Options extends Activity {

	Button b1,b2,b3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 Intent i = new Intent(Options.this,Description.class);
			 startActivity(i);
			}
				
		});
		
b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 Intent in = new Intent(Options.this,ShareGame.class);
			 startActivity(in);
			}
				
		});

b3.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
	 Intent im = new Intent(Options.this,ViewGames.class);
	 startActivity(im);
	}
		
});
	}
}
	
