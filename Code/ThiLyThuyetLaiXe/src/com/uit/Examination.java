package com.uit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Examination extends Activity{
	Button btnLuyenThi, btnThiThu, btnXemKetQua;
	TextView textViewUserName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exam);
		
		//get data
		final String getdata = getIntent().getExtras().getString("UserName");
		//Toast.makeText(this, getdata, Toast.LENGTH_SHORT).show();
		//
		textViewUserName = (TextView)findViewById(R.id.textViewUserName);
		textViewUserName.setText("Chào bạn: " +getdata);
		
		//
		btnLuyenThi = (Button)findViewById(R.id.btnLuyenThi);
		btnThiThu = (Button)findViewById(R.id.btnThiThu);
		btnXemKetQua = (Button)findViewById(R.id.btnXemKetQua);
		
		btnLuyenThi.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i =new Intent("com.uit.LUYENTHI");
				Bundle extras = new Bundle();				
				extras.putString("UserName", getdata);
				i.putExtras(extras);
				startActivity(i);
			}
		});
		
	}
}
