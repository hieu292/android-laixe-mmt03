package com.uit.UI;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.uit.R;
import com.uit.Providers.BienBaoDB;
import com.uit.Providers.HashmapDB;
import com.uit.objects.BienBao;
import com.uit.objects.PopupMenuItem;

public class BienBaoSwitcher extends Activity implements
		AdapterView.OnItemSelectedListener, ViewSwitcher.ViewFactory {
	public static final int ID_ALL = 0;
	public static final int ID_CAM = 1;
	public static final int ID_CHIDAN = 2;
	public static final int ID_HIEULENH = 3;
	public static final int ID_NGUYHIEM = 4;
	PopupMenuAction quickAction;

	HashMap<String, Integer> map = null;
	ArrayList<BienBao> list_bienbao;
	TextView txtTenBB, txtNoidung;
	Button btnMenu;
	Gallery g;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_bienbao_switcher);

		map = new HashmapDB().getMap();
		list_bienbao = new BienBaoDB(this).get_list_bienbao(ID_ALL);

		txtTenBB = (TextView) findViewById(R.id.switch_tenbb);
		txtNoidung = (TextView) findViewById(R.id.switch_noidung);
		btnMenu = (Button) findViewById(R.id.switch_btnMenu);
		mSwitcher = (ImageSwitcher) findViewById(R.id.switcher);
		mSwitcher.setFactory(this);
		mSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_in));
		mSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,
				android.R.anim.fade_out));

		g = (Gallery) findViewById(R.id.gallery);
		g.setAdapter(new ImageAdapter(this, 0, list_bienbao.size() - 1,
				list_bienbao, map));
		g.setOnItemSelectedListener(this);
		inti();
		btnMenu.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				quickAction.show(v);
				// quickAction.setAnimStyle(QuickAction.ANIM_REFLECT);
			}
		});
	}

	public void onItemSelected(AdapterView parent, View v, int position, long id) {
		BienBao localBienBao = (BienBao) this.list_bienbao.get(position);
		mSwitcher.setImageResource(((Integer) this.map
				.get(localBienBao.link_anh)).intValue());
		txtTenBB.setText(localBienBao.tenbb);
		txtNoidung.setText(localBienBao.noidung);
	}

	public void onNothingSelected(AdapterView parent) {

	}

	public View makeView() {
		ImageView i = new ImageView(this);
		i.setBackgroundColor(0xFF000000);
		i.setScaleType(ImageView.ScaleType.FIT_CENTER);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return i;
	}

	private ImageSwitcher mSwitcher;

	public class ImageAdapter extends BaseAdapter {
		Context context;
		int start;
		int end;
		ArrayList<BienBao> list_bienbao;
		HashMap<String, Integer> map = null;

		public ImageAdapter(Context c, int start, int end,
				ArrayList<BienBao> list_bienbao, HashMap<String, Integer> map) {
			super();
			this.context = c;
			this.start = start;
			this.end = end;
			this.list_bienbao = list_bienbao;
			this.map = map;
		}

		public int getCount() {
			return 1 + (this.end - this.start);
		}

		public Object getItem(int paramInt) {
			return this.list_bienbao.get(paramInt + this.start);
		}

		public long getItemId(int paramInt) {
			return paramInt;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ImageView image = new ImageView(context);
			BienBao localBienBao = (BienBao) list_bienbao.get(position
					+ this.start);
			image.setImageResource(((Integer) map.get(localBienBao.link_anh))
					.intValue());
			image.setAdjustViewBounds(true);
			image.setLayoutParams(new Gallery.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			// i.setBackgroundResource(R.drawable.picture_frame);
			return image;
		}
	}

	public void inti() {

		PopupMenuItem bienAll = new PopupMenuItem(ID_ALL, "Tất cả", getResources()
				.getDrawable(R.drawable.menu_bb_all));
		PopupMenuItem bienCam = new PopupMenuItem(ID_CAM, "Biển cấm", getResources()
				.getDrawable(R.drawable.menu_bb_cam));
		PopupMenuItem bienChiDan = new PopupMenuItem(ID_CHIDAN, "Biển chỉ dẫn",
				getResources().getDrawable(R.drawable.menu_bb_chidan));
		PopupMenuItem bienHieuLenh = new PopupMenuItem(ID_HIEULENH, "Biển hiệu lệnh", getResources()
				.getDrawable(R.drawable.menu_bb_hieulenh));
		PopupMenuItem bienNguyHiem = new PopupMenuItem(ID_NGUYHIEM, "Biển nguy hiểm", getResources()
				.getDrawable(R.drawable.menu_bb_nguyhiem));

//		// use setSticky(true) to disable QuickAction dialog being dismissed
//		// after an item is clicked
//		prevItem.setSticky(true);
//		nextItem.setSticky(true);

		// create QuickAction. Use QuickAction.VERTICAL or
		// QuickAction.HORIZONTAL param to define layout
		// orientation
		quickAction = new PopupMenuAction(this, PopupMenuAction.VERTICAL);

		// add action items into QuickAction
		quickAction.addActionItem(bienAll);
		quickAction.addActionItem(bienCam);
		quickAction.addActionItem(bienChiDan);
		quickAction.addActionItem(bienHieuLenh);
		quickAction.addActionItem(bienNguyHiem);

		// Set listener for action item clicked
		quickAction
				.setOnActionItemClickListener(new PopupMenuAction.OnActionItemClickListener() {
					public void onItemClick(PopupMenuAction source, int pos,
							int actionId) {
						PopupMenuItem actionItem = quickAction.getActionItem(pos);

						// here we can filter which action item was clicked with
						// pos or actionId parameter
						if (actionId == ID_ALL) {
							
						} else if (actionId == ID_CAM) {
							list_bienbao = new BienBaoDB(getApplicationContext()).get_list_bienbao(ID_CAM);
							g.setAdapter(new ImageAdapter(getApplicationContext(), 0, list_bienbao.size() - 1,
									list_bienbao, map));
						} else if (actionId == ID_CHIDAN) {

						} else if (actionId == ID_HIEULENH) {

						} else if (actionId == ID_NGUYHIEM) {

						} else {

						}
					}
				});

		// set listnener for on dismiss event, this listener will be called only
		// if QuickAction dialog was dismissed
		// by clicking the area outside the dialog.
		quickAction.setOnDismissListener(new PopupMenuAction.OnDismissListener() {
			public void onDismiss() {
				Toast.makeText(getApplicationContext(), "Dismissed",
						Toast.LENGTH_SHORT).show();
			}
		});

	}
}
