package com.Cory.gigbag;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class Add_band_info extends Activity{
	
	String cameFromEdit;
	
	EditText nameEditText;
	EditText sizeEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_band_info);
		
		Bundle extras = getIntent().getExtras();
		
		// **** targetting the input fields **** //
		nameEditText = (EditText)findViewById(R.id.band_name_add_or_edit);
		sizeEditText = (EditText)findViewById(R.id.band_size_add_or_edit);
		
		cameFromEdit = extras.getString("cameFromEdit");
		
		// **** if this activity was started by a call to edit **** //
		// **** I want the name and size fields to be filled **** //
		// **** when the user gets here **** //
		if(cameFromEdit.contains("Yes")){
			
			nameEditText.setText(extras.getString("nameOfBand").toString());
			sizeEditText.setText(extras.getString("bandSize").toString());
			
		}
		
		Log.i("band name", extras.getString("nameOfBand").toString());
		Log.i("came from edit", "" + extras.getString("cameFromEdit"));
		Log.i("band size", extras.getString("bandSize"));
	}

}
