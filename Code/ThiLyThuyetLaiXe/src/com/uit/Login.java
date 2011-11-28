package com.uit;

import objects.Person;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Login extends ListActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Person p =new Person(this);
		String[] users = p.getListofUserName(this);
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.login, users));
		ListView list = getListView();
		list.setTextFilterEnabled(true);
		list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), ((TextView) arg1).getText().toString(), Toast.LENGTH_LONG).show();
				//go to intent luyenthi
				Intent i =new Intent("com.uit.EXAM");
				Bundle extras = new Bundle();
				
				extras.putString("UserName", ((TextView)arg1).getText().toString());				
				
				i.putExtras(extras);
				startActivity(i);				
			}			
		});
	}	
}
