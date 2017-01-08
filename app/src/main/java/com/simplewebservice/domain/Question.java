package com.simplewebservice.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Demis on 10/23/16.
 */

public class Question implements Serializable {
    @SerializedName("pk")
    private int pk;
    @SerializedName("question_text")
    private String questionText;
    private ArrayList<Choice> choices;

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Question(int pk, String questionText) {
        this.pk = pk;
        this.questionText = questionText;
    }
}
