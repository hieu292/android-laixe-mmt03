package com.uit.UI;

import com.uit.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThongKeActivity extends Activity {

	Button btnXepLoai, btnThongKe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thongke);
		btnXepLoai = (Button)findViewById(R.id.btnXepLoaiTop);
		btnThongKe = (Button)findViewById(R.id.btnThongKeCauHoiHaySai);
		
		btnXepLoai.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getBaseContext(), XepLoaiTop.class);
				startActivity(i);
			}
		});
		
		btnThongKe.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getBaseContext(), CauHoiHaySai.class);
				startActivity(i);
			}
		});
	}
	
}