package Utils;

import java.io.File;
import javax.sound.sampled.*;

/**
 * A class desgined to make loading sounds into a program easier.
 * @author Jacob Hogrefe
 */
public class Sound implements LineListener {

    protected boolean playCompleted, doesSoundLoop;
    protected Clip soundClip;

    /**
     * An object representing a sound.
     * @param soundFileName The name of the sound file (do not include path)
     * @param doesSoundLoop Determines if the sound will keep playing after it has finished playing
     * @author Jacob Hogrefe
     */
    public Sound(String soundFileName, boolean doesSoundLoop) {
        this.doesSoundLoop = doesSoundLoop;
        try {
            File soundFile = new File(soundFileName);
            AudioInputStream soundStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat soundFormat = soundStream.getFormat();
            DataLine.Info soundInfo = new DataLine.Info(Clip.class, soundFormat);
            soundClip = (Clip) AudioSystem.getLine(soundInfo);
            soundClip.addLineListener(this);
            soundClip.open(soundStream);
        } catch (Exception e) {}
    }

    /**
     * Checks if the current sound has stopped playing.
     * @author Jacob Hogrefe
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.STOP && !doesSoundLoop) {
            playCompleted = true;
        }
    }

    /**
     * Plays the sound associated with this object.
     * @author Jacob Hogrefe
     */
    public void play() {
        System.out.println("Playing sound");
        if (soundClip != null && !doesSoundLoop) {
            soundClip.start();
        } else if (soundClip != null && doesSoundLoop) {
            soundClip.loop(Clip.LOOP_CONTINUOUSLY);
            soundClip.start();
        }
    }

    /**
     * Closes the current sound to free up memory being used by it.
     * @author Jacob Hogrefe
     */
    public void close() {
        if (soundClip != null) {
            soundClip.stop();
            soundClip.close();
        }
    }

    /**
     * Pauses the current sound associated with this object.
     * @author Jacob Hogrefe
     */
    public void pause() {
        if (soundClip != null) {
            soundClip.stop();
        }
    }

    /**
     * Sets the position of the sound to the beginning (useful for looping sounds that need to be restarted).
     * @author Jacob Hogrefe
     */
    public void restart() {
        if (soundClip != null) {
            soundClip.setMicrosecondPosition(0);
        }
    }

    /**
     * Pauses and restarts the current sound associated with this object.
     * @author Jacob Hogrefe
     */
    public void stop() {
        if (soundClip != null) {
            soundClip.stop();
            soundClip.setMicrosecondPosition(0);
        }
    }

    /**
     * Checks if the sound associated with this object has completed playing.
     * @return Whether or not the sound has finished playing
     * @author Jacob Hogrefe
     */
    public boolean isPlayComplete() {
        return playCompleted;
    }
}
