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
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    int correctAns;
    //Binding RadioButtons for Question 1
    @BindView(R.id.radio_button_q1_ans1) RadioButton radioQuestion1Answer1;
    @BindView(R.id.radio_button_q1_ans2) RadioButton radioQuestion1Answer2;
    @BindView(R.id.radio_button_q1_ans3) RadioButton radioQuestion1Answer3;
    @BindView(R.id.radio_button_q1_ans4) RadioButton radioQuestion1Answer4;
    //Bind CheckBoxes for Question2
    @BindView(R.id.checkbox_q2_ans1) CheckBox checkboxQuestion2Answer1;
    @BindView(R.id.checkbox_q2_ans2) CheckBox checkboxQuestion2Answer2;
    @BindView(R.id.checkbox_q2_ans3) CheckBox checkboxQuestion2Answer3;
    @BindView(R.id.checkbox_q2_ans4) CheckBox checkboxQuestion2Answer4;
    //Bind EditText for Question3
    @BindView(R.id.edittext_q3_ans1) EditText edittextQuestion3Answer1;
    //Bind TextView which contains right answer for Question3
    @BindView(R.id.textview_q3_ans) TextView question3RightAnswer;
    //Binding RadioButtons for Question 4
    @BindView(R.id.radio_button_q4_ans1) RadioButton radioQuestion4Answer1;
    @BindView(R.id.radio_button_q4_ans2) RadioButton radioQuestion4Answer2;
    @BindView(R.id.radio_button_q4_ans3) RadioButton radioQuestion4Answer3;
    @BindView(R.id.radio_button_q4_ans4) RadioButton radioQuestion4Answer4;
    //Bind EditText for Question3
    @BindView(R.id.edittext_q5_ans1) EditText edittextQuestion5Answer1;
    //Bind TextView which contains right answer for Question3
    @BindView(R.id.textview_q5_ans) TextView question5RightAnswer;
    //Bind TextView which used to show results
    @BindView(R.id.textview_results) TextView results;
    //Bind buttons
    @BindView(R.id.share_button) Button buttonShare;
    @BindView(R.id.open_answers_button) Button openAnswersButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * After pressing "Open Answers" button this method
     * highlight with green and red colors right and wrong answers accordingly where possible;
     *  Also show right answers for question 3 and 5 with user input
     */
    public void seeAnswers(View v) {
        //highlighting in question 1
        radioQuestion1Answer1.setTextColor(getResources().getColor(R.color.right_answer));
        radioQuestion1Answer2.setTextColor(Color.RED);
        radioQuestion1Answer3.setTextColor(Color.RED);
        radioQuestion1Answer4.setTextColor(Color.RED);
        //highlighting in question 2;
        checkboxQuestion2Answer3.setTextColor(getResources().getColor(R.color.right_answer));
        checkboxQuestion2Answer4.setTextColor(getResources().getColor(R.color.right_answer));
        checkboxQuestion2Answer1.setTextColor(Color.RED);
        checkboxQuestion2Answer2.setTextColor(Color.RED);
        //show text with right answer in question 3
        question3RightAnswer.setVisibility(View.VISIBLE);
        question3RightAnswer.setText(getText(R.string.expl3));
        //highlighting in question 4
        radioQuestion4Answer2.setTextColor(getResources().getColor(R.color.right_answer));
        radioQuestion4Answer1.setTextColor(Color.RED);
        radioQuestion4Answer3.setTextColor(Color.RED);
        radioQuestion4Answer4.setTextColor(Color.RED);
        //show text with right answer in question 3
        question5RightAnswer.setVisibility(View.VISIBLE);
        question5RightAnswer.setText(getText(R.string.expl5));
    }

    /**
     * Check all answers
     * After calculating the number of correct answers show buttons
     * "share with friend" and "open correct answers"
     */
    public void checkAnswers(View v) {
        correctAns = 0;
        //dealing with question 1: check for correct one
        if (radioQuestion1Answer1.isChecked()) correctAns += 1;
        //dealing with question 2: be sure that right answers are checked
        // AND wrong answers are not checked
        if (checkboxQuestion2Answer3.isChecked() && checkboxQuestion2Answer4.isChecked()
                && !checkboxQuestion2Answer1.isChecked() && !checkboxQuestion2Answer2.isChecked())
            correctAns += 1;
        //question 3 check
        if (edittextQuestion3Answer1.getText().toString().equals("\\\'")) correctAns += 1;
        //question 4 check
        if (radioQuestion4Answer2.isChecked()) correctAns += 1;
        //question 5 check
        if (edittextQuestion5Answer1.getText().toString().equals("fc -l -5")) correctAns += 1;
        //showing the results
        results.setText(getString(R.string.results, correctAns));
        Toast.makeText(MainActivity.this, String.valueOf(correctAns) +
                " correct answers of 5",Toast.LENGTH_SHORT).show();
        //unveil share button and button for opening answers
        buttonShare.setVisibility(View.VISIBLE);
        openAnswersButton.setVisibility(View.VISIBLE);
    }

    /**
     * Method create implicit Intent for sending message which contains string with result
     */
    public void shareResult(View v) {
        String message = getString(R.string.sharing_message, correctAns);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
