package com.uit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BaseActivity extends Activity{
	Button btnAdduser, btnDeluser;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnAdduser = (Button)findViewById(R.id.btnAddUser);
        btnDeluser = (Button)findViewById(R.id.btnDelUser);
        
        btnAdduser.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//create intent adduser form
				startActivity(new Intent("com.uit.ADDUSER"));
			}
		});
        
        btnDeluser.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//create intent deluser form
				startActivity(new Intent("com.uit.DELUSER"));
			}
		});
        
    }
}
