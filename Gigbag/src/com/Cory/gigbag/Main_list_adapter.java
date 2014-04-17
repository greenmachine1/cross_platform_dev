package com.Cory.gigbag;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Main_list_adapter extends ArrayAdapter<Main_list_definition>{

	Context context;
	int layoutId;
	List<Main_list_definition> data = null;
	
	
	public Main_list_adapter(Context context, int resource,
			List<Main_list_definition> data) {
		super(context, resource, data);
		
		this.data = data;
		this.context = context;
		this.layoutId = resource;
		
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View row = convertView;
		MainListAdapterHolder holder = null;
		if(row == null){
			
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutId, parent, false);
			
			holder = new MainListAdapterHolder();
			holder.bandNameTitle = (TextView)row.findViewById(R.id.band_name_text_xml);
			holder.bandSizeTitle = (TextView)row.findViewById(R.id.band_size_text_xml);
			
			row.setTag(holder);
			
		}else{
			
			holder = (MainListAdapterHolder)row.getTag();
		}
		Main_list_definition ingredients_adapter = getItem(position);
		
		holder.bandNameTitle.setText(ingredients_adapter.bandNameTextView);
		holder.bandSizeTitle.setText(ingredients_adapter.bandSizeTextView);

		return row;
	}
	
	static class MainListAdapterHolder{
		TextView bandNameTitle;
		TextView bandSizeTitle;
	}
	
	

}
