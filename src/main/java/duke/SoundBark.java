package duke;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/** Barking Noise enabled during start
 * and duke doge termination to greet and bid
 * farewell to the user
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.2
 * @since 2021-02-24
 */
public class SoundBark {

    private String audioPath = "./src/main/java/sfx/464400__michael-grinnell__dog-bark.wav";

    /**
     * Enables JavaFX to play bark audio clip
     * at greeting and farewell
     *
     * @throws IOException
     * @throws LineUnavailableException if Audio Byte format is off (16-bit supported by Desktop)
     * @throws UnsupportedAudioFileException if Audio file is in incorrect format
     */
    // Solution below adapted from https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html
    void playAudio() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File soundFile = new File(audioPath);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);

        clip.start();
    }
}
