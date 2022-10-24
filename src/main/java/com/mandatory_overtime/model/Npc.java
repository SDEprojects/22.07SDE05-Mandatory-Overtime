package com.mandatory_overtime.model;

import com.google.gson.annotations.SerializedName;

public class Npc {



  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getInitialDialogue() {
    return initialDialogue;
  }

  public void setInitialDialogue(String initialDialogue) {
    this.initialDialogue = initialDialogue;
  }

  public String getDialogueNoItem() {
    return dialogueNoItem;
  }

  public void setDialogueNoItem(String dialogueNoItem) {
    this.dialogueNoItem = dialogueNoItem;
  }

  public String getDialogueWithItem() {
    return dialogueWithItem;
  }

  public void setDialogueWithItem(String dialogueWithItem) {
    this.dialogueWithItem = dialogueWithItem;
  }

  public String getItems() {
    return items;
  }

  public void setItems(String items) {
    this.items = items;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @SerializedName("location")
  private String location;//Can become array of rooms.
  @SerializedName("initialDialogue")
  private String initialDialogue;
  @SerializedName("dialogueNoItem")
  private String dialogueNoItem;
  @SerializedName("dialogueWithItem")
  private String dialogueWithItem;
  @SerializedName("itemsDropped")
  private String items;
  @SerializedName("NpcName")
  private String name;

  public String getDialogueQuestDone() {
    return dialogueQuestDone;
  }

  public void setDialogueQuestDone(String dialogueQuestDone) {
    this.dialogueQuestDone = dialogueQuestDone;
  }

  @SerializedName("dialogueQuestDone")
  private String dialogueQuestDone;

  public String getPrereq() {
    return prereq;
  }

  public void setPrereq(String prereq) {
    this.prereq = prereq;
  }

  @SerializedName("prereq")
  private String prereq;

  public Integer getNpcCount() {
    return npcCount++;
  }

  public void setNpcCount(Integer npcCount) {
    this.npcCount = npcCount;
  }

  @SerializedName("interactionCount")
  private Integer npcCount;



}