package com.simplewebservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.simplewebservice.adapters.QuestionListAdapter;
import com.simplewebservice.domain.Choice;
import com.simplewebservice.domain.Question;
import com.simplewebservice.service.WebService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnViewResults;
    static ArrayList<Question> questionArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new QuestionTask().execute();
        btnViewResults = (Button) findViewById(R.id.btn_view_results);
        btnViewResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);

                startActivity(intent);
                MainActivity.this.finish();
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

                //Toast.makeText(MainActivity.this,"Data requested sucessfully",Toast.LENGTH_SHORT).show();
                questionArrayList=new ArrayList<>();
                for (Map.Entry<Integer,Question> questionHashMap : questions.entrySet()){
                    questionArrayList.add(questionHashMap.getValue());
                }
                listView=(ListView) findViewById(R.id.listView);
                ArrayAdapter<Question> adapter = new QuestionListAdapter(MainActivity.this, R.layout.question_list, questionArrayList);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                        intent.putExtra("question_position",position);
                        startActivity(intent);
                    }
                });

            }

            else{
                Toast.makeText(MainActivity.this,"Error in communication with server",Toast.LENGTH_LONG).show();
            }

        }

    }


}
