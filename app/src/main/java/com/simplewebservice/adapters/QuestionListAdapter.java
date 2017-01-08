package com.simplewebservice.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.simplewebservice.MainActivity;
import com.simplewebservice.R;
import com.simplewebservice.domain.Choice;
import com.simplewebservice.domain.Question;
import com.simplewebservice.service.WebService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Demis on 10/23/16.
 */
public class QuestionListAdapter extends ArrayAdapter<Question>{
    //String questionText;
    Question question;
    TextView tvQuestionTextList;
    private LayoutInflater inflater;
    private int resourceId;
    RadioButton rbtnChoice1,rbtnChoice2,rbtnChoice3,rbtnChoice4,rbtnChoice5;
    Button btnVote;
    int pos;

    public QuestionListAdapter(Context context, int resource, ArrayList<Question> questions) {
        super(context,resource,questions);
        this.inflater=LayoutInflater.from(context);
        this.resourceId=resource;
    }

    public View getView(int position, View view, ViewGroup parent) {
        question= getItem(position);
        pos=position;
        view=inflater.inflate(this.resourceId,parent,false);
        //if (view == null) {
         //   view = LayoutInflater.from(getContext()).inflate(R.layout.question_list, parent, false);
       // }

        tvQuestionTextList=(TextView) view.findViewById(R.id.tv_question_text_list);
          tvQuestionTextList.setText(question.getQuestionText());

                question=getItem(pos);
                for (Choice choice1 : question.getChoices()) {
                    System.out.println(choice1.getChoiceText() + " " + choice1.getPk());
                }
        return view;
    }

}
