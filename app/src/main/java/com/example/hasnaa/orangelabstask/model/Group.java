package com.example.hasnaa.orangelabstask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by Hasnaa on 27-08-2017.
 */
public class Group {
    @SerializedName("nsid")
    @Expose
    private String nsid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("eighteenplus")
    @Expose
    private Integer eighteenplus;
    @SerializedName("iconserver")
    @Expose
    private String iconserver;
    @SerializedName("iconfarm")
    @Expose
    private Integer iconfarm;
    @SerializedName("members")
    @Expose
    private String members;
    @SerializedName("pool_count")
    @Expose
    private String poolCount;
    @SerializedName("topic_count")
    @Expose
    private String topicCount;
    @SerializedName("privacy")
    @Expose
    private Integer privacy;

    public String getNsid() {
        return nsid;
    }

    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEighteenplus() {
        return eighteenplus;
    }

    public void setEighteenplus(Integer eighteenplus) {
        this.eighteenplus = eighteenplus;
    }

    public String getIconserver() {
        return iconserver;
    }

    public void setIconserver(String iconserver) {
        this.iconserver = iconserver;
    }

    public Integer getIconfarm() {
        return iconfarm;
    }

    public void setIconfarm(Integer iconfarm) {
        this.iconfarm = iconfarm;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getPoolCount() {
        return poolCount;
    }

    public void setPoolCount(String poolCount) {
        this.poolCount = poolCount;
    }

    public String getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(String topicCount) {
        this.topicCount = topicCount;
    }

    public Integer getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }

}
