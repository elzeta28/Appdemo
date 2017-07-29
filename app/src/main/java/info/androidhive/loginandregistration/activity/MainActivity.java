package info.androidhive.loginandregistration.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import info.androidhive.loginandregistration.R;
import info.androidhive.loginandregistration.helper.SQLiteHandler;
import info.androidhive.loginandregistration.helper.SessionManager;

public class MainActivity extends Activity {

	private TextView txtName;
	private TextView txtEmail;
	private Button btnsiguiente;

	private Button btnLogout;

	private SQLiteHandler db;
	private SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtName = (TextView) findViewById(R.id.name);
		txtEmail = (TextView) findViewById(R.id.email);
		btnLogout = (Button) findViewById(R.id.btnLogout);
		btnsiguiente = (Button) findViewById(R.id.btnsiguiente);

		db = new SQLiteHandler(getApplicationContext());


		session = new SessionManager(getApplicationContext());

		if (!session.isLoggedIn()) {
			logoutUser();
		}

		HashMap<String, String> user = db.getUserDetails();

		String name = user.get("name");
		String email = user.get("email");


		txtName.setText(name);
		txtEmail.setText(email);

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
		Intent intent = new Intent(MainActivity.this, LoginActivity.class);
		startActivity(intent);
		finish();
	}

	private void siguiente() {

		// Launching the login activity
		Intent intent = new Intent(MainActivity.this, MainActivity2.class);
		startActivity(intent);

	}
}
