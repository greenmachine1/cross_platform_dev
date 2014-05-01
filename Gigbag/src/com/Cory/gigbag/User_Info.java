package com.Cory.gigbag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.ParseUser;


public class User_Info extends FragmentActivity{
	
	Context context;
	
	Button logOutButton;
	
	Button addBandInfoButton;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        
        context = this;
        
        addBandInfoButton = (Button)findViewById(R.id.add_band_info_button);
        addBandInfoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent addBandInfoIntent = new Intent(context, Add_band_info.class);
				addBandInfoIntent.putExtra("cameFromEdit", "No");
				startActivity(addBandInfoIntent);
			}
		});
     
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
	// **** the receiver class
	public class reciever extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Log.i("Got called from User_info class", "Yes");
		}
		
	}
}
