package com.orangestudio.mobilereader.EntityResult;

import com.google.gson.annotations.SerializedName;
import com.orangestudio.mobilereader.Entity.UserEntity;


/**
 * Created by thienlm on 7/20/2015.
 */
public class LoginResultEntity extends ResultEntityBase {
    @SerializedName("data")
    public UserEntity data;
}
