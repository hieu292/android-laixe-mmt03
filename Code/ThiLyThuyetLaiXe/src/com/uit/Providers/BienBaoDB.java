package com.uit.Providers;

import java.util.ArrayList;

import com.uit.UI.BienBaoSwitcher;
import com.uit.objects.BienBao;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BienBaoDB {
	public static final String KEY_ID = "id";
	public static final String KEY_LINK = "link";
	public static final String KEY_TEN_BB = "tenbb";
	public static final String KEY_NOIDUNG = "noidung";
	public static final String KEY_PHANLOAI = "phanloai";

	private static final String TAG = "CauHoi";

	private static final String DATABASE_NAME = "lythuyetlaixe";
	private static final String DATABASE_TABLE = "bienbao";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table bienbao (id integer primary key not null, link text, "
			+ " tenbb text, noidung text, phanloai text";

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public BienBaoDB(Context context) {
		super();
		this.context = context;
		DBHelper = new DatabaseHelper(this.context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		// constructor
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		//

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(DATABASE_CREATE);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}

	// open the database
	public BienBaoDB open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	// close the database
	public void close() {
		DBHelper.close();
	}

	// insert a question into the database
	// this function don't need
	/*
	 * public long insertRow(Context _context, String name, long _ngaygiothi,
	 * long _thoigianhoanthanh, int _ketqua){ //get userid from name Person p =
	 * new Person(_context); Cursor user = p.getUser(name);
	 * 
	 * int userId = -1; if(user.moveToFirst()){ //get id userId =
	 * user.getInt(0); }
	 * 
	 * if(userId == -1){ Toast.makeText(_context,
	 * "Không tồn tại tài khoản này!", Toast.LENGTH_SHORT).show(); return -1; }
	 * else { ContentValues initialValues = new ContentValues();
	 * initialValues.put(KEY_USERID, userId); initialValues.put(KEY_NGAYGIOTHI,
	 * _ngaygiothi); initialValues.put(KEY_THOIGIANHOANTHANH,
	 * _thoigianhoanthanh); initialValues.put(KEY_KETQUA, _ketqua); return
	 * db.insert(DATABASE_TABLE, null, initialValues); } }
	 */

	// delete
	public boolean deleteAllRows() {
		return db.delete(DATABASE_TABLE, null, null) > 0;
	}

	// retrieves all rows
	public Cursor getAllRows() {
		return db.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_LINK,
				KEY_TEN_BB, KEY_NOIDUNG, KEY_PHANLOAI }, null, null, null,
				null, null);
	}

	public Cursor getSpecialRow(int loai) {
		return db.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_LINK,
				KEY_TEN_BB, KEY_NOIDUNG, KEY_PHANLOAI }, KEY_PHANLOAI + "="
				+ loai, null, null, null, null);
	}

	public ArrayList<BienBao> get_list_bienbao(int loai) {
		ArrayList<BienBao> list_bienbao = new ArrayList<BienBao>();
		int id;
		String linkanh = "";
		String tenbb = "";
		String noidung = "";
		String phanloai = "";
		BienBaoDB bb = new BienBaoDB(context);
		bb.open();
		Cursor c;
		if(loai == BienBaoSwitcher.ID_ALL){
			c = bb.getAllRows();
		}else{
			c = bb.getSpecialRow(loai);
		}
		if (c.moveToFirst()) {
			do {
				id = c.getInt(0);
				linkanh = c.getString(1);
				tenbb = c.getString(2);
				noidung = c.getString(3);
				phanloai = c.getString(4);
				BienBao bb_temp = new BienBao(id, linkanh, tenbb, noidung,
						phanloai);
				list_bienbao.add(bb_temp);
			} while (c.moveToNext());
		}
		bb.close();
		return list_bienbao;
	}
}
