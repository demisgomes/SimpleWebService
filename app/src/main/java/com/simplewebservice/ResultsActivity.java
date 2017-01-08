package com.simplewebservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.simplewebservice.adapters.QuestionListAdapter;
import com.simplewebservice.adapters.QuestionResultsAdapter;
import com.simplewebservice.domain.Question;
import com.simplewebservice.service.WebService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsActivity extends AppCompatActivity {

    ArrayList<Question> questionArrayList;
    ListView listView;
    Button btnViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        //Bundle extras = getIntent().getExtras();
        new QuestionTask().execute();
        btnViewBack = (Button) findViewById(R.id.btn_go_back);
        btnViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultsActivity.this, MainActivity.class);

                startActivity(intent);
                ResultsActivity.this.finish();
            }
        });

    }

    private class QuestionTask extends AsyncTask<Void,Integer,HashMap<Integer,Question>> {

        @Override
        protected  HashMap<Integer,Question> doInBackground(Void... params) {
            WebService ws=new WebService();

            return ws.getQuestions();
        }

        @Override
        protected void onPostExecute(HashMap<Integer,Question> questions) {
            super.onPostExecute(questions);
            if (questions!=null){

                Toast.makeText(getApplicationContext(), "Data requested sucessfully", Toast.LENGTH_SHORT);
                questionArrayList=new ArrayList<>();
                for (Map.Entry<Integer,Question> questionHashMap : questions.entrySet()){
                    questionArrayList.add(questionHashMap.getValue());
                }
                listView=(ListView) findViewById(R.id.listViewResults);
                ArrayAdapter<Question> adapter = new QuestionResultsAdapter(ResultsActivity.this, questionArrayList);
                listView.setAdapter(adapter);

            }

            else{
                Toast.makeText(getApplicationContext(),"Error in communication with server",Toast.LENGTH_LONG);
            }

        }

    }
}
