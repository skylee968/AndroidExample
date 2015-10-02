package com.orangestudio.mobilereader.EntityResult;

import com.google.gson.annotations.SerializedName;
import com.orangestudio.mobilereader.Entity.ServerConfigEntity;


/**
 * Created by thienlm on 7/7/2015.
 */
public class ServerConfigResultEntity extends ResultEntityBase {
    @SerializedName("data")
    public ServerConfigEntity data;
}
