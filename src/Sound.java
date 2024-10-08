import java.io.*;
import javax.sound.sampled.*;

/**
 * Sound class
 * Made with javax.sound.sampled
 * 
 * Supports WAV, AIFF, AU formats 
 * (not all WAV files supported - it depends on file encoding)
 */
public class Sound {
    private Clip sound;

    public Sound(String filepath) {
        sound = loadSound(filepath);
    }
    
    public Sound(String filepath, int vol) {
        sound = loadSound(filepath);
        setVolume(vol);
    }

    /**
     * Plays sound one time (async)
     */
    public boolean play() {
        try {
            sound.start();
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Stops sound from playing
     */
    public boolean stop() {
        try {
            sound.stop();
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    /**
     * Resets sound to play again
     */
    public boolean reset() {
        try {
            sound.stop();
            sound.setFramePosition(0);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    /**
     * Loops the sound
     */
    public boolean loop() {
        try {
            sound.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    /**
     * Sets volume of the sound (in percents)
     */
    public boolean setVolume(int vol) {
        vol = Math.min(Math.max(vol, 0), 100);
        double val = vol / 100.0;
        try {
            FloatControl volControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(val == 0.0 ? 0.0001 : val) / Math.log(10.0) * 20.0);
            volControl.setValue(dB);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    /**
     * Loads sound from file
     */
    private Clip loadSound(String filepath) {
        Clip newClip;

        try {
            InputStream istream = Sound.class.getResourceAsStream(filepath);
            AudioInputStream aistream = AudioSystem.getAudioInputStream(istream);
            AudioFormat format = aistream.getFormat();

            // we cannot play ALAW/ULAW, so we convert them to PCM
            if ((format.getEncoding() == AudioFormat.Encoding.ULAW) ||
                (format.getEncoding() == AudioFormat.Encoding.ALAW)) {
                AudioFormat tmp = new AudioFormat(
                                          AudioFormat.Encoding.PCM_SIGNED, 
                                          format.getSampleRate(),
                                          format.getSampleSizeInBits() * 2,
                                          format.getChannels(),
                                          format.getFrameSize() * 2,
                                          format.getFrameRate(),
                                          true);
                aistream = AudioSystem.getAudioInputStream(tmp, aistream);
                format = tmp;
            }
            DataLine.Info info = new DataLine.Info(Clip.class, 
                                           aistream.getFormat(),
                                           ((int) aistream.getFrameLength() * format.getFrameSize()));
            newClip = (Clip) AudioSystem.getLine(info);
            newClip.open(aistream);
            return newClip;
        } catch (Exception ex) {
            System.err.println("Unable to load sound:\n\t" + filepath);
            return null;
        }
    }
}
