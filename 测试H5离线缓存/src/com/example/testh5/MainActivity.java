package com.example.testh5;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Website;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebStorage.QuotaUpdater;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebView myWebView = (WebView) findViewById(R.id.my_wv);
		WebSettings webSettings = myWebView.getSettings() ;
		webSettings.setDomStorageEnabled(true);
		webSettings.setAppCacheMaxSize(1024*1024*8);
		String cachePath = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
		System.out.println("cachepath="+cachePath);
	    webSettings.setAppCachePath(cachePath);
	    webSettings.setAllowFileAccess(true);
	    webSettings.setAppCacheEnabled(true);
	    webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
	    webSettings.setJavaScriptEnabled(true);
	    webSettings.setDatabaseEnabled(true);
	    
	    WebChromeClient client = new WebChromeClient(){
	    	@Override
	    	public void onReachedMaxAppCacheSize(long requiredStorage, long quota, QuotaUpdater quotaUpdater) {
	    		// TODO Auto-generated method stub
	    		quotaUpdater.updateQuota(requiredStorage*2);
	    	}
	    };
	    //http://192.168.0.78/mxw//FileAccess.aspx?FileName=/Reports/mobilesample/financialanalysis/exception.fpd&uid=admin&pwd=admin

	    myWebView.setWebChromeClient(client);
	   myWebView.loadUrl("http://news.baidu.com/");
	   // myWebView.loadUrl("http://192.168.0.78/mxw/add_ons/MxwFpd/View.aspx?objpath=/Reports/mobilesample/financialanalysis/exception.fpd");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
