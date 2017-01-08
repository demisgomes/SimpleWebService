package com.simplewebservice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.simplewebservice.R;
import com.simplewebservice.domain.Question;

import java.util.ArrayList;

/**
 * Created by Demis on 10/23/16.
 */
public class QuestionResultsAdapter extends ArrayAdapter<Question>{
    //String questionText;
    TextView tvQuestionText;
    TextView tvChoice1,tvChoice2,tvChoice3,tvChoice4,tvChoice5;
    TextView tvResult1,tvResult2,tvResult3,tvResult4,tvResult5;
    int pos;

    public QuestionResultsAdapter(Context context, ArrayList<Question> questions) {
        super(context,0,questions);
    }

    public View getView(int position, View view, ViewGroup parent) {
        Question question = getItem(position);
        pos=position;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.question_results, parent, false);
        }

        tvQuestionText=(TextView) view.findViewById(R.id.tv_question_text);
        tvChoice1=(TextView) view.findViewById(R.id.tv_choice1);
        tvChoice2=(TextView) view.findViewById(R.id.tv_choice2);
        tvChoice3=(TextView) view.findViewById(R.id.tv_choice3);
        tvChoice4=(TextView) view.findViewById(R.id.tv_choice4);
        tvChoice5=(TextView) view.findViewById(R.id.tv_choice5);

        tvResult1=(TextView) view.findViewById(R.id.tv_result1);
        tvResult2=(TextView) view.findViewById(R.id.tv_result2);
        tvResult3=(TextView) view.findViewById(R.id.tv_result3);
        tvResult4=(TextView) view.findViewById(R.id.tv_result4);
        tvResult5=(TextView) view.findViewById(R.id.tv_result5);

        //setting values
        tvQuestionText.setText(question.getQuestionText());
        tvChoice1.setText(question.getChoices().get(0).getChoiceText());
        tvChoice2.setText(question.getChoices().get(1).getChoiceText());
        tvChoice3.setText(question.getChoices().get(2).getChoiceText());
        tvChoice4.setText(question.getChoices().get(3).getChoiceText());
        tvChoice5.setText(question.getChoices().get(4).getChoiceText());

        tvResult1.setText(question.getChoices().get(0).getVotes()+" votes");
        tvResult2.setText(question.getChoices().get(1).getVotes()+" votes");
        tvResult3.setText(question.getChoices().get(2).getVotes()+" votes");
        tvResult4.setText(question.getChoices().get(3).getVotes()+" votes");
        tvResult5.setText(question.getChoices().get(4).getVotes()+" votes");

        return view;
    }

}
