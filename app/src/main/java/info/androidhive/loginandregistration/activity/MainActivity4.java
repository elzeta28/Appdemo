package info.androidhive.loginandregistration.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import info.androidhive.loginandregistration.R;
import info.androidhive.loginandregistration.helper.SQLiteHandler;
import info.androidhive.loginandregistration.helper.SessionManager;

public class MainActivity4 extends Activity {


	private Button btnsiguiente;
	private Button btnLogout;
	private SQLiteHandler db;
	private SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main4);


		btnsiguiente = (Button) findViewById(R.id.btnsiguiente);
		btnLogout = (Button) findViewById(R.id.btnLogout);
		db = new SQLiteHandler(getApplicationContext());


		session = new SessionManager(getApplicationContext());

		if (!session.isLoggedIn()) {
			logoutUser();
		}

		HashMap<String, String> user = db.getUserDetails();

		String name = user.get("name");
		String email = user.get("email");


		btnLogout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				logoutUser();
			}
		});
		btnsiguiente.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				siguiente();
			}
		});

	}


	private void logoutUser() {
		session.setLogin(false);

		db.deleteUsers();

		// Launching the login activity
		Intent intent = new Intent(MainActivity4.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	private void siguiente() {


		// Launching the login activity
		Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
		startActivity(intent);

	}
}
