package com.uit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class LuyenThi extends Activity{
	Integer[] imageIDs = {
			R.drawable.de1,
			R.drawable.de2,
			R.drawable.de3,
			R.drawable.de4,
			R.drawable.de5,
			R.drawable.de6,
			R.drawable.de7,
			R.drawable.de8,
			R.drawable.de9,
			R.drawable.de10,
			R.drawable.de11,
			R.drawable.de12,
			R.drawable.de13,
			R.drawable.de14,
			R.drawable.de15,
			R.drawable.de16,
			R.drawable.de17,
			R.drawable.de18,
			R.drawable.de19,
			R.drawable.de20,
			
			
	};
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.luyenthi_chonde);
        
        GridView gridView = (GridView)findViewById(R.id.gridviewChonDeLuyenThi);
        gridView.setAdapter(new ImageAdapter(this));
        
        gridView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "Chon de: "+(arg2 + 1), Toast.LENGTH_SHORT).show();
			}
		});
    }
    
    public class ImageAdapter extends BaseAdapter {
    	private Context context;
    	public ImageAdapter(Context c)
    	{
    		context  = c;
    		
    	}
		public int getCount() {
			// TODO Auto-generated method stub
			return imageIDs.length;
		}
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ImageView imageView;
			if(convertView == null)
			{
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(5, 5, 5, 5);
			}
			else
			{
				imageView = (ImageView)convertView;
			}
			imageView.setImageResource(imageIDs[position]);
			return imageView;
		}
	}
}
