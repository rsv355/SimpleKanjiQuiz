package com.simple.kanjiquiz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.phase2.kanjiquiz.R;

public class AnswerActivity extends Activity implements OnClickListener {

	private String userAnswer, correctAnswer, optionAnswer;
	private int counter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_screen);
		init();
		bindView();
		addListner();
	}

	private void init() {
		userAnswer = getIntent().getStringExtra("user_ans");
		correctAnswer = getIntent().getStringExtra("correct_ans");
		optionAnswer = getIntent().getStringExtra("option");
		counter = getIntent().getIntExtra("isFinish", 0);
	}

	@SuppressLint("DefaultLocale")
	private void bindView() {
		((TextView) findViewById(R.id.tvCorrectAnswerForAnswerScreen))
				.setText("CORRECT ANSWER" + "\n \n"
						+ correctAnswer.toUpperCase());
		((TextView) findViewById(R.id.tvYourAnswerForAnswerScreen))
				.setText("YOUR ANSWER" + "\n \n" + userAnswer.toUpperCase());


     	if (Utility.mOptionAorB == 0) {

            if (!optionAnswer.equals("")) {
				((TextView) findViewById(R.id.tvOptionAnswerForAnswerScreen))
						.setText(("Atheist's ANSWER" + "\n \n" + optionAnswer
								.toUpperCase()));
			} else {
				((TextView) findViewById(R.id.tvOptionAnswerForAnswerScreen))
						.setText(("Atheist HAVE NOT ATTEMPT THIS QUESTION!"));
			}

		} else {

			if (!optionAnswer.equals("")) {
				((TextView) findViewById(R.id.tvOptionAnswerForAnswerScreen))
						.setText(("Believer's ANSWER" + "\n \n" + optionAnswer
								.toUpperCase()));
			} else {
				((TextView) findViewById(R.id.tvOptionAnswerForAnswerScreen))
						.setText(("Believer HAVE NOT ATTEMPT THIS QUESTION!"));

			}
		}

	}

	private void addListner() {

		((Button) findViewById(R.id.btnNextQuestionForAnswerScreen))
				.setOnClickListener(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		if (counter == 10) {
			Intent i = new Intent(AnswerActivity.this, FinishQuizActivity.class);
			startActivity(i);
			AnswerActivity.this.finish();
		} else {
			AnswerActivity.this.finishActivity(111);
		}

	}

	@Override
	public void onClick(View v) {

		Log.d("tag", "OnClick Call");
		if (counter == 10) {
			Intent i = new Intent(AnswerActivity.this, FinishQuizActivity.class);
			startActivity(i);
			AnswerActivity.this.finish();
		} else {
			AnswerActivity.this.finish();
		}

	}
}
