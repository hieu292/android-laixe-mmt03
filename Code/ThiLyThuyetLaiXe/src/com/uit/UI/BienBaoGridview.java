package com.uit.UI;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.uit.R;
import com.uit.Functions.BienBaoAdapter;
import com.uit.Objects.BienBao;
import com.uit.Providers.BienBaoDB;
import com.uit.Providers.HashmapDB;

public class BienBaoGridview extends Activity {
	GridView gridview;
	ArrayList<BienBao> list_bienbao = new ArrayList<BienBao>();
	HashMap<String, Integer> map = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bienbao_main);
		gridview = (GridView) findViewById(R.id.grid_bienbao);

		// get data from database
		//
		map = new HashmapDB().getMap();
		list_bienbao = new BienBaoDB(this).get_list_bienbao(BienBaoSwitcher.ID_ALL);

		gridview.setAdapter(new BienBaoAdapter(0, list_bienbao.size() - 1, getLayoutInflater(),
				list_bienbao, map));

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(BienBaoGridview.this,
						BienBaoSwitcher.class);
				i.putExtra("id", position);
				startActivity(i);
			}
		});
	}


}
