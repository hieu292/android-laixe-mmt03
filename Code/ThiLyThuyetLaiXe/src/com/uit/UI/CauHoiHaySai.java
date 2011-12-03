package com.uit.UI;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.uit.R;
import com.uit.Functions.TwoColorAdapter;
import com.uit.Providers.CauHoi;

/*
 * Purpose....
 * the questions usually wrong. When touch the question, will tell you answer this question
 * and how you can remember this answer. 
 */
public class CauHoiHaySai extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final String[] noidung = new String[30];// 30 question is usually wrong
		int[] idCauhoi = new int[30];
		final String[] luachon1 = new String[30];
		final String[] luachon2 = new String[30];
		final String[] luachon3 = new String[30];
		final String[] luachon4 = new String[30];
		final String[] dapan = new String[30];
		String[] hinhanh = new String[30];
		int[] solantraloisai = new int[30];

		// get data from database
		//
		int index = 0;
		CauHoi ch = new CauHoi(this);
		ch.open();
		Cursor c = ch.getMostWrongRows();
		if (c.moveToFirst()) {
			do {
				idCauhoi[index] = c.getInt(0);
				noidung[index] = String.valueOf(index + 1) + ": "
						+ c.getString(1);
				luachon1[index] = c.getString(2);
				luachon2[index] = c.getString(3);
				luachon3[index] = c.getString(4);
				luachon4[index] = c.getString(5);
				dapan[index] = c.getString(6);
				hinhanh[index] = c.getString(7);
				solantraloisai[index] = c.getInt(9);

				index++;
			} while (c.moveToNext() && index < 30);
		}
		ch.close();
		setListAdapter(new TwoColorAdapter(this, R.layout.thongkecauhoihaysai,
				noidung));

		final int temp = index;

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String str = ((TextView) view).getText().toString();
				int i = 0;// luu giu vi tri cua cau hoi trong mang
				for (; i < temp; i++) {
					if (str.equals(noidung[i])) {
						break;
					}
				}

				String show = str + "\n \n";
				show += "A: " + (luachon1[i]) + "\n \n";
				show += "B: " + (luachon2[i]) + "\n \n";
				if (luachon3[i] != null)
					show += "C: " + (luachon3[i]) + "\n \n";
				if (luachon4[i] != null && !(luachon4[i]).trim().endsWith(""))
					show += "D: " + (luachon4[i]) + "\n \n";
				show += "Đáp án: " + dapan[i];

				createDetailDialog(show);

				// truong hop co hinh anh, se hien thi hinh anh sau
				//
			}
		});
	}

	public void createDetailDialog(CharSequence charSequence) {
		final TextView input = new TextView(this);
		input.setText(charSequence);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(input);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
