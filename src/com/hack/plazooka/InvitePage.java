package com.hack.plazooka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class InvitePage extends Activity{


	  private ArrayList<Map<String, String>> contacts;
	    private ListView contactsListView;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_invite_page);
	        
	        contactsListView = (ListView) findViewById(R.id.listView1);
	        
	        String[] from = { "name" , "number" };
	        int[] to = { R.id.textView1, R.id.textView2 };
	        
	        contacts = fetchWhatsAppContacts();
	        
	        SimpleAdapter adapter = new SimpleAdapter(this, contacts, R.layout.activity_contacts, from, to);
	        contactsListView.setAdapter(adapter);
	        
	        contactsListView.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				    try{
	                    Uri uri = Uri.parse("smsto:"+ contacts.get(R.id.textView2).get("number").toString());
	                    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
	                    i.setPackage("com.whatsapp");
	                    startActivity(i);
	                }catch (ActivityNotFoundException e) {
	                    Toast.makeText(getApplicationContext(), "no whatsapp!", Toast.LENGTH_SHORT).show();
	                    Log.e("Intent", e.getMessage());
	                }
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub
					
				}
	        });
	        
	    }
	    
	    private HashMap<String, String> putData(String name, String number) {
	        HashMap<String, String> item = new HashMap<String, String>();
	        item.put("name", name);
	        item.put("number", number);
	        return item;
	      }
	    
	    private ArrayList<Map<String, String>> fetchWhatsAppContacts(){
	        
	        ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
	        
	        final String[] projection={
	                ContactsContract.Data.CONTACT_ID,
	                ContactsContract.Data.MIMETYPE,
	                "account_type",
	                ContactsContract.Data.DATA3,
	                };
	        final String selection= ContactsContract.Data.MIMETYPE+" =? and account_type=?";
	        final String[] selectionArgs = {
	                "vnd.android.cursor.item/vnd.com.whatsapp.profile",
	                "com.whatsapp"
	                };
	        ContentResolver cr = getContentResolver();
	        Cursor c = cr.query(
	                ContactsContract.Data.CONTENT_URI,
	                projection,
	                selection,
	                selectionArgs,
	                null);
	        while(c.moveToNext()){
	            String id=c.getString(c.getColumnIndex(ContactsContract.Data.CONTACT_ID));
	            String number=c.getString(c.getColumnIndex(ContactsContract.Data.DATA3));
	            String name="";
	            Cursor mCursor=getContentResolver().query(
	                    ContactsContract.Contacts.CONTENT_URI,
	                    new String[]{ContactsContract.Contacts.DISPLAY_NAME},
	                    ContactsContract.Contacts._ID+" =?",
	                    new String[]{id},
	                    null);
	            while(mCursor.moveToNext()){
	                name=mCursor.getString(mCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
	            }
	            mCursor.close();
	            list.add(putData(name, number));
	        }
	        Log.v("WhatsApp", "Total WhatsApp Contacts: "+c.getCount());
	        c.close();
	        return list;
	    }

	}