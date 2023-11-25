package cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SongPlayer {

	private Socket socket;
	private DatagramSocket udpSocket;
	
	public SongPlayer(Socket socket) {
		this.socket = socket;
	}
	
	public void init() 
	{
		// El DatagramSocket será utilizado para enviar la información
		
		try {
			// Su puerto local será el siguiente al del socket tcp.
			this.udpSocket = new DatagramSocket(socket.getLocalPort() + 1);
			
			// Obtengo una salida de audio que soporte el formato .wav
			AudioFormat formatoWAV = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4 , 44100, false);
			SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getSourceDataLine(formatoWAV);
			sourceDataLine.open();
			
			Thread threadPlayer = new Thread() {

				@Override public void run() 
				{
					sourceDataLine.start(); // Activo la lectura (grabación) de esta linea
					
					byte [] buffer = new byte[8820];
					try {
						while(true) 
						{
							DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
							udpSocket.receive(packet);
							byte [] b = packet.getData();
							sourceDataLine.write(b, 0, packet.getLength()); // Reproduzco lo recibido
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			};
			
			threadPlayer.start();
			
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	
	}
}
