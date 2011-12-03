package com.uit.Functions;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uit.R;

public class TwoColorAdapter extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] names;

	public TwoColorAdapter(Activity context, int layoutId, String[] names) {
		super(context, layoutId, names);
		this.context = context;
		this.names = names;
	}

	// static to save the reference to the outer class and to avoid access to
	// any members of the containing class
	static class ViewHolder {
		public TextView textView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ViewHolder will buffer the assess to the individual fields of the row
		// layout

		ViewHolder holder;
		// Recycle existing view if passed as parameter
		// This will save memory and time on Android
		// This only works if the base layout for all classes are the same
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.activity_thongke_content, null, true);
			holder = new ViewHolder();
			holder.textView = (TextView) rowView.findViewById(R.id.t_txtThongKe);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}

		holder.textView.setText(names[position]);
		if (position % 2 == 0) {

			holder.textView.setTextColor(Color.parseColor("#000033"));
		} else {
			holder.textView.setTextColor(Color.parseColor("#CC0000"));
		}
		return rowView;
	}
}
