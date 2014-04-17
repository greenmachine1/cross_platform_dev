package com.Cory.gigbag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;



// **** class for dealing with the fragment
public class User_info_List_Fragment extends Fragment{
	
	HashMap<String, String> bandName;
	HashMap<String, String> bandSize;
	
	ArrayList<String> listOfNames;
	
	ListView mainListView;
	
	// **** setting the adapter **** //
	Main_list_adapter adapter;
	
	
	// **** declaring the adapter array list **** //
	public ArrayList<Main_list_definition> bandNameAndSizeList = new ArrayList<Main_list_definition>();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		bandName = new HashMap<String, String>();
		bandSize = new HashMap<String, String>();
		listOfNames = new ArrayList<String>();

	
		
		View view;
		view = inflater.inflate(R.layout.list_for_fragment, container, false);
		
		// **** targetting the main listview **** //
		mainListView = (ListView)view.findViewById(R.id.main_listView);
		
		// **** final bridging of the adapter **** //
		adapter = new Main_list_adapter(getActivity(), R.layout.item_for_list, bandNameAndSizeList);

		// **** getting the current user **** //
		ParseUser user = ParseUser.getCurrentUser();
		
		
		
		// **** gathering up all the users info and putting it **** //
        // **** into hashMaps **** //
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("user", user);
        query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> listOfObjects, ParseException e) {
				// TODO Auto-generated method stub
				if(e == null){
					
					
					bandName.clear();
					bandSize.clear();
					listOfNames.clear();
					
					int amountOfObjects = listOfObjects.size();
					
					// **** cycling through the objects **** //
					for(int i = 0; i < amountOfObjects; i++){
					
						// **** successfully gets the bandNames and their sizes of all the objects **** //
						String bandNameString = listOfObjects.get(i).getString("bandName");
						int bandSizeString = listOfObjects.get(i).getInt("bandSize");
						
						// **** saving the band name in a hashmap **** // 
						bandName.put(bandNameString, bandNameString);
						
						// **** saving the band size in a hashmap **** //
						bandSize.put(bandNameString, "" + bandSizeString);
						
						// **** adding names to the listOfNames array **** //
						listOfNames.add(bandNameString);
					
					}
					
					adapter.notifyDataSetChanged();
					
				}else{
					
					Log.i("error", e.getMessage().toString());
				}

				// **** able to add bands to the adapter and be displayed
				for(int i = 0; i < listOfNames.size(); i++){
					
					//Main_list_definition item = new Main_list_definition(bandName.get(i - 1).toString(), bandSize.get(i - 1).toString());
					Main_list_definition item = new Main_list_definition("Band Name: " + bandName.get(listOfNames.get(i)).toString(),
																		 "Number of Members: " + bandSize.get(listOfNames.get(i)).toString());
					bandNameAndSizeList.add(item);
					adapter.notifyDataSetChanged();
				}
				
			}

		});

		// **** setting the mainlist to hold the adapter **** //
		mainListView.setAdapter(adapter);
		
		// **** the onItemClickListener for the main View **** //
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int item,
					long arg3) {
				
				Log.i("Selected", "" + item);
				
				// **** setting up the edit and delete functionality **** //
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				
				// **** Cancel, delete, and edit buttons
				builder.setMessage("Edit or Delete band " + listOfNames.get(item)).
						setTitle("Edit or Delete").
						setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Log.i("Ok clicked", "Yes");
								dialog.cancel();
							}
						}).setNeutralButton("Delete", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Log.i("Ok clicked", "Yes");
								dialog.cancel();
							}
						}).setNegativeButton("Edit", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								Log.i("Ok clicked", "yes");
								dialog.cancel();
							}
						}).show();
			}
		});
		
		return view;
	}
}
