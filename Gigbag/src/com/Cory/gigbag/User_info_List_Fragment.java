package com.Cory.gigbag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

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
	ArrayList<ParseObject> objects;
	
	ListView mainListView;
	
	// **** setting the adapter **** //
	Main_list_adapter adapter;
	
	// **** getting the current user **** //
	ParseUser user = ParseUser.getCurrentUser();
	
	BroadcastReceiver networkStateReceiver;
	Network_Info networkInfo;
	IntentFilter filter;
	boolean isConnected;
	
	
	// **** declaring the adapter array list **** //
	public ArrayList<Main_list_definition> bandNameAndSizeList = new ArrayList<Main_list_definition>();

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// **** getting the network singleton **** // 
		networkInfo = Network_Info.getInstance();
		
		bandName = new HashMap<String, String>();
		bandSize = new HashMap<String, String>();
		listOfNames = new ArrayList<String>();
		objects = new ArrayList<ParseObject>();

		View view;
		view = inflater.inflate(R.layout.list_for_fragment, container, false);
		
		// **** targetting the main listview **** //
		mainListView = (ListView)view.findViewById(R.id.main_listView);
		
		// **** final bridging of the adapter **** //
		adapter = new Main_list_adapter(getActivity(), R.layout.item_for_list, bandNameAndSizeList);
		
		// **** checking network status **** //
		if(networkInfo.returnStatus() == true){
			// **** loading the data **** //
			loadData();
		}else{
			// **** no connection toast **** //
			Toast.makeText(getActivity(), "No Connection", Toast.LENGTH_LONG).show();
		}
		
		
		

		// **** broadcast reciever for my connection change **** //
        networkStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            	networkInfo.detectNetworkStatus(getActivity());
            	isConnected = networkInfo.returnStatus();
                
                if(isConnected == true){
                	loadData();
                }
            }
        };
        
        // **** the intent filter for the receiver **** //
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);  
        getActivity().registerReceiver(networkStateReceiver, filter);
		
	
        
		

		
		// **** setting the mainlist to hold the adapter **** //
		mainListView.setAdapter(adapter);
		
		// **** the onItemClickListener for the main View **** //
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			String nameString;
			int itemInt;
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int item,
					long arg3) {
				
				nameString = listOfNames.get(item).toString();
				itemInt = item;
				Log.i("Selected", "" + item);
				
				// **** setting up the edit and delete functionality **** //
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				if(isConnected == true){
				
					// **** Cancel, delete, and edit buttons
					builder.setMessage("Edit or Delete band " + listOfNames.get(item)).
							setTitle("Edit or Delete").
							setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.cancel();
								}
							}).setNeutralButton("Delete", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {									
									objects.get(itemInt).deleteInBackground();
									loadData();
									dialog.cancel();
								}
							}).setNegativeButton("Edit", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.cancel();
									
									// **** starting the Add_band_info class to pass **** //
									// **** in info to be edited **** //
									Intent editIntent = new Intent(getActivity(), Add_band_info.class);
									
									// **** passing in the name of the band **** //
									editIntent.putExtra("nameOfBand", nameString);
									editIntent.putExtra("bandSize", bandSize.get(listOfNames.get(itemInt)).toString());
									editIntent.putExtra("idString", objects.get(itemInt).getObjectId());
									editIntent.putExtra("cameFromEdit", "Yes");
									
									startActivity(editIntent);
								}
							}).show();
					
				// **** if there is no connection **** //	
				}else if(isConnected == false){
					
					builder.setMessage("Please connect to WiFi or cellular connectivity to Edit or Delete").
					setTitle("No Connection").
					setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							// **** dismiss the dialog **** //
							dialog.cancel();
						}
					}).show();
				}
			}
		});
		
		// **** return the view **** //
		return view;
	}

	
	
	// **** loading up the data **** //
	public void loadData(){

		Toast.makeText(getActivity(), "Loading", Toast.LENGTH_SHORT).show();
		
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereEqualTo("user", user);
        query.findInBackground(new FindCallback<ParseObject>() {
			
			@Override
			public void done(List<ParseObject> listOfObjects, ParseException e) {
				// TODO Auto-generated method stub
				if(e == null){
					
					// **** clears out the hashmaps **** //
					clearOutHashMaps();
					
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
						objects.add(listOfObjects.get(i));
					
					}
					
					// **** after loading everything into the hashmaps **** //
					// **** notify the adapter **** //
					adapter.notifyDataSetChanged();
					
				}else{
					Log.i("error", e.getMessage().toString());
				}

				// **** able to add bands to the adapter and be displayed
				for(int i = 0; i < listOfNames.size(); i++){

					Main_list_definition item = new Main_list_definition("Band Name: " + bandName.get(listOfNames.get(i)).toString(),
																		 "Number of Members: " + bandSize.get(listOfNames.get(i)).toString());
					bandNameAndSizeList.add(item);
					adapter.notifyDataSetChanged();
				}	
			}
		});
	}
	
	
	
	public void clearOutHashMaps(){
		
		// **** clear out all the arrays and hashmaps **** //
		bandName.clear();
		bandSize.clear();
		listOfNames.clear();
		bandNameAndSizeList.clear();
		objects.clear();
		adapter.notifyDataSetChanged();
	}
	
	
	
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		// **** unregister the reciever **** //
		getActivity().unregisterReceiver(networkStateReceiver);
	}


	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// **** registering the reciever **** //
		getActivity().registerReceiver(networkStateReceiver, filter);
	}
	
}
