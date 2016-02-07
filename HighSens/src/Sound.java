import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.DataLine; 
import javax.sound.sampled.FloatControl; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.SourceDataLine; 
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound extends Thread {
    
    private static int PLAY_COUNT;
    private String filename;
    
    private Position curPosition;
 
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb 
 
    enum Position { 
        LEFT, RIGHT, NORMAL
    };

    public Sound(String wavfile, int count) { 
        filename = wavfile;
        PLAY_COUNT = count;
        curPosition = Position.NORMAL;
    } 

    public Sound(String wavfile, Position p, int count) { 
        filename = wavfile;
        PLAY_COUNT = count;
        curPosition = p;
    } 

    public void run() { 

    	InputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filename);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		InputStream inputStream = new BufferedInputStream(fileInputStream);
        
        AudioInputStream audioInputStream = null;
        try { 
            audioInputStream = AudioSystem.getAudioInputStream(inputStream);
        } catch (UnsupportedAudioFileException e1) { 
            e1.printStackTrace();
            return;
        } catch (IOException e1) { 
            e1.printStackTrace();
            return;
        } 
        if (! audioInputStream.markSupported())
		{
			System.out.println("###  resetting not supported");
			System.exit(1);
		}

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try { 
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (LineUnavailableException e) { 
            e.printStackTrace();
            return;
        } catch (Exception e) { 
            e.printStackTrace();
            return;
        } 

        if (auline.isControlSupported(FloatControl.Type.PAN)) { 
            FloatControl pan = (FloatControl) auline
                    .getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT) 
                pan.setValue(1.0f);
            else if (curPosition == Position.LEFT) 
                pan.setValue(-1.0f);
        } 

        int nBytesRead = 0;
        int nPlayCount = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];

		if (audioInputStream.getFrameLength() == AudioSystem.NOT_SPECIFIED ||
			format.getFrameSize() == AudioSystem.NOT_SPECIFIED)
		{
			System.out.println("cannot calculate length of AudioInputStream!");
			System.exit(1);
		}
		long lStreamLengthInBytes = audioInputStream.getFrameLength()
			* format.getFrameSize();
		if (lStreamLengthInBytes > Integer.MAX_VALUE)
		{
			System.out.println("length of AudioInputStream exceeds 2^31, cannot properly reset stream!");
			System.exit(1);
		}
		int nStreamLengthInBytes = (int) lStreamLengthInBytes;

		auline.start();

		while (nPlayCount < PLAY_COUNT)
		{
			nPlayCount++;
			audioInputStream.mark(nStreamLengthInBytes);
			nBytesRead = 0;
			
			//System.out.println(nPlayCount + " out of " + PLAY_COUNT);
			
			while (nBytesRead != -1)
			{
				try
				{
					//System.out.println("Reading Audio: " + audioInputStream.toString());
					nBytesRead = audioInputStream.read(abData, 0, abData.length);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				if (nBytesRead >= 0)
				{
					//System.out.println("Writing Audio: " + audioInputStream.getClass());
					int	nBytesWritten = auline.write(abData, 0, nBytesRead);
				}
			}
			try {
				//System.out.println("Resetting: " + audioInputStream.toString());
				audioInputStream.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		auline.drain();
		auline.close();
    } 
}
