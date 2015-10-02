package com.orangestudio.mobilereader.EntityResult;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thienlm on 7/28/2015.
 */
public class ResultEntityBase {
    @SerializedName("error_code")
    public int error_code;

    @SerializedName("message")
    public String error_msg;
}
