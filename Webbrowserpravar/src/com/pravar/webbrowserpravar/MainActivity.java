package com.pravar.webbrowserpravar;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener{

	WebView wv;
	ImageButton go,back,forward,reload,bookmark;
	EditText urla;
	ProgressBar pbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
	    setContentView(R.layout.activity_main);
	   // getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
	    pbar = (ProgressBar) findViewById(R.id.progressBar1);
		urla=(EditText)findViewById(R.id.urla);
		wv=(WebView)findViewById(R.id.webView1);
        reload=(ImageButton)findViewById(R.id.rel);
        bookmark=(ImageButton)findViewById(R.id.bookmark);
        //go=(Button)findViewById(R.id.go);
        back=(ImageButton)findViewById(R.id.bk);
        forward=(ImageButton)findViewById(R.id.fwd);
       // go.setOnClickListener(this);
        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        reload.setOnClickListener(this);
        bookmark.setOnClickListener(this);
        wv.setWebViewClient(new MyBrowser());
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        urla.setText("http://www.google.com");
        wv.loadUrl("http://www.google.com");
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) 
               {
               if(progress < 100 && pbar.getVisibility() == ProgressBar.GONE){
                   pbar.setVisibility(ProgressBar.VISIBLE);
               }
               pbar.setProgress(progress);
               if(progress == 100) {
                   pbar.setVisibility(ProgressBar.GONE);
               }
            }
        });
       urla.addTextChangedListener(new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			reload.setImageResource(R.drawable.gob);
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	});
   
	}
	
	@Override
	public void onClick(View v) {
		 switch(v.getId())
		    {
		   /* case R.id.go:
		    {
		    	String URL=url.getText().toString();
		    	wv.loadUrl(URL);
		    }
		    	break;*/
		    case R.id.bk:
		    	if(wv.canGoBack())
		    	{
		    		wv.goBack();
		    	}
		    	break;
		    case R.id.fwd:
		    	if(wv.canGoForward())
		    	{
		    		wv.goForward();
		    	}
		    	break;
		    case R.id.rel:
		    	String URL=urla.getText().toString();
		    	wv.loadUrl(URL);
		    	bookmark.setImageResource(R.drawable.star1);
		    	reload.setImageResource(R.drawable.reloadicon);
		    	Intent k=new Intent(this,HistoryActivity.class);
		    	k.putExtra("hist", urla.getText().toString());
		    	k.putExtra("flag",1);
		    	SharedPreferences sharedPrefe = getApplication().getSharedPreferences("myprefs",0);
				SharedPreferences.Editor editore = sharedPrefe.edit();
				editore.putInt("b", 2);
				editore.commit();
				
		    	startActivityForResult(k,5);
		    	break;
		    	
		    case R.id.bookmark:
		    
		    	bookmark.setImageResource(R.drawable.star2);
		    	Intent i=new Intent(this,BookmarkActivity.class);
		    	i.putExtra("book", urla.getText().toString());
		    	i.putExtra("flagval",1);
		    	SharedPreferences sharedPref = getApplication().getSharedPreferences("myprefs",0);
				SharedPreferences.Editor editor = sharedPref.edit();
				editor.putInt("a", 2);
				editor.commit();
				
		    	startActivityForResult(i,2);
		    	break;
		    
		    }
		 
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==1 && resultCode==4)
		{
			
			Bundle bd=data.getExtras();
			String bname=bd.getString("bookname");
			//Toast.makeText(this, "Onactivityresult", Toast.LENGTH_SHORT).show();
			urla.setText(bname);
			reload.setImageResource(R.drawable.reloadicon);
			myfunc();
			
		}
		
		if(requestCode==6 && resultCode==7)
		{
			
			Bundle bd=data.getExtras();
			String hname=bd.getString("histname");
			//Toast.makeText(this, "Onactivityresult", Toast.LENGTH_SHORT).show();
			urla.setText(hname);
			reload.setImageResource(R.drawable.reloadicon);
			wv.loadUrl(hname);
			
		}
		
		
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(1, 0, 1,"Bookmarks" );
		menu.add(1, 1, 2,"History");
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		//Toast.makeText(this, item.getItemId()+" ", Toast.LENGTH_LONG).show();
		if(item.getItemId()==0)
		{
		//Toast.makeText(this, item.getItemId()+" ", Toast.LENGTH_LONG).show();
		SharedPreferences sharedPref = getApplication().getSharedPreferences("myprefs",0);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt("a", 1);
		editor.commit();
		Intent i=new Intent(this,BookmarkActivity.class);
		startActivityForResult(i,1);
		}
		else
		{
			SharedPreferences sharedPref = getApplication().getSharedPreferences("myprefs",0);
			SharedPreferences.Editor editor = sharedPref.edit();
			editor.putInt("b", 1);
			editor.commit();
			Intent i=new Intent(this,HistoryActivity.class);
			startActivityForResult(i,6);
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	public void myfunc()
	{
		String URL=urla.getText().toString();
    	wv.loadUrl(URL);
    	bookmark.setImageResource(R.drawable.star1);
    	Intent k=new Intent(this,HistoryActivity.class);
    	k.putExtra("hist", urla.getText().toString());
    	k.putExtra("flag",1);
    	SharedPreferences sharedPrefe = getApplication().getSharedPreferences("myprefs",0);
		SharedPreferences.Editor editore = sharedPrefe.edit();
		editore.putInt("b", 2);
		editore.commit();
		
    	startActivityForResult(k,5);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(event.KEYCODE_BACK==keyCode)
		{
			if(wv.canGoBack())
	    	{
	    		wv.goBack();
	    		urla.setText(wv.getUrl());
	    		return true;
	    	}
			
		}
		
		return super.onKeyDown(keyCode, event);
	}
}
