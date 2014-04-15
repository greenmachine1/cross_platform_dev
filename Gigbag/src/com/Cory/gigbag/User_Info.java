package com.Cory.gigbag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class User_Info extends Activity{
	
	Context context;
	
	Button logOutButton;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        
        context = this;
        
        logOutButton = (Button)findViewById(R.id.log_out_button_user_info);
        
        Bundle bundleExtras = getIntent().getExtras();
        String userId = bundleExtras.getString("objectID").toString();
        
        Log.i("user Id", userId);
        
	}
}
