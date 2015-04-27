package com.simple.kanjiquiz;

import java.util.ArrayList;
import java.util.HashMap;

import com.phase2.kanjiquiz.R;
import com.simple.kanjiquiz.database.SqlLiteDbHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartQuiz extends Activity implements OnClickListener {

	private SqlLiteDbHelper dbHelper;
	private ArrayList<HashMap<String, String>> alQuestionContainer;
	private ArrayList<String> alAttempQuestion;

	private TextView tvIndex, tvQuestion, tvScoreCountDown;
	private Button btnAns1, btnAns2, btnAns3, btnAns4, btnAns5;
	private int random, count, score, incorrect;
	private String userAnswer, correctAnswer, correctIndex, optionAns,
			userSelectIndex;
   // private ArrayList<String> Table_Name ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_ui);
      /*  Table_Name = new ArrayList<String>();
        Table_Name.add("Math");
        Table_Name.add("Sport");
        Table_Name.add("PopCulture");*/


		init();
		bindView();
		addListner();
	}

	private void init() {
		userSelectIndex = "";
		count = 0;
		score = 0;
		incorrect = 0;
		alAttempQuestion = new ArrayList<String>();
		alQuestionContainer = new ArrayList<HashMap<String, String>>();
		dbHelper = new SqlLiteDbHelper(this);
		dbHelper.openDataBase();

        String topicName = getIntent().getStringExtra("topic");
       /* if(topicName.equalsIgnoreCase("Trivia")){
                int pos = Utility.generateRandomForTableName();
                Utility.mSelectTest = Table_Name.get(pos);
        }else {*/
            Utility.mSelectTest = topicName;
        //}

        alQuestionContainer = dbHelper.getQuestion(Utility.mSelectTest);
		if (alQuestionContainer.size() > 0) {
			//from krishna
			// random = Utility.generateRandomQuestion();

            random = Utility.generateRandomForQuestion(alQuestionContainer.size());
			alAttempQuestion.add(String.valueOf(random));
		}
		dbHelper.close();
	}

	private void bindView() {
		tvIndex = (TextView) findViewById(R.id.tvIndexForQuestionUI);
		tvQuestion = (TextView) findViewById(R.id.tvQuestionForQuestionUI);
		tvScoreCountDown = (TextView) findViewById(R.id.tvScoreCountDownForQuestionUI);

		btnAns1 = (Button) findViewById(R.id.btnAns1ForQuestionUI);
		btnAns2 = (Button) findViewById(R.id.btnAns2ForQuestionUI);
		btnAns3 = (Button) findViewById(R.id.btnAns3ForQuestionUI);
		btnAns4 = (Button) findViewById(R.id.btnAns4ForQuestionUI);
		btnAns5 = (Button) findViewById(R.id.btnAns5ForQuestionUI);

		btnAns1.setTag("1");
		btnAns2.setTag("2");
		btnAns3.setTag("3");
		btnAns4.setTag("4");
		btnAns5.setTag("5");

		setQuestionUI();
	}

	private void addListner() {
		btnAns1.setOnClickListener(this);
		btnAns2.setOnClickListener(this);
		btnAns3.setOnClickListener(this);
		btnAns4.setOnClickListener(this);
		btnAns5.setOnClickListener(this);
		// findViewById(R.id.btnDoneForQuestionUI).setOnClickListener(this);
	}

	public void setQuestionUI() {
		Log.e("ans", "Random : " + random);
		tvIndex.setText("Question Remaining : " + (10 - count) + " of 10 ");
		tvScoreCountDown.setText("Correct: " + score + "  Incorrect: "
				+ incorrect);
		tvQuestion.setText(alQuestionContainer.get(random).get("question")
				.toString()
				+ "?");
		btnAns1.setText(alQuestionContainer.get(random).get("ans_1").toString());
		btnAns2.setText(alQuestionContainer.get(random).get("ans_2").toString());
		btnAns3.setText(alQuestionContainer.get(random).get("ans_3").toString());
		btnAns4.setText(alQuestionContainer.get(random).get("ans_4").toString());
		btnAns5.setText(alQuestionContainer.get(random).get("ans_5").toString());
		correctIndex = alQuestionContainer.get(random).get("index").toString();
		correctAnswer = alQuestionContainer.get(random).get("correct_ans")
				.toString();
		try {

            Log.e("random value",""+random);
			if (Utility.mOptionAorB == 0) {

                int rr = Utility.generateRandomForAI();
                String temp = "ans_"+String.valueOf(rr);
                Log.e("temp my number",""+rr);
                Log.e("temp String",temp);
                optionAns = alQuestionContainer.get(random).get(temp)
                        .toString();

//				optionAns = alQuestionContainer.get(random).get("option_b_ans")
//						.toString();
			} else {


                int rr = Utility.generateRandomForAI();
                String temp = "ans_"+String.valueOf(rr);
                Log.e("temp my number",""+rr);
                Log.e("temp String",temp);
                optionAns = alQuestionContainer.get(random).get(temp)
                        .toString();

				/*optionAns = alQuestionContainer.get(random).get("option_a_ans")
						.toString();*/
			}
		} catch (Exception e) {
			optionAns = "";
            Log.e("Exce in startwuiz",e.toString());
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (count < 25) {
			if (requestCode == 111) {
				userAnswer = "";
				userSelectIndex = "";
				nextQuestion();
			} else {
				Log.e("tag", "Reslult Code Wrong");
			}
		} else {
			Toast.makeText(StartQuiz.this, "Quiz Over!", Toast.LENGTH_LONG)
					.show();
		}

	}

	public void nextQuestion() {
        //from krishna
        // random = Utility.generateRandomQuestion();
        random = Utility.generateRandomForQuestion(alQuestionContainer.size());

		if (alAttempQuestion.contains(String.valueOf(random))) {
			nextQuestion();
		} else {
			Log.e("tag", "Next Question is : " + random);
			alAttempQuestion.add(String.valueOf(random));
			setQuestionUI();
		}
	}

	@Override
	public void onClick(View v) {
		Button btn = (Button) v;
		if (count < 10) {
			userAnswer = btn.getText().toString();
			userSelectIndex = v.getTag().toString();
			count++;
			if (correctIndex.equals(userSelectIndex)) {
				score = score + 1;
				Log.e("ans", "Correct Answe");
			} else {
				incorrect = incorrect + 1;
				Log.e("ans", "Not Correct");
			}

			dbHelper.openDataBase();
			if (Utility.mOptionAorB == 0) {
				dbHelper.insertAnswer(Utility.mSelectTest, "option_a_ans",
						userAnswer, String.valueOf(alQuestionContainer.get(
								random).get("id")));
				if (count == 10) {
					dbHelper.insertAnswer1("HighScore", "option_a_score",
							String.valueOf(score), Utility.mSelectTest);
					dbHelper.close();
				}
			} else {
				dbHelper.insertAnswer(Utility.mSelectTest, "option_b_ans",
						userAnswer, String.valueOf(alQuestionContainer.get(
								random).get("id")));
				if (count == 10) {
					dbHelper.insertAnswer1("HighScore", "option_b_score",
							String.valueOf(score), Utility.mSelectTest);
					dbHelper.close();
				}
			}
			if (count == 10) {
				Intent i = new Intent(StartQuiz.this, AnswerActivity.class);
				i.putExtra("correct_ans", correctAnswer);
				i.putExtra("user_ans", userAnswer);
				i.putExtra("option", optionAns);
				i.putExtra("isFinish", count);
				startActivity(i);
				StartQuiz.this.finish();
			} else {
				Intent i = new Intent(StartQuiz.this, AnswerActivity.class);
				i.putExtra("correct_ans", correctAnswer);
				i.putExtra("user_ans", userAnswer);
				i.putExtra("option", optionAns);
				i.putExtra("isFinish", count);
				startActivityForResult(i, 111);
			}
		} else {
			Toast.makeText(getApplicationContext(), "Test is Over!",
					Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void onBackPressed() {

		showWarningDialog();
	}

	public void showWarningDialog() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				StartQuiz.this);

		alertDialogBuilder.setTitle("Alert!");
		alertDialogBuilder
				.setMessage("ARE YOU QUITE QUIZ?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent i = new Intent(StartQuiz.this,
										SplasScreen.class);
								startActivity(i);
								StartQuiz.this.finish();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();

		alertDialog.show();
	}
}
