package com.mandatory_overtime.model;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.sound.sampled.UnsupportedAudioFileException;

public class GameMusic {
  private static Clip clip;

  public GameMusic() throws IOException {
  }

  public void playAudio(String soundFile) {

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

  public void stopAudio() {
    if (clip.isRunning()) {
      clip.stop();
    } else {
      clip.start();
    }
  }

  public void startAudio() throws InterruptedException {
    clip.start();
    clip.loop(0);
    clip.wait();
  }

  public void startBackgroundMusic() {
    playAudio("Spooky_Music.wav");
  }
}
