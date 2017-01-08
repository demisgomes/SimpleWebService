package com.simplewebservice.service;

import android.util.Log;

import com.simplewebservice.domain.Choice;
import com.simplewebservice.domain.Question;
import com.simplewebservice.domain.ServerResponse;
import com.simplewebservice.domain.WebServiceInterface;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Demis on 10/23/16.
 */
public class WebService {
    private static final String SERVICE_URL="http://ec2-52-67-53-83.sa-east-1.compute.amazonaws.com:8000/polls/api/";

    public HashMap<Integer,Question> getQuestions() {
        try {
            JSONObject json=new JSONObject(getJSON(SERVICE_URL));
            JSONArray questions=json.getJSONArray("questions");
            JSONArray choices=json.getJSONArray("choices");
            HashMap<Integer,Question> questionsList=new HashMap<Integer,Question>();

            for (int i=0;i<questions.length();i++) {
                int pk=questions.getJSONObject(i).getInt("pk");
                String questionText=questions.getJSONObject(i).getJSONObject("fields").getString("question_text");
                ArrayList<Choice> choiceArrayList=new ArrayList<Choice>();
                Question newQuestion = new Question(pk,questionText);
                newQuestion.setChoices(choiceArrayList);

                questionsList.put(pk,newQuestion);
            }

            //Log.e("QUESTIONS",questionsList.get(1).getChoices().toString());

            for (int j=0; j<choices.length();j++) {
                int pk = choices.getJSONObject(j).getInt("pk");
                String choiceText = choices.getJSONObject(j).getJSONObject("fields").getString("choice_text");
                int votes = choices.getJSONObject(j).getJSONObject("fields").getInt("votes");
                int questionId = choices.getJSONObject(j).getJSONObject("fields").getInt("question");

                Choice newChoice = new Choice(pk, choiceText, votes, questionId);

                questionsList.get(questionId).getChoices().add(newChoice);

            }
            return questionsList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveChoice(Choice choice){

       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_URL)
                .build();

        WebServiceInterface communicatorInterface = retrofit.create(WebServiceInterface.class);
        Callback<ServerResponse> callback = new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                Log.e("POST_RESPONSE", "WORKS");
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.e("POST_RESPONSE", "not work");
            }
        };

        Call<Choice> choiceCall =communicatorInterface.postChoice(choice.getPk(), choice.getChoiceText(), callback);
*/
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("pk", choice.getPk());
            jsonObject.put("question_id", choice.getQuestionId());
            URL url = new URL(SERVICE_URL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = null;
        try {
           httpPost = new HttpPost(SERVICE_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }// Seta o corpo do Post
        try {

            httpPost.setEntity(new StringEntity(jsonObject.toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // Configura o cabeçalho do post informando que é um JSON
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept-Encoding", "application/json");
        httpPost.setHeader("Accept-Language", "en-US");
        // Executa o POST
        try {
            HttpResponse response = httpClient.execute(httpPost);
            //inseriu=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return inseriu;



    }

    protected  String getJSON(String url)  {
        InputStream in;
        String result = "";
        HttpURLConnection urlConnection=null;
        try {
            java.net.URL jsonUrl = new URL(url);

            urlConnection = (HttpURLConnection) jsonUrl.openConnection();

            in = new BufferedInputStream(urlConnection.getInputStream());
            result=convertInputStreamToString(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            urlConnection.disconnect();
        }return result;
    }

    protected static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}

