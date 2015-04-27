package com.simple.kanjiquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phase2.kanjiquiz.R;

public class TopicScreen extends Activity implements OnClickListener {

    private LinearLayout txtTrivia,txtScience,txtReligion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.topic_screen);


        txtReligion = (LinearLayout)findViewById(R.id.txtReligion);
        txtScience = (LinearLayout)findViewById(R.id.txtScience);
        txtTrivia = (LinearLayout)findViewById(R.id.txtTrivia);

        txtTrivia.setOnClickListener(this);
        txtReligion.setOnClickListener(this);
        txtScience.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {

        switch (v.getId()) {
            case R.id.txtTrivia:
                Intent i1 = new Intent(TopicScreen.this, StartQuiz.class);
                startActivity(i1);
                TopicScreen.this.finish();
                break;
            case R.id.txtReligion:
                Intent i2 = new Intent(TopicScreen.this, StartQuiz.class);
                startActivity(i2);
                TopicScreen.this.finish();
                break;
            case R.id.txtScience:
                Intent i3 = new Intent(TopicScreen.this, StartQuiz.class);
                startActivity(i3);
                TopicScreen.this.finish();
                break;
        }

       /* Intent i = new Intent(TopicScreen.this, StartQuiz.class);
        startActivity(i);
        TopicScreen.this.finish();
*/
	}
}
