package com.simplewebservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.simplewebservice.domain.Choice;
import com.simplewebservice.domain.Question;
import com.simplewebservice.service.WebService;

public class QuestionActivity extends AppCompatActivity {

    Question question;
    TextView tvQuestionText;
    private LayoutInflater inflater;
    private int resourceId;
    RadioButton rbtnChoice1,rbtnChoice2,rbtnChoice3,rbtnChoice4,rbtnChoice5;
    Button btnVote;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        int questionPosition=getIntent().getIntExtra("question_position",0);
        question=MainActivity.questionArrayList.get(questionPosition);

        tvQuestionText=(TextView) findViewById(R.id.tv_question_text);
        rbtnChoice1=(RadioButton) findViewById(R.id.rbtn_choice1);
        rbtnChoice2=(RadioButton) findViewById(R.id.rbtn_choice2);
        rbtnChoice3=(RadioButton) findViewById(R.id.rbtn_choice3);
        rbtnChoice4=(RadioButton) findViewById(R.id.rbtn_choice4);
        rbtnChoice5=(RadioButton) findViewById(R.id.rbtn_choice5);

        btnVote=(Button) findViewById(R.id.btn_vote);

        //setting values
        tvQuestionText.setText(question.getQuestionText());
        rbtnChoice1.setText(question.getChoices().get(0).getChoiceText());
        rbtnChoice2.setText(question.getChoices().get(1).getChoiceText());
        rbtnChoice3.setText(question.getChoices().get(2).getChoiceText());
        rbtnChoice4.setText(question.getChoices().get(3).getChoiceText());
        rbtnChoice5.setText(question.getChoices().get(4).getChoiceText());

        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int choiceId=0;

                if (rbtnChoice1.isChecked()) {
                    choiceId = 0;
                }

                if (rbtnChoice2.isChecked()) {
                    choiceId = 1;
                }

                if(rbtnChoice3.isChecked()){
                    choiceId=2;
                }

                if(rbtnChoice4.isChecked()){
                    choiceId=3;
                }

                if(rbtnChoice5.isChecked()){
                    choiceId=4;
                }

                for (Choice choice1 : question.getChoices()){
                    System.out.println(choice1.getChoiceText()+" "+choice1.getPk());
                }

                Choice choice=question.getChoices().get(choiceId);
                new QuestionTask().execute(choice);
                Toast.makeText(getApplicationContext(), "Vote sucessully computed", Toast.LENGTH_LONG).show();

            }
        });


    }
    private class QuestionTask extends AsyncTask<Choice,Integer,Integer> {

        @Override
        protected Integer doInBackground(Choice... params) {
            WebService ws=new WebService();
            ws.saveChoice(params[0]);
            return 1;
        }
    }

}
