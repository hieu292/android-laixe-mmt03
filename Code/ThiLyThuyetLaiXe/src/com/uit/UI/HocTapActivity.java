package com.uit.UI;

import com.uit.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HocTapActivity extends Activity {
	
	ImageButton btnBB;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hoctap);
		
		btnBB = (ImageButton) findViewById(R.id.t_btnBB);
		btnBB.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(HocTapActivity.this, BienBaoGridview.class);
				startActivity(i);
			}
		});
	}
}
