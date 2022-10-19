package com.mandatory_overtime.model;

import com.google.gson.annotations.SerializedName;

public class Room {
  //Simplify field names to remove location
  @SerializedName("directions")
  private String[] locationsDirections;//Can become array of rooms.
  @SerializedName("items")
  private String locationItem;
  @SerializedName("NPC")
  private String locationNPC;
  @SerializedName("RoomName")
  private String DisplayName;
  @SerializedName("preReq")
  private String locationPreReqs;
  @SerializedName("fail")
  private Boolean FailCondition;

  public String[] getLocationsDirections() {
    return locationsDirections;
  }

  public void setLocationsDirections(String[] locationsDirections) {
    this.locationsDirections = locationsDirections;
  }

  public String getLocationItem() {
    return locationItem;
  }

  public void setLocationItem(String locationItem) {
    this.locationItem = locationItem;
  }

  public String getLocationNPC() {
    return locationNPC;
  }

  public void setLocationNPC(String locationNPC) {
    this.locationNPC = locationNPC;
  }

  public String getDisplayName() {
    return DisplayName;
  }

  public void setDisplayName(String locationDisplayName) {
    this.DisplayName = locationDisplayName;
  }

  public String getLocationPreReqs() {
    return locationPreReqs;
  }

  public void setLocationPreReqs(String locationPreReqs) {
    this.locationPreReqs = locationPreReqs;
  }


}
