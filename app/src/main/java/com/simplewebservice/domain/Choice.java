package com.simplewebservice.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Demis on 10/23/16.
 */
public class Choice {
    @SerializedName("pk")
    private int pk;
    @SerializedName("choice_text")
    private String choiceText;
    @SerializedName("votes")
    private int votes;
    @SerializedName("question")
    private int questionId;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Choice(int pk, String choiceText, int votes, int questionId) {
        this.pk = pk;
        this.choiceText = choiceText;
        this.votes = votes;
        this.questionId = questionId;
    }
}
