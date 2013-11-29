package com.example.androidhttptest;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText message;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		message = (EditText)this.findViewById(R.id.message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void send() {

    	try{ 
    		HttpClient Client = new DefaultHttpClient();
  
    		String msg = message.getText().toString();
    		String URL = "http://192.168.0.180/appLog.php?appName=android&message=" + msg;
   
    		try
    		{
                HttpGet httpget = new HttpGet(URL);
               ResponseHandler<String> responseHandler = new BasicResponseHandler();
               Client.execute(httpget, responseHandler);
     
    		} catch(Exception ex) {
    			String str = ex.getMessage();
    			Log.i("app", str == null ? "error: null" : str);
    		}
    	}
    	finally{}
    }

	public void sendButtonOnClick(View v)
	{
		new Thread(new Task()).start();
	}
	
    class Task implements Runnable {
        @Override

        public void run() {
        	send();
        }
 
    }

}
