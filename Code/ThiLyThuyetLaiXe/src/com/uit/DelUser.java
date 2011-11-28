package com.uit;

import objects.Person;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class DelUser extends Activity{

	Button btnDel;
	String[] usernames;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deluser);
		
		//String[] usernames is a list of username query from database
		usernames = getListofUserName();
		
		//Create arraydapter <String> to save list of username query from database
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, usernames);
		
		final AutoCompleteTextView txtUserName = (AutoCompleteTextView)findViewById(R.id.autoCompleteUserName);
		txtUserName.setThreshold(1);
		txtUserName.setAdapter(adapter);
		
		//create a instance of Person
		final Person p = new Person(this); 
		
		//button delete click event
		btnDel = (Button)findViewById(R.id.btnDel);
		btnDel.setOnClickListener(new Button.OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(txtUserName.getText() == null){
					Toast.makeText(getBaseContext(), "Enter username will delete!", Toast.LENGTH_SHORT).show();
				}
				else{					
					int id = -1;
					p.open();
					Cursor c = p.getAllUsers();
					if(c.moveToFirst()){
						do{
							if(p.CheckName(c, txtUserName.getText().toString())){							
								id = c.getInt(0);
								break;
							}						
						}while(c.moveToNext());
					}
					//if don't exists username entered in database, warning!
					if(id == -1){
						Toast.makeText(getBaseContext(), "Don't exists this username in database!", Toast.LENGTH_SHORT).show();
					}
					else
					{
						//delete user name entered
						p.deleteUser(id);
						Toast.makeText(getBaseContext(), "Delete successfully!", Toast.LENGTH_SHORT).show();
						
						//update String[]
						txtUserName.setText("");
						txtUserName.setFocusable(true);
						usernames = getListofUserName();
					}					
					p.close();
				}	
			}			
		});		
	}
	
	//function query list of username from database after click button delete
	String[] getListofUserName()
	{
		
		//create a Person instance
		Person user = new Person(this);
		user.open();
		
		Cursor c = user.getAllUsers();
		int count = c.getCount();
		//Toast.makeText(getBaseContext(), ((Integer)count).toString(), Toast.LENGTH_SHORT).show();
		int index = 0;
		String[] result = new String[count];
		if(c.moveToFirst()){		
			do{
				//add name to list String
				result[index++] = c.getString(1).toString();				
			}while(c.moveToNext());
		}		
		user.close();
		
		return result;
	}
}
