package com.mandatory_overtime.model.exception;

public class MissingRequirementException extends IllegalArgumentException {

  public MissingRequirementException(String noun){
    System.out.println("Looks like you're missing something in inventory. You won't be able to proceed to " + noun.toUpperCase() + " until you do.");
  }

}
