package com.example.android.linuxquiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    int correctAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*higlight with green and red colors right and wrong answers accordingly where possible;
    *also show right answers for question 3 and 5 with user input
     */
    public void seeAnswers(View v) {
        //highlighting in question 1
        RadioButton r1 = findViewById(R.id.ans1);
        r1.setTextColor(Color.parseColor("#006400"));
        RadioButton r2 = findViewById(R.id.ans1_wr1);
        r2.setTextColor(Color.RED);
        RadioButton r3 = findViewById(R.id.ans1_wr2);
        r3.setTextColor(Color.RED);
        RadioButton r4 = findViewById(R.id.ans1_wr3);
        r4.setTextColor(Color.RED);
        //highlighting in question 2
        CheckBox ch2_a = findViewById(R.id.ans2_a);
        ch2_a.setTextColor(Color.parseColor("#006400"));
        CheckBox ch2_b = findViewById(R.id.ans2_b);
        ch2_b.setTextColor(Color.parseColor("#006400"));
        CheckBox wr1 = findViewById(R.id.ans2_wrong1);
        wr1.setTextColor(Color.RED);
        CheckBox wr2 = findViewById(R.id.ans2_wrong2);
        wr2.setTextColor(Color.RED);
        //highlighting text answer in question 3
        TextView expl3 = findViewById(R.id.expl3);
        expl3.setVisibility(View.VISIBLE);
        expl3.setText(getText(R.string.expl3));
        //highlighting in question 4
        RadioButton r4_ans4 = findViewById(R.id.ans4);
        r4_ans4.setTextColor(Color.parseColor("#006400"));
        RadioButton r4_wr1 = findViewById(R.id.ans4_wr1);
        r4_wr1.setTextColor(Color.RED);
        RadioButton r4_wr2 = findViewById(R.id.ans4_wr2);
        r4_wr2.setTextColor(Color.RED);
        RadioButton r4_wr3 = findViewById(R.id.ans4_wr3);
        r4_wr3.setTextColor(Color.RED);
        //highlighting in question 5
        TextView expl5 = findViewById(R.id.expl5);
        expl5.setVisibility(View.VISIBLE);
        expl5.setText(getText(R.string.expl5));
    }

    /*checking all answers;
    *after show buttons "share with friend" and "open correct answers"
     */
    public void checkAnswers(View v) {
        correctAns = 0;
        //dealing with question 1: check for correct one
        RadioButton r1 = findViewById(R.id.ans1);
        if (r1.isChecked()) correctAns += 1;
        //dealing with question 2: check for correct ones
        CheckBox ch2_a = findViewById(R.id.ans2_a);
        CheckBox ch2_b = findViewById(R.id.ans2_b);
        CheckBox wr1 = findViewById(R.id.ans2_wrong1);
        CheckBox wr2 = findViewById(R.id.ans2_wrong2);
        if (ch2_a.isChecked() && ch2_b.isChecked() && !wr1.isChecked() && !wr2.isChecked())
            correctAns += 1;
        //question 3 check
        EditText ed3 = findViewById(R.id.ans3);
        if (ed3.getText().toString().equals("\\\'")) correctAns += 1;
        //question 4 check
        RadioButton r4 = findViewById(R.id.ans4);
        if (r4.isChecked()) correctAns += 1;
        //question 5 check
        EditText ed5 = findViewById(R.id.ans5);
        if (ed5.getText().toString().equals("fc -l -5")) correctAns += 1;
        //showing the results
        TextView res = findViewById(R.id.results);
        res.setText(getString(R.string.results, correctAns));
        //unveil share button and button for opening answers
        Button shareButton = findViewById(R.id.share_button);
        shareButton.setVisibility(View.VISIBLE);
        Button openAnswers = findViewById(R.id.seeans_button);
        openAnswers.setVisibility(View.VISIBLE);
    }

    //intent for sending message
    public void shareResult(View v) {
        String message = getString(R.string.sharing_message, correctAns);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
