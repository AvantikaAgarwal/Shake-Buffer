package com.pravar.webbrowserpravar;


import java.util.*;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class BookmarkActivity extends Activity implements OnItemClickListener,OnItemLongClickListener {

	ListView lv;
	ArrayList<String> al;
	ArrayAdapter<String> aa;
	Set<String> ss;
	int a;
	Bundle s;
	int position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bookmark);
		lv=(ListView)findViewById(R.id.listView1);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
		al= new ArrayList<String>();
		SharedPreferences sharedPref = getApplication().getSharedPreferences("myprefs",0);
		a = sharedPref.getInt("a",0);
		ss=sharedPref.getStringSet("aa",null);
		if(a==2)
		{
			Intent i= getIntent();
			Bundle bdl=i.getExtras();
			int flag=bdl.getInt("flagval");
			String str=bdl.getString("book");
			if(ss!=null)
			{
				al= new ArrayList<String>(ss);
			}
			al.add(str);
			ss = new HashSet<String>();
			ss.addAll(al);
			SharedPreferences sharedPrefe = getApplication().getSharedPreferences("myprefs",0);
			SharedPreferences.Editor editor = sharedPrefe.edit();
			editor.putStringSet("aa", ss);
			editor.commit();
			if(flag==1)
			{
				
				Intent j=new Intent();
				setResult(RESULT_CANCELED, j);
				finish();
			}
			
		}
		
		
		if(a==1){
			Log.d("sdsd", "inside if==1");
			
		SharedPreferences sharedPrefe = getApplication().getSharedPreferences("myprefs",0);
		ss=sharedPrefe.getStringSet("aa",null);
		if(ss==null){al= new ArrayList<String>();}
		else{al= new ArrayList<String>(ss);}
		aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al);
		lv.setAdapter(aa);
		registerForContextMenu(lv);
		}
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		// TODO Auto-generated method stub
		
		String s=arg0.getItemAtPosition(pos).toString();
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
		Intent k=new Intent();
		k.putExtra("bookname",s);
		setResult(4,k);//result code
		finish();
		
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.listView1) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	    String menuItem="Remove";
	    menu.add(1, 1, 1, menuItem);
	  }
	  }

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		al.remove(position);
		aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al);
		lv.setAdapter(aa);
		ss = new HashSet<String>();
		ss.addAll(al);
		SharedPreferences sharedPrefe = getApplication().getSharedPreferences("myprefs",0);
		SharedPreferences.Editor editor = sharedPrefe.edit();
		editor.putStringSet("aa", ss);
		editor.commit();
		return super.onContextItemSelected(item);
		
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		position=arg2;
		return false;
	}

}
