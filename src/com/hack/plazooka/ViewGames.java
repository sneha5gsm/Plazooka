package com.hack.plazooka;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewGames extends ListActivity{
	
	 ListView lv;
	 String games[]={"SevenStones","HideNSeek","Dodgeball","DogInTheBone","MiddleMonkey","Frisbee"};
	 
	 @Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_view_games);
			
			ArrayAdapter adapter = new ArrayAdapter(ViewGames.this , android.R.layout.simple_expandable_list_item_1,games);
			setListAdapter(adapter);
			
	 }
	 protected void onListItemClick(ListView l, View v, int position, long id) {
	try{	 
	 String string = "com.hack.plazooka."+games[position];
	 Class<?> c = Class.forName(string);
	 Intent intent = new Intent(ViewGames.this,c);
	 startActivity(intent); }catch(Exception e){System.out.println(""+e.getMessage());}
	 };
	 }