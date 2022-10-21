package com.mandatory_overtime.model;

import com.google.gson.annotations.SerializedName;

/**
 * Provides java structure of game Items for imported ItemStructure
 */
public class Item {

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
  @SerializedName("challenge prompt")
  private String challengePrompt;
  @SerializedName("challenge answer")
  private String challengeAnswer;
  @SerializedName("acquired")
  private Boolean acquired;

  public Boolean getAcquired() {
    return acquired;
  }

  public void setAcquired(Boolean acquired) {
    this.acquired = acquired;
  }

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
