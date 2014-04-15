package com.Cory.gigbag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class User_Info extends Activity{
	
	Context context;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        
        context = this;
        
        Bundle bundleExtras = getIntent().getExtras();
        String userId = bundleExtras.getString("objectID").toString();
        
        Log.i("user Id", userId);
        
	}
}
