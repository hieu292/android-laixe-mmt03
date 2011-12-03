package com.uit.Objects;

import com.uit.Providers.Person;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

public class UserAction {
	public final static String ACCOUNT = "account";
	public final static String NAME = "name";
	
	private Context context;
	final Person user;

	public UserAction(Context context) {
		super();
		this.context = context;
		user = new Person(context);
		
	}

	public String AddUser(String username){
		String result = "";		
		boolean ok = true; //check name is true and don't exists
		
		//get all users
        user.open();
        Cursor c = user.getAllUsers();
		if(c.moveToFirst()){
        	do{
        		//check name is not null
        		if(username == null || username.equals("")){
        			result = "Tên tài khoản không thể để trống";
        			ok = false;
        			break;
        		}
        		//check name is not exists in database
        		if(user.CheckName(c, username))
        		{
        			result = "Tên tài khoản đã tồn tại";
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
				result =  "Thêm tài khoản thành công!";
			else
				result = "Thêm tài khoản thất bại";
		}
		
		//close adapter
		user.close();
		return result;
	}
	
	public String DelUser(String username){
		String result;	
		
		final Person p = new Person(context);
		if(username == null){
			result = "Vui lòng ch�?n tài khoản";
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
				result = "Không tồn tại tài khoản này trong cơ sở dữ liệu";
			}
			else
			{
				//delete user name entered
				p.deleteUser(id);
				result = "Xóa tài khoản thành công!";
			}					
			p.close();
		}		
		return result;
	}
	
	

	public void storeInformation(String username) {
		SharedPreferences account = context.getSharedPreferences(ACCOUNT, 0);
		SharedPreferences.Editor editor = account.edit();
		editor.putString(NAME, username);
		editor.commit();
		
	}
	
	public void removeSaveInfo(){
		SharedPreferences account = context.getSharedPreferences(ACCOUNT, 0);
		SharedPreferences.Editor editor = account.edit();
		editor.remove(ACCOUNT);
		editor.commit();
	}
	
	/**
	 * function query list of username from database after click button delete
	 * @param _context
	 * @return
	 */
  	public String[] getListofUserName(Context _context)
  	{
  		
  		//create a Person instance
  		Person user = new Person(_context);
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
  	
  /**
   * function to check name of user
   * @param c
   * @param _name
   * @return
   */
    public boolean CheckName(Cursor c, String _name){
    	String str = c.getString(1);
    	if(str.equals(_name))
    		return true;    	return false;
    }

}