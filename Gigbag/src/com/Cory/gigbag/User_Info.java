package com.Cory.gigbag;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import com.parse.ParseUser;


public class User_Info extends FragmentActivity{
	
	Context context;
	
	Button logOutButton;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        
        context = this;
      
        ParseUser user = ParseUser.getCurrentUser();
     
        // **** the logout button **** //
        logOutButton = (Button)findViewById(R.id.log_out_button_user_info);
        logOutButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				// **** logging out the user **** //
				ParseUser.logOut();
				
				// **** closes this activity **** //
				finish();
			}
        	
        });
        
	}
}
