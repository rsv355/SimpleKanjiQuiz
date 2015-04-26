package com.simple.kanjiquiz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.phase2.kanjiquiz.R;

public class OptionSelectScreen extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option_screen);
		addListner();
	}

	@SuppressLint("SimpleDateFormat")
	private void addListner() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date strDate;
		try {
			strDate = sdf.parse("05/05/2015");
			if (new Date().after(strDate)) {
				Log.d("tag", "Liences Expire");
				Toast.makeText(getApplicationContext(),
						"Expire Application Liences!", Toast.LENGTH_LONG)
						.show();
			} else {
				findViewById(R.id.llAForOptionScreen).setOnClickListener(this);
				findViewById(R.id.llBForOptionScreen).setOnClickListener(this);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.llAForOptionScreen:
			Utility.mOptionAorB = 0;
			break;

		case R.id.llBForOptionScreen:
			Utility.mOptionAorB = 1;
			break;
		}

		Intent i = new Intent(OptionSelectScreen.this, StartQuiz.class);
		startActivity(i);
		OptionSelectScreen.this.finish();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		Intent i = new Intent(OptionSelectScreen.this, SplasScreen.class);
		startActivity(i);
		OptionSelectScreen.this.finish();
	}
}
