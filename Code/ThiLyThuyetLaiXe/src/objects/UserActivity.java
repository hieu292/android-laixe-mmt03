package objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.R;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class UserActivity {
	private Context context;
	final Person user;

	public UserActivity(Context context) {
		super();
		this.context = context;
		user = new Person(context);
		
	}

	public String AddUser(String username){
		String result = "";
		OpenDatabase();
		boolean ok = true; //check name is true and don't exists
		
		//get all users
        user.open();
        Cursor c = user.getAllUsers();
		if(c.moveToFirst()){
        	do{
        		//check name is not null
        		if(username == null){
        			result = "User name must not null!";
        			ok = false;
        			break;
        		}
        		//check name is not exists in database
        		if(user.CheckName(c, username))
        		{
        			result = "User name was existed!";
        			ok = false;
        			break;
        		}
        		//check email rule is true
        		
        	}while(c.moveToNext());
        }
		
		
		//insert data
		if(ok){
			//insert to database
			long i = user.insertUser(username);
			
			if(i != -1)
				result =  "Insert successfully!";
			else
				result = "Insert failed!";
		}
		
		//close adapter
		user.close();
		return result;
	}
	
	public String DelUser(String username){
		String result;	
		
		final Person p = new Person(context);
		// TODO Auto-generated method stub
		if(username == null){
			result = "Enter username will delete!";
		}
		else{					
			int id = -1;
			p.open();
			Cursor c = p.getAllUsers();
			if(c.moveToFirst()){
				do{
					if(p.CheckName(c, username)){							
						id = c.getInt(0);
						break;
					}						
				}while(c.moveToNext());
			}
			//if don't exists username entered in database, warning!
			if(id == -1){
				result = "Don't exists this username in database!";
			}
			else
			{
				//delete user name entered
				p.deleteUser(id);
				result = "Delete successfully!";
			}					
			p.close();
		}		
		return result;
	}
	/**
	 * 
	 */
	public void OpenDatabase() {
		try{
			String destPath = "/data/data/" + context.getPackageName() + 
    			"/databases/lythuyetlaixe";
			File f = new File(destPath);
			if(!f.exists()){
				CopyDB(context.getAssets().open("lythuyetlaixe"), 
    				new FileOutputStream(destPath));    
			}
		}
	    catch(FileNotFoundException e){
	    	e.printStackTrace();
	    }
	    catch(IOException e){
	    	e.printStackTrace();
	    }        	
	}

	public void CopyDB(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		// copy 1k bytes at a time
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}

//	// function to display user from cursor
//	public void DisplayContact(Cursor c) {
//		Toast.makeText(
//				this,
//				"ID: " + c.getInt(0) + "\n" + "Name: " + c.getString(1) + "\n"
//						+ "Email: " + c.getString(2), Toast.LENGTH_LONG).show();
//	}
}