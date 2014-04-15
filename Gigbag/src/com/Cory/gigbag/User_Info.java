package com.Cory.gigbag;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class User_Info extends Activity{
	
	Context context;
	
	Button logOutButton;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        
        context = this;
      
        ParseUser user = ParseUser.getCurrentUser();
        
        Log.i("User", user.getUsername().toString());
        
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("user", user);
        query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> listOfObjects, ParseException e) {
				// TODO Auto-generated method stub
				if(e == null){
					
					int amountOfObjects = listOfObjects.size();
					
					for(int i = 0; i < amountOfObjects; i++){
					
						// **** successfully gets the bandNames and their sizes of all the objects **** //
						Log.i("Band name", "" + listOfObjects.get(i).getString("bandName"));
						Log.i("size", "" + listOfObjects.get(i).getInt("bandSize"));
					}
				}else{
					Log.i("error", e.getMessage().toString());
				}
			}
		});
        
        
        
        
        // **** the logout button **** //
        logOutButton = (Button)findViewById(R.id.log_out_button_user_info);
        logOutButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				// **** logging out the user **** //
				ParseUser.logOut();
				
				// **** closes this activity **** //
				finish();
			}
        	
        });
        
	}
}
