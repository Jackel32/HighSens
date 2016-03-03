package com.highsens.game;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayer {
	AudioData AudioData;

	private static HashMap<String, AudioData> soundMap = new HashMap<String, AudioData>();

	/**
	 * Loads a sound clip from a file and gives it the specified name. This name
	 * can be used when calling the <code>{@link #play play}</code> and
	 * <code>{@link #stop stop}</code> methods. The sound clip is completely
	 * loaded into memory, so it is recommended that this method be used for
	 * loading small or short sounds. For longer sounds, consider using the
	 * <code>{@link #loadStream(String, String) loadStream}</code> method.
	 *
	 * @param soundName
	 *            the name to give this audio stream
	 * @param filename
	 *            the name of the file to load the audio stream from
	 *
	 * @return <code>true</code> if the clip loaded successfully,
	 *         <code>false</code> otherwise
	 */
	public static boolean loadClip(String soundName, String filename) {
		try {
			return loadClip(soundName, AudioSystem.getAudioInputStream(new File(filename)));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Loads a sound clip from a <code>{@link java.net.URL}</code> and gives it
	 * the specified name. This name can be used when calling the
	 * <code>{@link #play play}</code> and <code>{@link #stop stop}</code>
	 * methods. The sound clip is completely loaded into memory, so it is
	 * recommended that this method be used for loading small or short sounds.
	 * For longer sounds, consider using the
	 * <code>{@link #loadStream(String, URL) loadStream}</code> method.
	 *
	 * @param soundName
	 *            the name to give this audio stream
	 * @param url
	 *            the name of the file to load the audio stream from
	 *
	 * @return <code>true</code> if the clip loaded successfully,
	 *         <code>false</code> otherwise
	 */
	public static boolean loadClip(String soundName, URL url) {
		try {

			// return loadClip(soundName, AudioSystem.getAudioInputStream(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean loadClip(String soundName, AudioInputStream audioInputStream) {
		boolean retVal = true;

		try {
			// convert the AudioInputStream to PCM format -- needed for loading
			// mp3 files and files in other formats
			// audioInputStream = convertToPCM(audioInputStream);

			// get a line for the Clip and load the audio from the input stream
			// DataLine.Info info = new DataLine.Info(Clip.class,
			// audioInputStream.getFormat());
			// Clip clip = (Clip) AudioSystem.getLine(info);
			// clip.open(audioInputStream);

			// AudioData ad = new AudioData();
			// ad.audioInputStream = audioInputStream;
			// ad.dataLine = clip;
			//
			// soundMap.put(soundName, ad);
		} catch (Exception e) {
			e.printStackTrace();
			retVal = false;
		} finally {
			try {
				audioInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
				retVal = false;
			}
		}

		return retVal;
	}

	/**
	 * Converts an AudioInputStream to PCM_SIGNED format if it is not already
	 * either PCM_SIGNED or PCM_UNSIGNED.
	 */
	private static AudioInputStream convertToPCM(AudioInputStream audioInputStream) {
		AudioFormat format = audioInputStream.getFormat();

		if ((format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED)
				&& (format.getEncoding() != AudioFormat.Encoding.PCM_UNSIGNED)) {
			AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16,
					format.getChannels(), format.getChannels() * 2, format.getSampleRate(), format.isBigEndian());
			audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
		}

		return audioInputStream;
	}

	/**
	 * Loads an audio stream from a file and gives it the specified name. This
	 * name can be used when calling the <code>{@link #play play}</code> and
	 * <code>{@link #stop stop}</code> methods.
	 *
	 * @param soundName
	 *            the name to give this audio stream
	 * @param filename
	 *            the name of the file to load the audio stream from
	 *
	 * @return <code>true</code> if the audio stream loaded successfully,
	 *         <code>false</code> otherwise
	 */
	public static boolean loadStream(String soundName, String filename) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
			return loadStream(soundName, audioInputStream);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Loads an audio stream from a <code>{@link java.net.URL}</code> and gives
	 * it the specified name. This name can be used when calling the
	 * <code>{@link #play play}</code> and <code>{@link #stop stop}</code>
	 * methods.
	 *
	 * @param soundName
	 *            the name to give this audio stream
	 * @param url
	 *            the name of the file to load the audio stream from
	 *
	 * @return <code>true</code> if the audio stream loaded successfully,
	 *         <code>false</code> otherwise
	 */
	public static boolean loadStream(String soundName, URL url) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			return loadStream(soundName, audioInputStream);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean loadStream(String soundName, AudioInputStream audioInputStream) {
		boolean retVal = true;

		try {
			// convert the audio input stream to a buffered input stream that
			// supports
			// mark() and reset()
			BufferedInputStream bufferedInputStream = new BufferedInputStream(audioInputStream);
			audioInputStream = new AudioInputStream(bufferedInputStream, audioInputStream.getFormat(),
					audioInputStream.getFrameLength());

			try {
				// convert the AudioInputStream to PCM format -- needed for
				// loading
				// mp3 files and files in other formats
				audioInputStream = convertToPCM(audioInputStream);

				DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioInputStream.getFormat());
				SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);

				AudioData ad = new AudioData();
				ad.audioInputStream = audioInputStream;
				ad.dataLine = sourceDataLine;

				soundMap.put(soundName, ad);
				audioInputStream.mark(2000000000);
				sourceDataLine.open(audioInputStream.getFormat());
			} catch (Exception e) {
				e.printStackTrace();
				retVal = false;
			} finally {
				// ain.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			retVal = false;
		}

		return retVal;
	}

	/**
	 * Plays a sound that has already been loaded by one of the sound loading
	 * methods. The sound can be played once or looped forever. If the specified
	 * sound name does not exist, this method does nothing.
	 *
	 * @param soundName
	 *            the name of the sound to play
	 * @param loop
	 *            <code>true</code> if the sound should loop forever,
	 *            <code>false</code> if the sound should play once
	 */
	public static void play(String soundName, boolean loop) {
		// AudioData ad = soundMap.get(soundName);
		// if (ad != null) {
		// if ((ad.thread == null) || (!ad.thread.isAlive())) {
		// if (ad.dataLine instanceof SourceDataLine) {
		// PlayStreamThread pt = new PlayStreamThread(ad.audioInputStream,
		// (SourceDataLine) ad.dataLine);
		// ad.thread = pt;
		// } else if (ad.dataLine instanceof Clip) {
		// PlayClipThread pt = new PlayClipThread((Clip) ad.dataLine);
		// ad.thread = pt;
		// } else {
		// return;
		// }
		//
		// ad.thread.setLooping(loop);
		// ad.thread.start();
		// } else {
		// ad.thread.stopSound();
		// ad.thread.setLooping(loop);
		// ad.thread.playSound();
		// }
		// }
	}

	/**
	 * Stops playing the specified sound.
	 *
	 * @param soundName
	 *            the name of the sound to stop
	 */
	public static void stop(String soundName) {
		AudioData ad = soundMap.get(soundName);
		if (ad != null) {
			if (ad.thread != null) {
				ad.thread.stopSound();
			}
		}
	}

	/**
	 * Stops all playing sounds and closes all lines and audio input streams.
	 * Any previously loaded sounds will have to be re-loaded to be played
	 * again.
	 */
	public static void shutdown() {
		for (AudioData ad : soundMap.values()) {
			if (ad != null) {
				if (ad.thread != null) {
					ad.thread.stopSound();
					ad.dataLine.close();
					try {
						ad.audioInputStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		soundMap.clear();
	}
}