package com.uit.UI;

import java.util.Date;

import Functions.NgayThang;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.uit.objects.Person;
import com.uit.objects.ThongKe;

public class ThongKeActivity extends Activity {
	Person p;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Date testDate = new Date();
		Log.d("Time now!", ((Long)testDate.getTime()).toString());
		p = new Person(this);
		
		TableLayout table = new TableLayout(this);

        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

        TableRow rowLabels = new TableRow(this);
        TableRow[] rowDetail = new TableRow[10];
        
       
        

        // title column/row
        TextView title = new TextView(this);
        title.setText("Danh sách các kết quả tốt nhất");

        title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title.setGravity(Gravity.CENTER);
        title.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 4;

        rowTitle.addView(title, params);//add tieu de vao hang dau tien


        TextView[] tv = new TextView[4];
        for(int i=0; i<4; i++){
        	tv[i] = new TextView(this);
        	if(i==0){
        		tv[i].setText("Tên");
        	}
        	if(i==1){
        		tv[i].setText("Điểm");
        	}
        	if(i==2){
        		tv[i].setText("Thời gian");
        	}
        	if(i==3){
        		tv[i].setText("Ngày thi");
        	}
        	tv[i].setTypeface(Typeface.SERIF, Typeface.BOLD);
        	tv[i].setGravity(Gravity.CENTER);
        	
        	rowLabels.addView(tv[i]);
        }
        table.addView(rowTitle);
        table.addView(rowLabels);
        
        int[] name = new int[10];
        int[] diem = new int[10];
        long[] thoigianthi = new long[10];
        long[] ngaythi = new long[10];
        int index=0;
        
        ThongKe tk = new ThongKe(this);
        tk.open();
        Cursor c = tk.getTenRows();
        if(c.moveToFirst()){
        	do{
        		name[index] = c.getInt(0);
        		ngaythi[index] = c.getLong(1);
        		thoigianthi[index] = c.getLong(2);
        		diem[index] = c.getInt(3);
        		index++;//index toi da la 10
        		
        	}while(c.moveToNext());
        }
        tk.close();
        
        
        
        
        for(int i=0; i<index; i++){
        	rowDetail[i] = new TableRow(this);
        	for(int j=0; j<4; j++){
        		tv[j] = new TextView(this);
        		if(j==0){
        			p.open();
        			Cursor cUserName = p.getUser(name[i]);
        			String username = "Unknown";
        			if(cUserName.moveToFirst()){
        				username = cUserName.getString(1).toString();
        			}
        			tv[j].setText(username);
            		p.close();
            	}
            	if(j==1){
            		tv[j].setText(((Integer)diem[i]).toString());
            	}
            	if(j==2){
            		tv[j].setText(((Long)thoigianthi[i]).toString());
            	}
            	if(j==3){
            		//chuyen ve ngay thang nam
            		NgayThang nt = new NgayThang();
            		Date d = nt.FromLongToDate(ngaythi[i]);
            		//ngay - thang - nam
            		String ngay, thang, nam;
            		ngay = ((Integer)d.getDay()).toString();
            		thang = ((Integer)d.getMonth()).toString();
            		nam = ((Integer)d.getYear()).toString();
            		
            		tv[j].setText(ngay +"-"+ thang +"-"+ nam);
            	}
            	tv[j].setTypeface(Typeface.SERIF);
            	tv[j].setGravity(Gravity.CENTER);
            	
            	rowDetail[i].addView(tv[j]);
        	}
        	table.addView(rowDetail[i]);
        }
//        

        
        
        
        

        setContentView(table);
		
		//setContentView(R.layout.account);
	}
	
}
