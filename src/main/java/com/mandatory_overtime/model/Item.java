package com.mandatory_overtime.model;

import com.google.gson.annotations.SerializedName;
import javax.swing.SwingContainer;

public class Item {

//  FIELDS
  @SerializedName("item")
  private String name;
  @SerializedName("location")
  private String location;
  @SerializedName("purpose")
  private String purpose;
  @SerializedName("npc")
  private boolean npc;
  @SerializedName("preReq")
  private String preReq;
  @SerializedName("challenge")
  private Boolean challenge;
  @SerializedName("challengePrompt")
  private String challengePrompt;
  @SerializedName("challengeAnswer")
  private String challengeAnswer;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public boolean isNpc() {
    return npc;
  }

  public void setNpc(boolean npc) {
    this.npc = npc;
  }

  public String getPreReq() {
    return preReq;
  }

  public void setPreReq(String preReq) {
    this.preReq = preReq;
  }

  public Boolean getChallenge() {
    return challenge;
  }

  public void setChallenge(Boolean challenge) {
    this.challenge = challenge;
  }

  public String getChallengePrompt() {
    return challengePrompt;
  }

  public void setChallengePrompt(String challengePrompt) {
    this.challengePrompt = challengePrompt;
  }

  public String getChallengeAnswer() {
    return challengeAnswer;
  }

  public void setChallengeAnswer(String challengeAnswer) {
    this.challengeAnswer = challengeAnswer;
  }
}
