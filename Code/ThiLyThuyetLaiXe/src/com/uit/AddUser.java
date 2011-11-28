package com.uit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import objects.Person;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUser extends Activity {
    
	//Initialize parameter
	private Button btnRegister;
	private EditText txtName;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adduser);
        try{
        	String destPath = "/data/data/" + getPackageName() + 
        			"/databases/lythuyetlaixe";
        	File f = new File(destPath);
        	if(!f.exists()){
        		CopyDB(getBaseContext().getAssets().open("lythuyetlaixe"), 
        				new FileOutputStream(destPath));
        	}    
        	
        }
        catch(FileNotFoundException e){
        	e.printStackTrace();
        }
        catch(IOException e){
        	e.printStackTrace();
        }
        
        //get data from user input
        btnRegister = (Button)findViewById(R.id.buttonRegister);
        txtName = (EditText)findViewById(R.id.txtUsername);
       
        
        final Person user = new Person(this);
        
        btnRegister.setOnClickListener(new Button.OnClickListener(){
        	
			public void onClick(View arg0) {
				boolean ok = true; //check name is true and don't exists
				
				//get all users
		        user.open();
		        Cursor c = user.getAllUsers();
				if(c.moveToFirst()){
		        	do{
		        		//check name is not null
		        		if(txtName.getText().toString() == null){
		        			Toast.makeText(getBaseContext(), "User name must not null!", Toast.LENGTH_SHORT).show();
		        			txtName.setFocusable(true);
		        			ok = false;
		        			break;
		        		}
		        		//check name is not exists in database
		        		if(user.CheckName(c, txtName.getText().toString()))
		        		{
		        			Toast.makeText(getBaseContext(), "User name was existed!", Toast.LENGTH_SHORT).show();
		        			txtName.setFocusable(true);
		        			ok = false;
		        			break;
		        		}
		        		//check email rule is true
		        		
		        	}while(c.moveToNext());
		        }
				
				
				//insert data
				if(ok){
					//insert to database
					long i = user.insertUser(txtName.getText().toString());
					
					if(i != -1)
						Toast.makeText(getBaseContext(), "Insert successfully!", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(getBaseContext(), "Insert failed!", Toast.LENGTH_SHORT).show();
					txtName.setFocusable(true);
					txtName.setText("");
				}
				
				//close adapter
				user.close();
			}        	
        });
    }
    
    public void CopyDB(InputStream inputStream, OutputStream outputStream) throws IOException{
    	//copy 1k bytes at a time
    	byte[] buffer = new byte[1024];
    	int length;
    	while((length = inputStream.read(buffer)) > 0){
    		outputStream.write(buffer, 0, length);
    	}
    	inputStream.close();
    	outputStream.close();
    }
    
    //function to display user from cursor
    public void DisplayContact(Cursor c){
    	Toast.makeText(this, "ID: " + c.getInt(0) + "\n"
    			+ "Name: " + c.getString(1) + "\n"
    			+ "Email: " + c.getString(2), Toast.LENGTH_LONG).show();
    }
    
    
}