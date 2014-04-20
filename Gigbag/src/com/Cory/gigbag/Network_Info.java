package com.Cory.gigbag;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

// **** creation of a singleton class **** //
public class Network_Info {

	private static Network_Info info = null;
	
	ConnectivityManager cm;
	NetworkInfo activeNetworkWiFi;
	NetworkInfo activeNetworkMobile;
	boolean isConnected;
	
	Context _context;
	
	// **** private constructor **** //
	private Network_Info(){
	}
	
	// **** basically asking if one instance of **** //
	// **** this class exsists and if it does **** //
	// **** do not create a new one **** //
	public static Network_Info getInstance(){
		if(info == null){
			info = new Network_Info();
		}
		return info;	
	}
	
	public void detectNetworkStatus(Context context){
		
		_context = context;
		
		// **** gathering connection status **** //
        cm = (ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetworkWiFi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        activeNetworkMobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        isConnected = activeNetworkWiFi != null && (activeNetworkWiFi.isConnected() || (activeNetworkMobile.isConnected()));
	}
	
	
	// **** returns the status **** //
	public boolean returnStatus(){
		
		return isConnected;
	}
	
}
