package Servidor;

import java.io.ByteArrayOutputStream;
import java.net.DatagramPacket;
import java.net.Socket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

import Modelo.Song;


public class AtenderPeticion extends Thread {

	private Socket socket;
	private ByteArrayOutputStream byteArrayOut;
	
	public AtenderPeticion(Socket socket) {
		this.socket = socket;
	}
	
	
	public void run() 
	{
		//TODO: via tcp establece qué hacer, via udp envia el audio. 8820 buen tamaño buffer.

	
	
	
	
	}
	
	
	private void streamSong(Song s) {
		
		// 1º Abro una TargetDataLine que lea los bytes de la canción y los envíe por el Socket al cliente.
		
	}
}
