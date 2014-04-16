package com.Cory.gigbag;



// **** main list defintion to use in list fragment adapter **** //
public class Main_list_definition {

	public String bandNameTextView;
	public String bandSizeTextView;
	
	
	public Main_list_definition(){
		super();
	}
	
	public Main_list_definition(String bandNameText, String bandSizeText){
		super();
		
		this.bandNameTextView = bandNameText;
		this.bandSizeTextView = bandSizeText;
	}
	
}
