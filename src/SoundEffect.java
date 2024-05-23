

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect implements Runnable  {
    Thread t;
    File audioFile ;
    AudioInputStream audioStream;
    Clip audioClip;
    String fn;
    boolean loopsSoundEffect;
    public SoundEffect(String fileName, boolean loops) {
        fn = fileName;
        loopsSoundEffect = loops;
    }

    public void playOnThread() {
        Thread t = new Thread(this, "xyz");
        t.start();
    }
    private void playSound() {
        URL audioUrl = SoundEffect.class.getResource("/audio/" + fn);
        try {
            audioFile = new File(audioUrl.toURI());
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.start();
            if(loopsSoundEffect) {
                audioClip.loop(audioClip.LOOP_CONTINUOUSLY);;
            }
            audioClip.open(audioStream);
            audioClip.start();
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void stopSound() {
        audioClip.stop();
    }

    @Override
    public void run() {
        playSound();
    }
}
