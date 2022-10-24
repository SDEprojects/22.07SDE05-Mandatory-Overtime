package com.mandatory_overtime.model;


import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameMusic {

  private static Clip clip;
  static Boolean musicOnOff = true;
  static Player player = new Player();
//  static Building building;
static Building building;


  static {
    try {
      building = new Building();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public GameMusic() {

  }


  public static void playAudioFX(String soundFile) {

    try (InputStream audio = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(soundFile)) {

      AudioInputStream audioInput = AudioSystem.getAudioInputStream(audio);
      clip = AudioSystem.getClip();
      clip.open(audioInput);
      startAudio();

    } catch (UnsupportedAudioFileException e) {
    } catch (Exception e) {
    }
  }

  public static void playAudioMusic(String soundFile) {

    try (InputStream audio = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(soundFile)) {

      AudioInputStream audioInput = AudioSystem.getAudioInputStream(audio);
      clip = AudioSystem.getClip();
      clip.open(audioInput);
      startBackgroundAudio();
    } catch (UnsupportedAudioFileException e) {
    } catch (Exception e) {
    }
  }

  public static void musicOnOff(String noun) {
    if (noun.equals("off")) {
      clip.stop();
      clip.flush();
    }
    if (noun.equals("on")) {
      clip.stop();
      clip.flush();
      startBackgroundMusic();
    }
  }


  public static void startAudio() throws InterruptedException {
    clip.start();
    clip.loop(0);
    clip.wait();
  }

  public static void startBackgroundAudio() throws InterruptedException {
    clip.start();
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    clip.wait();
  }


  public static void startBackgroundMusic() {
    if (musicOnOff == false) {
      clip.stop();
      playAudioMusic("Spooky_Music.wav");
      setMusicOnOff(true);
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-9.0f);
    } else {
      playAudioMusic("Spooky_Music.wav");
      setMusicOnOff(false);
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-9.0f);
    }


  }


  public static void volumeUpDown(String noun) {
    if (noun.equals("0")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(gainControl.getMinimum());
      clip.start();
    } else if (noun.equals("1")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-21.0f);
      clip.start();
    } else if (noun.equals("2")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-18.0f);
      clip.start();
    } else if (noun.equals("3")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-15.0f);
      clip.start();
    } else if (noun.equals("4")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-12.0f);
      clip.start();
    } else if (noun.equals("5")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-9.0f);
      clip.start();
    } else if (noun.equals("6")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-6.0f);
      clip.start();
    } else if (noun.equals("7")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(-3.0f);
      clip.start();
    } else if (noun.equals("8")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(0.0f);
      clip.start();
    } else if (noun.equals("9")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(3.0f);
      clip.start();
    } else if (noun.equals("10")) {
      FloatControl gainControl =
          (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      gainControl.setValue(gainControl.getMaximum());
      clip.start();
    }
  }


  public static void playRoomSound(String noun) throws InterruptedException {
    playAudioFX(building.getBuilding().get(noun).getAudio1());
    Thread.sleep(500);
    playAudioFX(building.getBuilding().get(noun).getAudio2());
    Thread.sleep(1000);
    playAudioFX(building.getBuilding().get(noun).getAudio3());
  }

  public static void playItemSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    if (building.getBuilding().get(currentLoc).getInventoryAudio() != null) {
      playAudioFX(building.getBuilding().get(currentLoc).getInventoryAudio());
      Thread.sleep(1000);
    }
  }

  public static void playMoveSound(String noun) throws InterruptedException {
    playAudioFX(building.getBuilding().get(noun).getFootstepAudio());
//    Thread.sleep(250);
  }

  public static void playDrawerSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudioFX(building.getBuilding().get(currentLoc).getDrawerOpenAudio());
    Thread.sleep(1000);
  }

  public static void playAccessDeniedSound(String noun) throws InterruptedException {
    playAudioFX(building.getBuilding().get(noun).getAccessDeniedAudio());
    Thread.sleep(1000);
  }


  public static void playAccessGrantedSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudioFX(building.getBuilding().get(currentLoc).getAccessGrantedAudio());
    Thread.sleep(1000);
  }

  public static void playDoorOpenSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudioFX(building.getBuilding().get(currentLoc).getDoorOpenAudio());
    Thread.sleep(1000);
  }

  public static void playVendingMachineSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudioFX(building.getBuilding().get(currentLoc).getVendingMachineAudio());
    Thread.sleep(1000);
  }

  public static void playTypingSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudioFX(building.getBuilding().get(currentLoc).getTypingAudio());
    Thread.sleep(1000);
  }

  public static void playPhoneTyping() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    playAudioFX(building.getBuilding().get(currentLoc).getPhoneTypingAudio());
    Thread.sleep(1000);

  }

  public static void playPhoneUnlockingSound() throws InterruptedException {
    String currentLoc = player.getCurrentLocation();
    if (building.getBuilding().get(currentLoc).getPhoneUnlockingAudio() != null) {
      playAudioFX(building.getBuilding().get(currentLoc).getPhoneUnlockingAudio());
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
