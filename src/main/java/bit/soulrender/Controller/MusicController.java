package bit.soulrender.Controller;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicController {

	private int musicPosition = 0;
	private String[] musics = {"heartache.wav", "true_undyne.wav", "asgore.wav", "spider_dance.wav"};
	private static Clip clip;

	public void start() {
		File file = new File("src/main/resources/Music/" + musics[musicPosition]);
		try {
			clip = AudioSystem.getClip();
			AudioInputStream audio = AudioSystem.getAudioInputStream(file);
			clip.open(audio);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("Error: " + ex.getMessage());

		}
	}

	public void stop() {
		clip.close();
	}

	public boolean isPlaying() {
		return clip.isRunning();
	}

	public void setVolume(float volume) {
		if (clip.isRunning()) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(20f * (float) Math.log10(volume));
		}
	}

	public void nextMusic() {
		if (musicPosition == 3) {
			musicPosition = 0;
		} else {
			musicPosition++;
		}
		clip.close();
		start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}

	public void previousMusic() {
		if (musicPosition == 0) {
			musicPosition = 3;
		} else {
			musicPosition--;
		}
		clip.close();
		start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}

}
