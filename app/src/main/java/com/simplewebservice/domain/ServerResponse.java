package com.simplewebservice.domain;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Demis on 10/24/16.
 */
public class ServerResponse implements Serializable{
    @SerializedName("pk")
    private int pk;

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }
}
