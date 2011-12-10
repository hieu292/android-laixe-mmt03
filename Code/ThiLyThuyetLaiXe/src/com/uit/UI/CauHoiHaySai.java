package com.uit.UI;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.uit.R;
import com.uit.Functions.TwoColorAdapter;
import com.uit.Providers.CauHoiDB;
import com.uit.Providers.HashmapDB;
import com.uit.objects.CauHoi;

/*
 * Purpose....
 * the questions usually wrong. When touch the question, will tell you answer this question
 * and how you can remember this answer. 
 */
public class CauHoiHaySai extends ListActivity {
	protected static final String LUA_CHON1 = "A. ";
	protected static final String LUA_CHON2 = "B. ";
	protected static final String LUA_CHON3 = "C. ";
	protected static final String LUA_CHON4 = "D. ";
	protected HashMap<String, Integer> map_hinhanh = new HashMap<String, Integer>();

	protected ArrayList<CauHoi> list_cauhoi = new ArrayList<CauHoi>();

	private static final int SOCAU_HIENTHI = 30;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		map_hinhanh = new HashmapDB().getMapImageCauHoi();
		
		list_cauhoi = new CauHoiDB(this).getMostWrongs(SOCAU_HIENTHI);

//		final String[] noidung = new String[30];// 30 question is usually wrong
//		int[] idCauhoi = new int[30];
//		final String[] luachon1 = new String[30];
//		final String[] luachon2 = new String[30];
//		final String[] luachon3 = new String[30];
//		final String[] luachon4 = new String[30];
//		final String[] dapan = new String[30];
//		String[] hinhanh = new String[30];
//		int[] solantraloisai = new int[30];
//
//		// get data from database
//		//
//		int index = 0;
//		CauHoiDB ch = new CauHoiDB(this);
//		ch.open();
//		Cursor c = ch.getMostWrongRows();
//		if (c.moveToFirst()) {
//			do {
//				idCauhoi[index] = c.getInt(0);
//				noidung[index] = String.valueOf(index + 1) + ": "
//						+ c.getString(1);
//				luachon1[index] = c.getString(2);
//				luachon2[index] = c.getString(3);
//				luachon3[index] = c.getString(4);
//				luachon4[index] = c.getString(5);
//				dapan[index] = c.getString(6);
//				hinhanh[index] = c.getString(7);
//				solantraloisai[index] = c.getInt(9);
//
//				index++;
//			} while (c.moveToNext() && index < 30);
//		}
//		ch.close();
		String[] noidung = createListCauhoi();
		setListAdapter(new TwoColorAdapter(this,
				R.layout.activity_thongke_content, noidung));

//		final int temp = index;

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setScrollingCacheEnabled(false);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				createDetailDialog(list_cauhoi.get(position));
//				String str = ((TextView) view).getText().toString();
//				int i = 0;// luu giu vi tri cua cau hoi trong mang
//				for (; i < temp; i++) {
//					if (str.equals(noidung[i])) {
//						break;
//					}
//				}
//
//				String show = str + "\n \n";
//				show += "A: " + (luachon1[i]) + "\n \n";
//				show += "B: " + (luachon2[i]) + "\n \n";
//				if (luachon3[i] != null)
//					show += "C: " + (luachon3[i]) + "\n \n";
//				if (luachon4[i] != null && !(luachon4[i]).trim().endsWith(""))
//					show += "D: " + (luachon4[i]) + "\n \n";
//				show += "Đáp án: " + dapan[i];
//
//				createDetailDialog(show);
//
//				// truong hop co hinh anh, se hien thi hinh anh sau
//				//
			}
		});
	}

//	// hien thi dialog cho cau hoi onclick
//	private void createDetailDialog(CharSequence charSequence) {
//		final TextView input = new TextView(this);
//		input.setText(charSequence);
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setView(input);
//		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int whichButton) {
//				dialog.dismiss();
//			}
//		});
//		AlertDialog dialog = builder.create();
//		dialog.show();
//	}
	
	public String[] createListCauhoi() {
		String[] noidung = new String[list_cauhoi.size()];
		//set noi dung
		for (int i = 0; i < list_cauhoi.size(); i++) {
			int idCau = list_cauhoi.get(i).getId();
			noidung[i] = "Câu " + idCau + ": "
					+ list_cauhoi.get(i).getNoidung();
		}
		return noidung;
	}
	
	public void createDetailDialog(final CauHoi cauhoi) {
		String[] luachon;
		String luachon1 = LUA_CHON1 + cauhoi.getLuachon1();
		String luachon2 = LUA_CHON2 + cauhoi.getLuachon2();
		String luachon3 = LUA_CHON3 + cauhoi.getLuachon3();
		String luachon4 = LUA_CHON4 + cauhoi.getLuachon4();

		// tạo chuỗi lựa chọn cho adapter listview
		if (!cauhoi.getLuachon3().equals("") && cauhoi.getLuachon4().equals("")) {
			luachon = new String[] { luachon1, luachon2, luachon3 };
		} else if (cauhoi.getLuachon4() != null
				&& !(cauhoi.getLuachon4()).equals("")) {
			luachon = new String[] { luachon1, luachon2, luachon3, luachon4 };
		} else {
			luachon = new String[] { luachon1, luachon2 };
		}

		AlertDialog.Builder builder;
		LayoutInflater inflater = (LayoutInflater) getApplicationContext()
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.dialog_cauhoi,
				(ViewGroup) findViewById(R.id.dialog_root));
		// TextView nội dung câu hỏi
		TextView txtCauHoi = (TextView) layout
				.findViewById(R.id.dialog_content);

		txtCauHoi.setText("Câu " + cauhoi.getId() + ": " + cauhoi.getNoidung());

		// TextView đáp án
		final TextView txtDapAn = (TextView) layout
				.findViewById(R.id.dialog_dapan);

		// set hình ảnh cho câu hỏi (nếu có)
		// dùng hashmap
		if (cauhoi.getHinhanh() != null && !cauhoi.getHinhanh().equals("")) {
			ImageView image = (ImageView) layout
					.findViewById(R.id.dialog_image);
			image.setImageResource(((Integer) map_hinhanh.get(cauhoi
					.getHinhanh())).intValue());
		}

		// set listview hiển thị những đáp án của câu hỏi
		ListView list = (ListView) layout.findViewById(R.id.dialog_listview);
		list.setAdapter(new ArrayAdapter<String>(this,
				R.layout.dialog_listview, luachon));
		list.setItemsCanFocus(false);
		ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.MyTheme);
		builder = new AlertDialog.Builder(ctw);
		builder.setView(layout);

		final Button btnDapan = (Button) layout
				.findViewById(R.id.dialog_btnDapan);
		final AlertDialog dialog = builder.create();
		btnDapan.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				if (btnDapan.getText().equals("  Xem đáp án")) {
					btnDapan.setText(" OK!!!");
					txtDapAn.setText("Đáp án: " + cauhoi.getDapan());
				} else {
					dialog.dismiss();
				}
			}
		});

		//
		// ImageView abc = new ImageView(this);
		// abc.setBackgroundResource(R.drawable.t_icon_huongdan);
		// final TextView input = new TextView(this);
		// input.setText(noidung);
		// input.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
		// LayoutParams.FILL_PARENT));
		// AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setView(input);
		// builder.setView(abc);
		// builder.setCancelable(true);
		// builder.setNegativeButton("OK", new DialogInterface.OnClickListener()
		// {
		//
		// public void onClick(DialogInterface dialog, int which) {
		// dialog.dismiss();
		// }
		// });

		dialog.getWindow().setLayout(
				android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
				android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dialog.show();
	}

}
