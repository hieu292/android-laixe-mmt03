package com.uit.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.uit.R;
import com.uit.objects.UserActivity;

public class BaseActivity extends Activity {

	ImageButton btnTrain, btnAccount, btnThongKe;
	TextView txtUsername, txtEditUser;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txtUsername = (TextView) findViewById(R.id.m_txtUsername);
		btnTrain = (ImageButton) findViewById(R.id.m_btnTrain);
		btnAccount = (ImageButton) findViewById(R.id.m_btnAccount);
		txtEditUser = (TextView) findViewById(R.id.m_txtEditUser);
		btnThongKe = (ImageButton)findViewById(R.id.m_btnStatistic);
		
		checkLogin();

		txtEditUser.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				CreateUserDialog();
			}
		});
		
		btnAccount.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(BaseActivity.this, AccountActivity.class);
				i.putExtra(UserActivity.ACCOUNT, txtUsername.getText().toString());
				startActivity(i);
				finish();
			}
		});
		
		btnTrain.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(BaseActivity.this, TrainActivity.class);
				startActivity(i);
			}
		});
		
		btnThongKe.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(BaseActivity.this, ThongKeActivity.class);
				startActivity(i);
			}
		});
	}

	public void checkLogin() {
		SharedPreferences account = getSharedPreferences(UserActivity.ACCOUNT, 0);
		String username = account.getString(UserActivity.NAME, null);
		if (username == null || username.equals("")) {
			createLoginDialog();
		} else {
			txtUsername.setText(username);
		}
	}

	public void createLoginDialog() {
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
					checkLogin();
				}
				Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT)
						.show();
			}
		});
		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						checkLogin();
					}
				});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void CreateUserDialog() {
		UserActivity user = new UserActivity(this);
		// String[] usernames is a list of username query from database
		final String[] listUser;
		listUser = user.getListofUserName(this);
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
						txtUsername.setText(listUser[i]);
						dialog.dismiss();
						// UserActivity user = new
						// UserActivity(getBaseContext());
						// String result = user.DelUser(username);
						//
						// if(result.equals("Delete successfully!")){
						// txtUsername.setText(username);
						// storeInformation(username);
						// dialog.dismiss();
						// }else{
						// checkLogin();
						// }
						// Toast.makeText(getBaseContext(), result,
						// Toast.LENGTH_SHORT).show();
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
}
