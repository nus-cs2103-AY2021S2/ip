package duke;

/** Barking Noise enabled during start
 * and duke doge termination to greet and bid
 * farewell to the user
 *
 * @author Chia Jia-Xi, Kymie
 * @version 0.2
 * @since 2021-02-24
 */

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class SoundBark {

        public String audioPath = "./src/main/resources/sfx/464400__michael-grinnell__dog-bark.wav";

    void playAudio() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        File soundFile = new File(audioPath);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);

        clip.start();
    }
}