package com.Cory.gigbag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Add_band_info extends Activity{
	
	String cameFromEdit;
	EditText nameEditText;
	EditText sizeEditText;
	Button doneButton;
	String idOfObject;
	
	ParseUser user;
	
	Network_Info networkInfo;
	boolean isConnected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_band_info);
		
		user = ParseUser.getCurrentUser();

		Bundle extras = getIntent().getExtras();
		
		// **** targetting the input fields **** //
		nameEditText = (EditText)findViewById(R.id.band_name_add_or_edit);
		sizeEditText = (EditText)findViewById(R.id.band_size_add_or_edit);
		
		// **** changed the keypad to be a 12 key keypad **** //
		sizeEditText.setRawInputType(Configuration.KEYBOARD_12KEY);
		
		cameFromEdit = extras.getString("cameFromEdit");
		
		// **** get network status **** //
		networkInfo = Network_Info.getInstance();
		networkInfo.detectNetworkStatus(this);
		
		isConnected = networkInfo.returnStatus();
		
		// **** displaying a disclamer notifying the user **** //
		// **** that they are not connected to the server **** //
		if(isConnected == false){
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("You arent connected to the server, however, you can still create an entry and it will be delivered once connection is reestablished").
			setTitle("No Connection").
			setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			}).show();
		}
		
		// **** if this activity was started by a call to edit **** //
		// **** I want the name and size fields to be filled **** //
		// **** when the user gets here **** //
		if(cameFromEdit.contains("Yes")){
			
			nameEditText.setText(extras.getString("nameOfBand").toString());
			sizeEditText.setText(extras.getString("bandSize").toString());
			
			idOfObject = extras.getString("idString");
			
		}
		
		// **** the done button **** //
		doneButton = (Button)findViewById(R.id.done_button_add_or_edit);
		doneButton.setOnClickListener(new OnClickListener(){

			int size;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(cameFromEdit.contains("Yes")){
					
					ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
					query.getInBackground(idOfObject, new GetCallback<ParseObject>() {
						
						@Override
						public void done(ParseObject object, ParseException e) {
							// TODO Auto-generated method stub
							if(e == null){
								
								size = Integer.parseInt(sizeEditText.getText().toString());
								object.put("bandName", nameEditText.getText().toString());
								object.put("bandSize", size);
								object.put("user", user);
								
								// **** saves the object **** //
								object.saveEventually();
							}
						}
					});
					
				}else{
					
					ParseObject newParseObject = new ParseObject("Post");
					newParseObject.put("bandName", nameEditText.getText().toString());
				
					size = Integer.parseInt(sizeEditText.getText().toString());
					newParseObject.put("bandSize", size);
					newParseObject.put("user", user);
				
					newParseObject.saveEventually();
					
				}
				finish();
			}
		});
	}

}
