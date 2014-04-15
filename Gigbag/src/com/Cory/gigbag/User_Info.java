package com.Cory.gigbag;

import java.util.HashMap;
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
	
	HashMap<String, String> nameOfBands;
	HashMap<String, String> sizeOfBands;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        
        context = this;
      
        ParseUser user = ParseUser.getCurrentUser();
        
        nameOfBands = new HashMap<String, String>();
        sizeOfBands = new HashMap<String, String>();
        
        // **** gathering up all the users info and putting it **** //
        // **** into hashMaps **** //
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("user", user);
        query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> listOfObjects, ParseException e) {
				// TODO Auto-generated method stub
				if(e == null){
					
					nameOfBands.clear();
					sizeOfBands.clear();
					
					int amountOfObjects = listOfObjects.size();
					
					// **** cycling through the objects **** //
					for(int i = 0; i < amountOfObjects; i++){
					
						// **** successfully gets the bandNames and their sizes of all the objects **** //
						String bandName = listOfObjects.get(i).getString("bandName");
						int bandSize = listOfObjects.get(i).getInt("bandSize");
						
						// **** saving the band name in a hashmap **** // 
						nameOfBands.put(bandName, bandName);
						
						// **** saving the band size in a hashmap **** //
						sizeOfBands.put(bandName, "" + bandSize);
					}
					
					Log.i("band and size", nameOfBands.toString() + "" + sizeOfBands.toString());
					
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
				
				// **** logging out the user **** //
				ParseUser.logOut();
				
				// **** closes this activity **** //
				finish();
			}
        	
        });
        
	}
}
