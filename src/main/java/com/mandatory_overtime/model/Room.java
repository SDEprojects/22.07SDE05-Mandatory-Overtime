package com.mandatory_overtime.model;

import com.google.gson.annotations.SerializedName;

public class Room {
  //Simplify field names to remove location
  @SerializedName("directions")
  private String[] directions;//Can become array of rooms.
  @SerializedName("items")
  private String item;
  @SerializedName("NPC")
  private String NPC;
  @SerializedName("RoomName")
  private String displayName;
  @SerializedName("preReq")
  private String preReq;
  @SerializedName("fail")
  private Boolean failCondition;

  public String[] getDirections() {
    return directions;
  }

  public void setDirections(String[] directions) {
    this.directions = directions;
  }

  public String getItem() {
    return item;
  }

  public void setItem(String item) {
    this.item = item;
  }

  public String getNPC() {
    return NPC;
  }

  public void setNPC(String NPC) {
    this.NPC = NPC;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getPreReq() {
    return preReq;
  }

  public void setPreReq(String preReq) {
    this.preReq = preReq;
  }

  public Boolean getFailCondition() {
    return failCondition;
  }

  public void setFailCondition(Boolean failCondition) {
    this.failCondition = failCondition;
  }
}
