package com.Cory.gigbag;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class New_Account extends Activity{

	EditText userName;
	EditText passWord;
	EditText email;
	
	TextView userNameTextView;
	TextView passwordTextView;
	TextView emailTextView;
	TextView mainLabel;
	
	Button createAccountButton;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);
        
        userName = (EditText)findViewById(R.id.user_name_edit_text);
        passWord = (EditText)findViewById(R.id.password_edit_text);
        email = (EditText)findViewById(R.id.email_edit_text);
        
        userNameTextView = (TextView)findViewById(R.id.user_name_account_create_text);
        passwordTextView = (TextView)findViewById(R.id.password_account_create_text);
        emailTextView = (TextView)findViewById(R.id.email_account_create_text);
        mainLabel = (TextView)findViewById(R.id.main_create_account_text);
        
        createAccountButton = (Button)findViewById(R.id.create_account_button_account_create);
        
        
        
        
        
	}
	
}
