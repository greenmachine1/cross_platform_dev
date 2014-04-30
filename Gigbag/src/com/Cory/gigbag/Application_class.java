package com.Cory.gigbag;

import android.app.Application;

import com.parse.Parse;
import com.parse.PushService;

// **** creating a singleton class to handle app wide **** //
// **** info including parse initialization info **** //
public class Application_class extends Application{
	
	private static Application_class singleton;
	
	public Application_class getInstance(){
		return singleton;
		
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		singleton = this;
		
        // **** initializing my parse stuff **** //
        Parse.initialize(getApplicationContext(), "qpsrDDopYR4JfloQD3IMCm5oEAn9D0kAFoX0Xmec", 
        		"uqZYdwJyr8DJ9LiMhQiKy2683tZsHu36q0nUP7Bp");
        
        PushService.setDefaultPushCallback(this, MainActivity.class);
	}

}
