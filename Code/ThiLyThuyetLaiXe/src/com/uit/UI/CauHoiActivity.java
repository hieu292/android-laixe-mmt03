package com.uit.UI;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

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
public class CauHoiActivity extends ListActivity {
	private static final String LUA_CHON1 = "A. ";
	private static final String LUA_CHON2 = "B. ";
	private static final String LUA_CHON3 = "C. ";
	private static final String LUA_CHON4 = "D. ";
	HashMap<String, Integer> map_hinhanh = new HashMap<String, Integer>();

	ArrayList<CauHoi> list_cauhoi = new ArrayList<CauHoi>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		map_hinhanh = new HashmapDB().getMapImageCauHoi();
		String loai_cauhoi = getIntent().getExtras().getString(
				HocDeThiActivity.LOAI_CAUHOI);

		String[] noidung = createListCauhoi(loai_cauhoi);

		setListAdapter(new TwoColorAdapter(this,
				R.layout.activity_thongke_content, noidung));

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				createDetailDialog(list_cauhoi.get(position));
			}
		});
	}

	private String[] createListCauhoi(String loai_cauhoi) {
		int[] list_loai;
		if (loai_cauhoi.equals(HocDeThiActivity.CAUHOI_LUAT)) {
			list_loai = new int[] { 1, 2, 3, 4, 5, 6 };
		} else if (loai_cauhoi.equals(HocDeThiActivity.CAUHOI_BIENBAO)) {
			list_loai = new int[] { 7 };
		} else {
			list_loai = new int[] { 8 };
		}
		list_cauhoi = new CauHoiDB(this).getTheLoai(list_loai);
		String[] noidung = new String[list_cauhoi.size()];
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

		if (cauhoi.getLuachon3() != null && cauhoi.getLuachon4() == null) {
			luachon = new String[] { luachon1, luachon2, luachon3 };
		} else if (cauhoi.getLuachon4() != null
				&& !(cauhoi.getLuachon4()).trim().endsWith("")) {
			luachon = new String[] { luachon1, luachon2, luachon3, luachon4 };
		} else {
			luachon = new String[] { luachon1, luachon2 };
		}

		AlertDialog.Builder builder;
		LayoutInflater inflater = (LayoutInflater) getApplicationContext()
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.dialog_cauhoi,
				(ViewGroup) findViewById(R.id.dialog_root));

		TextView txtCauHoi = (TextView) layout
				.findViewById(R.id.dialog_content);

		txtCauHoi.setText("Câu " + cauhoi.getId() + ": " + cauhoi.getNoidung());

		final TextView txtDapAn = (TextView) layout
				.findViewById(R.id.dialog_dapan);

		if (cauhoi.getHinhanh() != null && !cauhoi.getHinhanh().equals("")) {
			ImageView image = (ImageView) layout
					.findViewById(R.id.dialog_image);
			image.setImageResource(((Integer) map_hinhanh.get(cauhoi
					.getHinhanh())).intValue());
		}

		ListView list = (ListView) layout.findViewById(R.id.dialog_listview);
		list.setAdapter(new ArrayAdapter<String>(this,
				R.layout.dialog_listview, luachon));
		list.setItemsCanFocus(false);
		ContextThemeWrapper ctw = new ContextThemeWrapper(this, R.style.MyTheme);
		builder = new AlertDialog.Builder(ctw);
		builder.setView(layout);

		final Button btnDapan = (Button) layout.findViewById(R.id.dialog_btnDapan);
		btnDapan.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				txtDapAn.setText("Đáp án: " + cauhoi.getDapan());
				btnDapan.setText("Đáp án: " + cauhoi.getDapan());
//				btnDapan.
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
		builder.setCancelable(true);
		builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		AlertDialog dialog = builder.create();
		dialog.getWindow().setLayout(
				android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,
				android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
		dialog.show();
	}
}
