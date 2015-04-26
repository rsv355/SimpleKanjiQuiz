package com.simple.kanjiquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.phase2.kanjiquiz.R;

public class SplasScreen extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splas_screen);

		Utility.mSelectTest = "";
		addListner();
	}

	public void addListner() {
		((Button) findViewById(R.id.btnStartGameForSplas))
				.setOnClickListener(this);
		((Button) findViewById(R.id.btnTopScoresForSplas))
				.setOnClickListener(this);
		((Button) findViewById(R.id.btnInstructionForSplas))
				.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnStartGameForSplas:
			Utility.mSelectTest = "PopCulture";
			Intent i = new Intent(SplasScreen.this, OptionSelectScreen.class);
			startActivity(i);
			SplasScreen.this.finish();
			break;
		case R.id.btnTopScoresForSplas:
			Intent ii = new Intent(SplasScreen.this, SelectQuizActivity.class);
			startActivity(ii);
			SplasScreen.this.finish();
			break;
		case R.id.btnInstructionForSplas:

			break;
		}
	}
}
