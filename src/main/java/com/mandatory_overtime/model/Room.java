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
  @SerializedName("description")
  private String description;
  @SerializedName("sound1")
  private String audio1;
  @SerializedName("sound2")
  private String audio2;
  @SerializedName("sound3")
  private String audio3;
  @SerializedName("footstepSound")
  private String footstepAudio;
  @SerializedName("inventorySound")
  private String inventoryAudio;
  @SerializedName("backgroundMusic")
  private Boolean backgroundMusic;
  @SerializedName("musicOnOff")
  private Boolean musicOnOff;
  @SerializedName("typingSound")
  private String typingAudio;
  @SerializedName("drawerOpenSound")
  private String drawerOpenAudio;
  @SerializedName("accessGrantedSound")
  private String accessGrantedAudio;
  @SerializedName("accessDeniedSound")
  private String accessDeniedAudio;
  @SerializedName("doorOpenSound")
  private String doorOpenAudio;
  @SerializedName("vendingMachineSound")
  private String vendingMachineAudio;
  @SerializedName("phoneTypingSound")
  private String phoneTypingAudio;
  @SerializedName("phoneUnlockingSound")
  private String phoneUnlockingAudio;

  public Boolean getMusicOnOff() {
    return musicOnOff;
  }

  public void setMusicOnOff(Boolean musicOnOff) {
    this.musicOnOff = musicOnOff;
  }
  public String getPhoneTypingAudio() {
    return phoneTypingAudio;
  }
  public void setPhoneTypingAudio(String phoneTypingAudio) {
    this.phoneTypingAudio = phoneTypingAudio;
  }
  public String getPhoneUnlockingAudio() {
    return phoneUnlockingAudio;
  }
  public void setPhoneUnlockingAudio(String phoneUnlockingAudio) {
    this.phoneUnlockingAudio = phoneUnlockingAudio;
  }
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTypingAudio() {
    return typingAudio;
  }

  public void setTypingAudio(String typingAudio) {
    this.typingAudio = typingAudio;
  }

  public String getDrawerOpenAudio() {
    return drawerOpenAudio;
  }

  public void setDrawerOpenAudio(String drawerOpenAudio) {
    this.drawerOpenAudio = drawerOpenAudio;
  }

  public String getAccessGrantedAudio() {
    return accessGrantedAudio;
  }

  public void setAccessGrantedAudio(String accessGrantedAudio) {
    this.accessGrantedAudio = accessGrantedAudio;
  }
  public String getAccessDeniedAudio() {
    return accessDeniedAudio;
  }

  public void setAccessDeniedAudio(String accessDeniedAudio) {
    this.accessDeniedAudio = accessDeniedAudio;
  }
  public String getDoorOpenAudio() {
    return doorOpenAudio;
  }

  public void setDoorOpenAudio(String doorOpenAudio) {
    this.doorOpenAudio = doorOpenAudio;
  }

  public String getVendingMachineAudio() {
    return vendingMachineAudio;
  }

  public void setVendingMachineAudio(String vendingMachineAudio) {
    this.vendingMachineAudio = vendingMachineAudio;
  }

  public String getFootstepAudio() {
    return footstepAudio;
  }

  public void setFootstepAudio(String footstepAudio) {
    this.footstepAudio = footstepAudio;
  }

  public String getInventoryAudio() {
    return inventoryAudio;
  }

  public void setInventoryAudio(String inventoryAudio) {
    this.inventoryAudio = inventoryAudio;
  }

  public Boolean getBackgroundMusic() {
    return backgroundMusic;
  }

  public void setBackgroundMusic(Boolean backgroundMusic) {
    this.backgroundMusic = backgroundMusic;
  }
  public String getAudio2() {
    return audio2;
  }

  public void setAudio2(String audio2) {
    this.audio2 = audio2;
  }

  public String getAudio3() {
    return audio3;
  }

  public void setAudio3(String audio3) {
    this.audio3 = audio3;
  }

  public String getAudio1() {
    return audio1;
  }

  public void setAudio1(String audio) {
    this.audio1 = audio;
  }


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
