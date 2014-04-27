package com.Cory.gigbag;



import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class MainActivity extends Activity {
	
	EditText userName;
	EditText password;
	
	TextView userNameTextView;
	TextView passwordTextView;
	TextView noConnectionTextView;
	
	Button logInButton;
	Button createNewAccountButton;
	
	Context context;
	
	Network_Info networkInfo;
	
	ConnectivityManager cm;
	NetworkInfo activeNetwork;
	boolean isConnected;
	
	IntentFilter filter;
	BroadcastReceiver networkStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        context = this;

        // **** gathering connection status **** //
        networkInfo = Network_Info.getInstance();
        networkInfo.detectNetworkStatus(getApplicationContext());
        
        isConnected = networkInfo.returnStatus();
        
        
        // **** pinpointing the username and passwords **** //
        userName = (EditText)findViewById(R.id.user_name_edit_text);
        password = (EditText)findViewById(R.id.password_edit_text);
        
        userNameTextView = (TextView)findViewById(R.id.user_name_text);
        passwordTextView = (TextView)findViewById(R.id.password_text);
        noConnectionTextView = (TextView)findViewById(R.id.no_connection_text);
        
        logInButton = (Button)findViewById(R.id.button1);
        createNewAccountButton = (Button)findViewById(R.id.button2);
        

        // **** broadcast reciever for my connection change **** //
        networkStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            	
            	networkInfo.detectNetworkStatus(getApplicationContext());
            	isConnected = networkInfo.returnStatus();
            	
                Log.i("connection status", "" + isConnected);
                
                disableEnableViewElements();
            }
        };
        
        // **** the intent filter for the receiver **** //
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);        
        registerReceiver(networkStateReceiver, filter);

        
        
        
        // **** disables or enables elements based on **** //
        // **** connectivity **** //
        disableEnableViewElements();
        
        // **** checking to see if there is a user still **** //
        // **** logged in **** //
        ParseUser currentUser = ParseUser.getCurrentUser();
        if((currentUser != null) && (isConnected == true)){
        	
        	// **** starting the user info activity **** //
        	Intent userInfoIntent = new Intent(context, User_Info.class);
        	
			startActivity(userInfoIntent);
        	
        }
        
        
        
        
        // **** the logInButtons onclicklistener **** //
        logInButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// **** getting the user info **** //
				String userNameString = userName.getText().toString();
				String passwordString = password.getText().toString();
				
				// **** sending it to the parse server **** //
				ParseUser.logInInBackground(userNameString, passwordString, new LogInCallback() {
					
					@Override
					public void done(ParseUser user, ParseException e) {
						
						// **** if the user exsists! **** //
						if(user != null){
							
							// **** starting the user info activity **** //
							Intent userInfoIntent = new Intent(context, User_Info.class);
							startActivity(userInfoIntent);
							
						// **** if not! **** //	
						}else{
							Toast.makeText(context, "User not Found", Toast.LENGTH_LONG).show();
						}
					}
				});	
			}
        	
        });
        
        // **** the createAccountButtons onclick listener **** //
        createNewAccountButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// **** launching the new account activity **** //
				Intent new_account_intent = new Intent(context, New_Account.class);
				
				startActivity(new_account_intent);
			}
        });
    }


	public void disableEnableViewElements(){
    	
    	if(isConnected == true){
        	userNameTextView.setVisibility(View.VISIBLE);
        	passwordTextView.setVisibility(View.VISIBLE);
        	userName.setVisibility(View.VISIBLE);
        	password.setVisibility(View.VISIBLE);
        	
        	logInButton.setVisibility(View.VISIBLE);
        	createNewAccountButton.setVisibility(View.VISIBLE);
        	noConnectionTextView.setVisibility(View.INVISIBLE);
        }else{
        	userNameTextView.setVisibility(View.INVISIBLE);
        	passwordTextView.setVisibility(View.INVISIBLE);
        	userName.setVisibility(View.INVISIBLE);
        	password.setVisibility(View.INVISIBLE);
        	
        	logInButton.setVisibility(View.INVISIBLE);
        	createNewAccountButton.setVisibility(View.INVISIBLE);
        	noConnectionTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
