package com.uit.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.uit.R;
import com.uit.objects.UserAction;

public class AccountActivity extends Activity {

	ImageButton btnAdd, btnDel, btnEdit, btnThongTin;
	
	public AccountActivity() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_account);
		
		btnAdd = (ImageButton) findViewById(R.id.a_btnAdd);
		btnDel = (ImageButton) findViewById(R.id.a_btnDel);
		btnThongTin = (ImageButton) findViewById(R.id.a_btnInfo);
		btnEdit = (ImageButton) findViewById(R.id.a_btnEdit);
		
		btnAdd.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				createAddUserDialog();
			}
		});

		btnThongTin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				CreateInfoDialog();
			}
		});

		btnDel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				CreateDelUserDialog();
			}
		});

		btnEdit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				createEditUserDialog();
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			Intent i = new Intent(AccountActivity.this, BaseActivity.class);
			startActivity(i);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	public void createAddUserDialog() {
		final EditText input = new EditText(this);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(input);
		builder.setCancelable(false);
		builder.setTitle("Input your Name...");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String username = input.getText().toString().trim();
				UserAction user = new UserAction(getBaseContext());
				String result = user.AddUser(username);
				if (result.equals("Thêm tài khoản thành công!")) {
					user.storeInformation(username);
					dialog.dismiss();
				} else {
					createAddUserDialog();
				}
				Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT)
						.show();
			}
		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						if(ListUser().length == 0){
							createAddUserDialog();
						}else{
							dialog.dismiss();
						}
					}
				});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void CreateDelUserDialog() {
		final String username = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Bạn có chắc chắn muốn xóa tài khoản " + username);
		builder.setPositiveButton("Delete",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						UserAction user = new UserAction(getBaseContext());
						String result = user.DelUser(username);
						if (result.equals("Xóa tài khoản thành công!")) {
							if (ListUser().length == 0) {
								user.removeSaveInfo();
							}
//								createAddUserDialog();
//								result = "Không có tài khoản nào được chọn, tạo tài khoản mới!";
//							} else {
//								CreateInfoDialog();
//							}
							dialog.dismiss();
						} else {
							CreateDelUserDialog();
						}
						Toast.makeText(getBaseContext(), result,
								Toast.LENGTH_SHORT).show();
					}

				});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void CreateInfoDialog() {
		
	}

	public void createEditUserDialog() {
		
	}

	private String[] ListUser() {
		String[] listUser;
		UserAction u = new UserAction(this);
		// String[] usernames is a list of username query from database
		listUser = u.getListofUserName(this);
		return listUser;
	}
	
	
}
