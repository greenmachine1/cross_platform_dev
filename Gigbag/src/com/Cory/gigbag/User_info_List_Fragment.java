package com.Cory.gigbag;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



// **** class for dealing with the fragment
public class User_info_List_Fragment extends Fragment{

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view;
		
		view = inflater.inflate(R.layout.list_for_fragment, container, false);
		
		return view;
	}





}
