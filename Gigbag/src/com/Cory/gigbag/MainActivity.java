package com.Cory.gigbag;

import java.io.Serializable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class MainActivity extends Activity {
	
	EditText userName;
	EditText password;
	
	Button logInButton;
	Button createNewAccountButton;
	
	Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        context = this;
        
        // **** initializing my parse stuff **** //
        Parse.initialize(context, "qpsrDDopYR4JfloQD3IMCm5oEAn9D0kAFoX0Xmec", 
        		"uqZYdwJyr8DJ9LiMhQiKy2683tZsHu36q0nUP7Bp");
        
        
        // **** pinpointing the username and passwords **** //
        userName = (EditText)findViewById(R.id.user_name_edit_text);
        password = (EditText)findViewById(R.id.password_edit_text);
        
        
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(currentUser != null){
        	
        	Log.i("user is here", currentUser.getUsername().toString());
        	
        	Intent userInfoIntent = new Intent(context, User_Info.class);
			// **** inserting the userID
			userInfoIntent.putExtra("objectID", currentUser.getObjectId());
		
			startActivity(userInfoIntent);
        	
        }else{
        	
        }
        
        
        
        
        // **** the logInButtons onclicklistener **** //
        logInButton = (Button)findViewById(R.id.button1);
        logInButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// **** getting the user info **** //
				String userNameString = userName.getText().toString();
				String passwordString = password.getText().toString();
				
				// **** sending it to the parse server **** //
				ParseUser.logInInBackground(userNameString, passwordString, new LogInCallback() {
					
					@Override
					public void done(ParseUser user, ParseException e) {
						
						// **** if the user exsists! **** //
						if(user != null){
							
							Intent userInfoIntent = new Intent(context, User_Info.class);
							
							// **** inserting the userID
							userInfoIntent.putExtra("objectID", user.getObjectId());
						
							startActivity(userInfoIntent);
							
						// **** if not! **** //	
						}else{
							
							Log.i("User isnt there", "nope");
						}
					}
				});

				
			}
        	
        });
        
        // **** the createAccountButtons onclick listener **** //
        createNewAccountButton = (Button)findViewById(R.id.button2);
        createNewAccountButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Log.i("create new account clicked", "Yes");
				
				// **** launching the new account activity **** //
				Intent new_account_intent = new Intent(context, New_Account.class);
				
				startActivity(new_account_intent);
				
			}
        	
        });
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
