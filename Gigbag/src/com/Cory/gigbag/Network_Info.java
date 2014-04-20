package com.Cory.gigbag;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// **** creation of a singleton class **** //
public class Network_Info {

	ConnectivityManager cm;
	NetworkInfo activeNetwork;
	boolean isConnected;
	
	
	// **** private constructor **** //
	private Network_Info(){}
	
	public void detectNetworkStatus(Context context){
		
		// **** gathering connection status **** //
        cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        isConnected = activeNetwork != null && activeNetwork.isConnected();
	}
	
	// **** returns the status **** //
	public boolean returnStatus(){
		return isConnected;
	}
	
}
