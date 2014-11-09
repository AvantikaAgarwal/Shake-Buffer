package com.pravar.webbrowserpravar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class HistoryActivity extends Activity implements OnItemClickListener,OnItemLongClickListener{

	ListView lv;
	ArrayList<String> al;
	ArrayAdapter<String> aa;
	Set<String> ss;
	int b;
	Bundle s;
	int position;
	TextView tv;
	Date cal;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		
		//java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getBaseContext());
		//String mydate=dateFormat.format(date);
		
		//tv=(TextView)findViewById(R.id.textView2);
		tv=new TextView(this);
		String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		tv.setText(mydate+":");
		lv=(ListView)findViewById(R.id.listView2);
		lv.addHeaderView(tv);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
		al= new ArrayList<String>();
		SharedPreferences sharedPref = getApplication().getSharedPreferences("myprefs",0);
		b = sharedPref.getInt("b",0);
		ss=sharedPref.getStringSet("bb",null);
		if(b==2)
		{
			
			Intent i= getIntent();
			Bundle bdl=i.getExtras();
			int flag=bdl.getInt("flag");
			String str=bdl.getString("hist");
			if(ss!=null)
			{
				al= new ArrayList<String>(ss);
			}
			al.add(str);
			ss = new HashSet<String>();
			ss.addAll(al);
			SharedPreferences sharedPrefe = getApplication().getSharedPreferences("myprefs",0);
			SharedPreferences.Editor editor = sharedPrefe.edit();
			editor.putStringSet("bb", ss);
			editor.commit();
			if(flag==1)
			{
				
				Intent j=new Intent();
				setResult(RESULT_CANCELED, j);
				finish();
			}
			
		}
		
		
		if(b==1){
			Log.d("sdsd", "inside if==1");
			
		SharedPreferences sharedPrefe = getApplication().getSharedPreferences("myprefs",0);
		ss=sharedPrefe.getStringSet("bb",null);
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
		k.putExtra("histname",s);
		setResult(7,k);
		finish();
		
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.listView2) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	    String menuItem="Remove";
	    menu.add(1, 1, 1, menuItem);
	  }
	  }

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		al.remove(position-1);
		aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al);
		lv.setAdapter(aa);
		ss = new HashSet<String>();
		ss.addAll(al);
		SharedPreferences sharedPrefe = getApplication().getSharedPreferences("myprefs",0);
		SharedPreferences.Editor editor = sharedPrefe.edit();
		editor.putStringSet("bb", ss);
		editor.commit();
		return super.onContextItemSelected(item);
		
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		position=arg2;
		//Toast.makeText(this, "Position deleted="+position, Toast.LENGTH_LONG).show(); 
		return false;
	}
}
