package com.simple.kanjiquiz;

import java.util.HashMap;

import com.phase2.kanjiquiz.R;
import com.simple.kanjiquiz.database.SqlLiteDbHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SelectQuizActivity extends Activity implements OnClickListener {

	private SqlLiteDbHelper dbHelper;
	private HashMap<String, String[]> hmHighScore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.manu_ui);

		init();
		bindView();
		addListner();
	}

	private void init() {
		Utility.mSelectTest = "";

		hmHighScore = new HashMap<String, String[]>();
		dbHelper = new SqlLiteDbHelper(this);
		dbHelper.openDataBase();
		hmHighScore = dbHelper.getMathHighScore();
	}

	public String getHighScore(String val, String val2) {
		int valInt = Integer.parseInt(val);
		int valInt2 = Integer.parseInt(val2);

		Log.d("tag", "Val : " + val);
		Log.d("tag", "Val2 : " + val2);
		if (valInt > valInt2) {
			return String.valueOf(valInt);
		} else {
			return String.valueOf(valInt2);
		}
	}

	private void bindView() {
		((TextView) findViewById(R.id.tvMathScoreForMenuUI))
				.setText(getHighScore(hmHighScore.get("PopCulture")[0],
						hmHighScore.get("Math")[1])
						+ "/10");
		// ((TextView) findViewById(R.id.tvScienceScoreForMenuUI))
		// .setText(getHighScore(hmHighScore.get("Science")[0],
		// hmHighScore.get("Science")[1])+"/25");
		// ((TextView) findViewById(R.id.tvReligionScoreForMenuUI))
		// .setText(getHighScore(hmHighScore.get("Religion")[0],
		// hmHighScore.get("Religion")[1])+"/25");
		// ((TextView) findViewById(R.id.tvSportScoreForMenuUI))
		// .setText(getHighScore(hmHighScore.get("Sport")[0],
		// hmHighScore.get("Sport")[1])+"/25");
	}

	private void addListner() {
		// findViewById(R.id.rlMathForMenuUI).setOnClickListener(this);
		// findViewById(R.id.rlScienceForMenuUI).setOnClickListener(this);
		// findViewById(R.id.rlSportForMenuUI).setOnClickListener(this);
		// findViewById(R.id.rlReligionForMenuUI).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.rlMathForMenuUI:
//			Utility.mSelectTest = "Math";
//			break;
//		case R.id.rlReligionForMenuUI:
//			Utility.mSelectTest = "Religion";
//			break;
//		case R.id.rlScienceForMenuUI:
//			Utility.mSelectTest = "Science";
//			break;
//		case R.id.rlSportForMenuUI:
//			Utility.mSelectTest = "Sport";
//			break;
//		}

		Intent i = new Intent(SelectQuizActivity.this, OptionSelectScreen.class);
		startActivity(i);
		SelectQuizActivity.this.finish();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(SelectQuizActivity.this, SplasScreen.class);
		startActivity(i);
		SelectQuizActivity.this.finish();
	}
}
