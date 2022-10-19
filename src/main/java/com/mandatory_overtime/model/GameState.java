package com.mandatory_overtime.model;

/** Class controls the State of the game **/
public enum GameState {

  IN_PROGRESS {
    @Override
    public boolean isTerminal() {
      return false;
    }
  },
  WIN,
  LOSS;

  //FAIL_RESTART
  //PLAYER_MOVEMENT
  //NPC_MOVEMENT
  //PLAYER_WIN
  //NPC_WIN

  public boolean isTerminal(){
    return true;
  }

}
