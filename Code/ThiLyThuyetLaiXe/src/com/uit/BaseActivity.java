package com.uit;

import objects.Person;
import objects.UserActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends Activity{
	public final static String ACCOUNT = "account";
	public final static String NAME = "name";
	
	ImageButton btnAdduser, btnDeluser, btnLogin;
	TextView txtUsername;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        txtUsername = (TextView) findViewById(R.id.m_txtUsername);
        btnAdduser = (ImageButton) findViewById(R.id.m_btnTrain);
        
        checkLogin();
        
//        btnDeluser = (Button)findViewById(R.id.btnDelUser);
//        btnLogin = (Button)findViewById(R.id.btnLogin);
//        
        btnAdduser.setOnClickListener(new View.OnClickListener() {
			
        	public void onClick(View v) {
				// TODO Auto-generated method stub
				//create intent deluser form
				CreateUserDialog();
			}
		});
//        
//        btnDeluser.setOnClickListener(new View.OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				//create intent deluser form
//				startActivity(new Intent("com.uit.DELUSER"));
//			}
//		});
//        
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				startActivity(new Intent("com.uit.LOGIN"));
//			}
//		});
//        
    }
    
    public void checkLogin(){
    	SharedPreferences account = getSharedPreferences(ACCOUNT, 0);
    	String username = account.getString(NAME, null);
    	if(username == null || username.equals("")){
    		createLoginDialog();
    	}else{
    		txtUsername.setText(username);
    	}
    }
    
   public void createLoginDialog(){
		final EditText input = new EditText(this);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(input);
		builder.setCancelable(false);
		builder.setTitle("Input your Name...");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int whichButton) {
		    	String username = input.getText().toString().trim();
		    	UserActivity user = new UserActivity(getBaseContext());
		    	String result = user.AddUser(username);
		    	if(result.equals("Insert successfully!")){
		    		txtUsername.setText(username);
			    	storeInformation(username);
			    	dialog.dismiss();
		    	}else{
		    		checkLogin();
		    	}
		    	Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
		    }
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				checkLogin();
			}
		});
		AlertDialog dialog =  builder.create();
		dialog.show();
	}
   
   public void CreateUserDialog(){
	    String[] usernames;
	   	Person p = new Person(this);	
		//String[] usernames is a list of username query from database
		usernames = p.getListofUserName(this);
		
		//Create arraydapter <String> to save list of username query from database
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, usernames);
		
//		final AutoCompleteTextView txtUserName = (AutoCompleteTextView)findViewById(R.id.autoCompleteUserName);
		
		final AutoCompleteTextView txtInput = new AutoCompleteTextView(this);
		txtInput.setAdapter(adapter);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(txtInput);
		builder.setCancelable(false);
		builder.setTitle("Input your Name...");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int whichButton) {
		    	String username = txtInput.getText().toString().trim();
		    	UserActivity user = new UserActivity(getBaseContext());
		    	String result = user.DelUser(username);
		    	
		    	if(result.equals("Delete successfully!")){
		    		txtUsername.setText(username);
			    	storeInformation(username);
			    	dialog.dismiss();
		    	}else{
		    		checkLogin();
		    	}
		    	Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT).show();
		    }
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				checkLogin();
			}
		});
		AlertDialog dialog =  builder.create();
		dialog.show();
	}

   public void storeInformation(String username){
		SharedPreferences account = getSharedPreferences(ACCOUNT, 0);
		SharedPreferences.Editor editor = account.edit();
		editor.putString(NAME, username);
		editor.commit();
	}
   
}
