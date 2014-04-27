package com.Cory.gigbag;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class New_Account extends Activity{

	EditText userName;
	EditText passWord;
	EditText email;
	
	TextView userNameTextView;
	TextView passwordTextView;
	TextView emailTextView;
	TextView mainLabel;
	TextView noConnectionTextView;
	
	Button createAccountButtonCreate;
	Network_Info networkInfo;
	boolean isConnected;
	ParseUser user;
	
	IntentFilter filter;
	BroadcastReceiver networkStateReceiver;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);
        
        user = new ParseUser();
        
        userName = (EditText)findViewById(R.id.user_name_edit_text);
        passWord = (EditText)findViewById(R.id.password_edit_text);
        email = (EditText)findViewById(R.id.email_edit_text);
        
        userNameTextView = (TextView)findViewById(R.id.user_name_account_create_text);
        passwordTextView = (TextView)findViewById(R.id.password_account_create_text);
        emailTextView = (TextView)findViewById(R.id.email_account_create_text);
        mainLabel = (TextView)findViewById(R.id.main_create_account_text);
        noConnectionTextView = (TextView)findViewById(R.id.no_connection_text_field_new_account);
        
		
        createAccountButtonCreate = (Button)findViewById(R.id.create_account_button_account_create_button);
        
        // **** showing or not showing the elements initially **** //
        disableEnableViewElements();
        
        networkInfo = Network_Info.getInstance();
        networkInfo.detectNetworkStatus(this);
        
        isConnected = networkInfo.returnStatus();
        
        // **** broadcast reciever for my connection change **** //
        networkStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            	
            	networkInfo.detectNetworkStatus(getApplicationContext());
            	isConnected = networkInfo.returnStatus();
            	
            	// **** gets called when connection status changes **** //
                disableEnableViewElements();
            }
        };
        
        // **** the intent filter for the receiver **** //
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);        
        registerReceiver(networkStateReceiver, filter);

        
        // **** create account button **** //
        createAccountButtonCreate.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				
				// **** if there is a connection **** //
				if(isConnected == true){
					
					// **** gathering all the text fields **** //
					user.setUsername(userName.getText().toString());
					user.setEmail(email.getText().toString());
					user.setPassword(passWord.getText().toString());
					user.signUpInBackground(new SignUpCallback() {
						
						@Override
						public void done(ParseException e) {
							
							// **** sign up successful **** //
							if(e == null){
							
								Intent userInfoIntent = new Intent(getApplication(), User_Info.class);
								startActivity(userInfoIntent);
								
							// **** sign up not successful **** //	
							}else{
								
								Log.i("account creation", "not successful");
								
							}
							
						}
					});
					
				// **** if there is no connection **** //	
				}else{
					
					
				}
				
			}
        	
        });
        
	}
	
	
	// **** enables and disables view elements **** //
	public void disableEnableViewElements(){
		
		if(isConnected == true){
			Log.i("is connected", "true");
			userName.setVisibility(View.VISIBLE);
			email.setVisibility(View.VISIBLE);
			passWord.setVisibility(View.VISIBLE);
			
			userNameTextView.setVisibility(View.VISIBLE);
			passwordTextView.setVisibility(View.VISIBLE);
			emailTextView.setVisibility(View.VISIBLE);
			
			createAccountButtonCreate.setVisibility(View.VISIBLE);
			
			noConnectionTextView.setVisibility(View.INVISIBLE);
			
		}else if(isConnected == false){
			
			Log.i("is connected", "false");
			userName.setVisibility(View.INVISIBLE);
			email.setVisibility(View.INVISIBLE);
			passWord.setVisibility(View.INVISIBLE);
			
			userNameTextView.setVisibility(View.INVISIBLE);
			passwordTextView.setVisibility(View.INVISIBLE);
			emailTextView.setVisibility(View.INVISIBLE);
			
			createAccountButtonCreate.setVisibility(View.INVISIBLE);
			
			noConnectionTextView.setVisibility(View.VISIBLE);
			
		}
		
	}
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		// **** unregister the reciever **** //
		unregisterReceiver(networkStateReceiver);
	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// **** registering the reciever **** //
		registerReceiver(networkStateReceiver, filter);
	}
	
}
