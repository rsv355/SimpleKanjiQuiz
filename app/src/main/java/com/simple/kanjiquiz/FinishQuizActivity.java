package com.simple.kanjiquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.phase2.kanjiquiz.R;
import com.simple.kanjiquiz.database.SqlLiteDbHelper;

public class FinishQuizActivity extends Activity {

	private SqlLiteDbHelper dbHelper;
	private String[] highScoreArray = new String[2];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finish_quize);
		init();
		bindView();
	}

	private void init() {
		dbHelper = new SqlLiteDbHelper(this);
		dbHelper.openDataBase();
		highScoreArray = dbHelper.getSingleTestHighScore(Utility.mSelectTest
				.trim());
	}

	private void bindView() {
		if (Utility.mOptionAorB == 0) {
			((TextView) findViewById(R.id.tvScoreForFinishQuize))
					.setText("Believer Score : " + highScoreArray[0]);
			((TextView) findViewById(R.id.tvOppnentScoreForFinishQuize))
					.setText("Atheist Score : " + highScoreArray[1]);

			if (Integer.parseInt(highScoreArray[0]) >= Integer
					.parseInt(highScoreArray[1])) {
				((TextView) findViewById(R.id.tvMsgForFinishQuize))
						.setText("You are");
				((TextView) findViewById(R.id.tvMsg2ForFinishQuize))
						.setText("smarter than");
				((TextView) findViewById(R.id.tvMsg3ForFinishQuize))
						.setText("Atheist");
			} else {
				((TextView) findViewById(R.id.tvMsgForFinishQuize))
						.setText("You are");
				((TextView) findViewById(R.id.tvMsg2ForFinishQuize))
						.setText("dumber than");
				((TextView) findViewById(R.id.tvMsg3ForFinishQuize))
						.setText("Atheist");
			}
		} else {
			((TextView) findViewById(R.id.tvScoreForFinishQuize))
					.setText("Atheist Score : " + highScoreArray[1]);
			((TextView) findViewById(R.id.tvOppnentScoreForFinishQuize))
					.setText("Believer Score : " + highScoreArray[0]);

			if (Integer.parseInt(highScoreArray[1]) >= Integer
					.parseInt(highScoreArray[0])) {
				((TextView) findViewById(R.id.tvMsgForFinishQuize))
						.setText("You are");
				((TextView) findViewById(R.id.tvMsg2ForFinishQuize))
						.setText("smarter than");
				((TextView) findViewById(R.id.tvMsg3ForFinishQuize))
						.setText("Believer");
			} else {
				((TextView) findViewById(R.id.tvMsgForFinishQuize))
						.setText("You are");
				((TextView) findViewById(R.id.tvMsg2ForFinishQuize))
						.setText("dumber than");
				((TextView) findViewById(R.id.tvMsg3ForFinishQuize))
						.setText("Believer");
			}
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(FinishQuizActivity.this, SplasScreen.class);
		startActivity(i);
		FinishQuizActivity.this.finish();
	}
}
