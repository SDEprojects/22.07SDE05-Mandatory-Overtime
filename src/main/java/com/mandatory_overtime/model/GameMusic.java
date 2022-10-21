package com.mandatory_overtime.model;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.sound.sampled.UnsupportedAudioFileException;

public class GameMusic {
  private static Clip clip;



  static Boolean musicOnOff = true;
  static Player player = new Player();
  static Building building;

  static {
    try {
      building = new Building();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public GameMusic() throws IOException {
  }


  public static void playAudio(String soundFile) {

    try (InputStream audio = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(soundFile)) {

      AudioInputStream audioInput = AudioSystem.getAudioInputStream(audio);
      clip = AudioSystem.getClip();
      clip.open(audioInput);
      startAudio();
    } catch (UnsupportedAudioFileException e) {
//      e.printStackTrace();
    } catch (Exception e) {
//      e.printStackTrace();
    }
  }

  public static void musicOnOff(String noun) {
    if (musicOnOff && noun.equals("off")) {
      clip.stop();
      setMusicOnOff(false);
    } else if (!musicOnOff && noun.equals("on")){
      startBackgroundMusic();
      setMusicOnOff(true);
    }
  }


  public static void startAudio() throws InterruptedException {
    clip.start();
    clip.loop(0);
    clip.wait();
  }

  public static void startBackgroundMusic() {
    playAudio("Spooky_Music.wav");
  }

  public static void playRoomSound(String noun) throws InterruptedException {
      playAudio(building.getBuilding().get(noun).getAudio1());
    Thread.sleep(500);
    playAudio(building.getBuilding().get(noun).getAudio2());
    Thread.sleep(1000);
    playAudio(building.getBuilding().get(noun).getAudio3());
  }
  public static void playItemSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    if (building.getBuilding().get(currentLoc).getInventoryAudio() != null) {
      playAudio(building.getBuilding().get(currentLoc).getInventoryAudio());
      Thread.sleep(1000);
    }
  }

  public static void playMoveSound(String noun) throws InterruptedException {
    playAudio(building.getBuilding().get(noun).getFootstepAudio());
//    Thread.sleep(250);
  }

  public static void playDrawerSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudio(building.getBuilding().get(currentLoc).getDrawerOpenAudio());
    Thread.sleep(1000);
  }

  public static void playAccessDeniedSound(String noun) throws InterruptedException {
    playAudio(building.getBuilding().get(noun).getAccessDeniedAudio());
    Thread.sleep(1000);
  }

  public static void playAccessGrantedSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudio(building.getBuilding().get(currentLoc).getAccessGrantedAudio());
    Thread.sleep(1000);
  }

  public static void playDoorOpenSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudio(building.getBuilding().get(currentLoc).getDoorOpenAudio());
    Thread.sleep(1000);
  }

  public static void playVendingMachineSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudio(building.getBuilding().get(currentLoc).getVendingMachineAudio());
    Thread.sleep(1000);
  }

  public static void playTypingSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudio(building.getBuilding().get(currentLoc).getTypingAudio());
    Thread.sleep(1000);
  }

  public static void playPhoneTyping() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudio(building.getBuilding().get(currentLoc).getPhoneTypingAudio());
    Thread.sleep(1000);

  }

  public static void playPhoneUnlockingSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    if (building.getBuilding().get(currentLoc).getPhoneUnlockingAudio() != null) {
      playAudio(building.getBuilding().get(currentLoc).getPhoneUnlockingAudio());
      Thread.sleep(1000);
    }
  }


  public static Boolean getMusicOnOff() {
    return musicOnOff;
  }

  public static void setMusicOnOff(Boolean musicOnOff) {
    GameMusic.musicOnOff = musicOnOff;
  }
}
