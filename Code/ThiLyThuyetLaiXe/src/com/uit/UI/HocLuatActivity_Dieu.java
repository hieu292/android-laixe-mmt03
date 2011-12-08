package com.uit.UI;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.uit.R;
import com.uit.Functions.LuatListAdapter;
import com.uit.Providers.LuatDB;
import com.uit.objects.Luat;

/**
 * Demonstrates expandable lists using a custom {@link ExpandableListAdapter}
 * from {@link BaseExpandableListAdapter}.
 */
public class HocLuatActivity_Dieu extends Activity {

	TextView txtInfo;
	ListView list_noidung;
	ArrayList<Luat> list_luat = new ArrayList<Luat>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hocluat);
		int chuong = getIntent().getExtras().getInt(
				HocLuatActivity_Chuong.ID_CHUONG);
		// Log.d("check", "" + chuong);
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> contents = new ArrayList<String>();
		

		list_luat = new LuatDB(this).getDieu(chuong, LuatDB.ID_DIEU);

		for (int i = 0; i < list_luat.size(); i++) {
			names.add(list_luat.get(i).getNoidung());
		}

		txtInfo = (TextView) findViewById(R.id.luat_txtInfo);
		list_noidung = (ListView) findViewById(R.id.luat_listview);
		LuatListAdapter adapter = new LuatListAdapter(this, names, contents);
		list_noidung.setAdapter(adapter);

		list_noidung.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Luat muc = new LuatDB(getApplicationContext())
						.getLuatwithId(list_luat.get(position).getId() + 1);
				createDialogInfo(list_luat.get(position).getNoidung(), muc.getNoidung());
			}
		});
	}

	private void createDialogInfo(String dieu, String _noidung) {
		String[] noidung = null;
		noidung = new String[] { _noidung };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		ListView txt =  new ListView(this);
		txt.setAdapter( new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, noidung));
		txt.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
		
		builder.setTitle(dieu);
		// builder.setIcon(R.drawable.s_icon_file);
		builder.setView(txt);
//		builder.setItems(noidung, new OnClickListener() {
//
//			public void onClick(DialogInterface dialog, int which) {
//
//			}
//		});
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
