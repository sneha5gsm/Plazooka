package com.hack.plazooka;

import com.hack.plazooka.Plazooka;
import com.hack.plazooka.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Plazooka extends Activity implements OnClickListener {
	
	Button b;
	MediaPlayer backgroundsong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plazooka);
		 backgroundsong= MediaPlayer.create(Plazooka.this, R.raw.great);
		    backgroundsong.start();
		
		b = (Button)findViewById(R.id.button1);
		
		b.setOnClickListener(this);
	}
	@Override
	protected void onDestroy() {
	    // TODO Auto-generated method stub
	    super.onDestroy();
	   backgroundsong.release();
	}
	public void onClick(View v) {
	
		Intent i = new Intent(Plazooka.this,Options.class);
		startActivity(i);
		
	}
}
