package com.example.hasnaa.orangelabstask.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Hasnaa on 27-08-2017.
 */
public class GroupsSearch {

    @SerializedName("groups")
    @Expose
    private Groups groups;
    @SerializedName("stat")
    @Expose
    private String stat;

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
