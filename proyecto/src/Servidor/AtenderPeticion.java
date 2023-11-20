package Servidor;

import java.net.DatagramPacket;
import java.net.Socket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/*
 * https://stackoverflow.com/questions/28122097/live-audio-stream-java
 * https://stackoverflow.com/questions/1024951/does-my-amd-based-machine-use-little-endian-or-big-endian
 * 
 * 
 */


public class AtenderPeticion extends Thread {

	private Socket socket;
	private byte[] buffer;
    private int port;
    private AudioInputStream ais;
	
	public AtenderPeticion(Socket socket) {
		this.socket = socket;
	}
	
	
	public void run() 
	{
		TargetDataLine line;
        DatagramPacket dgp; 

        AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
        float rate = 44100.0f;
        int channels = 2;
        int sampleSize = 16;
        boolean bigEndian = true;

        AudioFormat format = new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
        
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line matching " + info + " not supported.");
            return;
        }

	
	
	
	
	}
	
	
	
	public void streamSong() {
		
	}
	
}
