//package com.uit;
//
//import objects.Person;
//import objects.UserActivity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.SharedPreferences;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class CustomDialog {
//	public final static String ACCOUNT = "account";
//	public final static String NAME = "name";
//	
//	private Context context;
//	
//	
//	public CustomDialog(Context context) {
//		super();
//		this.context = context;
//	}
//
//	public void createLoginDialog(final View view) {
//		final EditText input = new EditText(context);
//		AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		builder.setView(input);
//		builder.setCancelable(false);
//		builder.setTitle("Input your Name...");
//		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int whichButton) {
//				String username = input.getText().toString().trim();
//				UserActivity user = new UserActivity(context);
//				String result = user.AddUser(username);
//				if (result.equals("Insert successfully!")) {
//					((TextView) view).setText(username);
//					storeInformation(username);
//					dialog.dismiss();
//				} else {
//					checkLogin();
//				}
//				Toast.makeText(context, result, Toast.LENGTH_SHORT)
//						.show();
//			}
//		});
//		builder.setNegativeButton("Cancel",
//				new DialogInterface.OnClickListener() {
//
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.dismiss();
//						checkLogin();
//					}
//				});
//		AlertDialog dialog = builder.create();
//		dialog.show();
//	}
//
//	public void CreateUserDialog() {
//		Person p = new Person(context);
//		// String[] usernames is a list of username query from database
//		final String[] listUser;
//		listUser = p.getListofUserName(context);
//		AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		builder.setSingleChoiceItems(listUser, 0, null);
//		builder.setTitle("Choose your Name...");
//		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//				int itemChecked = ((AlertDialog) dialog).getListView()
//						.getCheckedItemPosition();
//				for (int i = 0; i < listUser.length; i++) {
//					if (itemChecked == i) {
//						txtUsername.setText(listUser[i]);
//						dialog.dismiss();
//						// UserActivity user = new
//						// UserActivity(getBaseContext());
//						// String result = user.DelUser(username);
//						//
//						// if(result.equals("Delete successfully!")){
//						// txtUsername.setText(username);
//						// storeInformation(username);
//						// dialog.dismiss();
//						// }else{
//						// checkLogin();
//						// }
//						// Toast.makeText(getBaseContext(), result,
//						// Toast.LENGTH_SHORT).show();
//					}
//				}
//			}
//		});
//		builder.setNegativeButton("Cancel",
//				new DialogInterface.OnClickListener() {
//
//					public void onClick(DialogInterface dialog, int which) {
//						dialog.dismiss();
//					}
//				});
//		AlertDialog dialog = builder.create();
//		dialog.show();
//	}
//
//	public void storeInformation(String username) {
//		SharedPreferences account = context.getSharedPreferences(ACCOUNT, 0);
//		SharedPreferences.Editor editor = account.edit();
//		editor.putString(NAME, username);
//		editor.commit();
//	}
//	
//	public void checkLogin() {
//		SharedPreferences account = context.getSharedPreferences(ACCOUNT, 0);
//		String username = account.getString(NAME, null);
//		if (username == null || username.equals("")) {
//			createLoginDialog();
//		} else {
//			txtUsername.setText(username);
//		}
//	}
//
//}
