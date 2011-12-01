package com.uit.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.uit.R;
import com.uit.objects.UserActivity;

public class AccountActivity extends Activity {

	TextView txtUsername;
	ImageButton btnAdd, btnDel, btnEdit, btnChange;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account);
		
		txtUsername = (TextView) findViewById(R.id.a_txtUsername);
		btnAdd = (ImageButton) findViewById(R.id.a_btnAdd);
		btnDel = (ImageButton) findViewById(R.id.a_btnDel);
		btnChange = (ImageButton) findViewById(R.id.a_btnChange);
		btnEdit = (ImageButton) findViewById(R.id.a_btnEdit);
		
		String username = getIntent().getExtras().getString(UserActivity.ACCOUNT);
		txtUsername.setText(username);
		
		btnAdd.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				createAddUserDialog();
			}
		});

		btnChange.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				CreateChangeUserDialog();
			}
		});

		btnDel.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				CreateDelUserDialog();
			}
		});

		btnEdit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

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
				UserActivity user = new UserActivity(getBaseContext());
				String result = user.AddUser(username);
				if (result.equals("Thêm tài khoản thành công!")) {
					txtUsername.setText(username);
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
		final String username = txtUsername.getText().toString();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Bạn có chắc chắn muốn xóa tài khoản " + username);
		builder.setPositiveButton("Delete",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						UserActivity user = new UserActivity(getBaseContext());
						String result = user.DelUser(username);
						if (result.equals("Xóa tài khoản thành công!")) {
							if (ListUser().length == 0) {
								user.removeSaveInfo();
								createAddUserDialog();
								result = "Không có tài khoản nào được chọn, tạo tài khoản mới!";
							} else {
								CreateChangeUserDialog();
							}
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

	public void CreateChangeUserDialog() {
		final String[] listUser = ListUser();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setSingleChoiceItems(listUser, 0, null);
		builder.setTitle("Choose your Name...");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				int itemChecked = ((AlertDialog) dialog).getListView()
						.getCheckedItemPosition();
				for (int i = 0; i < listUser.length; i++) {
					if (itemChecked == i) {
						String username = listUser[i];
						txtUsername.setText(username);
						new UserActivity(AccountActivity.this)
								.storeInformation(username);
						dialog.dismiss();
					}
				}
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

	private String[] ListUser() {
		String[] listUser;
		UserActivity u = new UserActivity(this);
		// String[] usernames is a list of username query from database
		listUser = u.getListofUserName(this);
		return listUser;

	}
}
