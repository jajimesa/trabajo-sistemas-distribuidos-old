package servidor;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.net.Socket;

import modelo.Song;


public class AtenderPeticion extends Thread {

	private Socket socket;
	private ObjectInputStream inputPeticion;
	
	public AtenderPeticion(Socket socket) {
		this.socket = socket;
	}
	
	
	public void run() 
	{
		//TODO: via tcp establece qué hacer, via udp envia el audio. 8820 buen tamaño buffer.

		try {
			// El dataInputStream recibe una petición
			this.inputPeticion = new ObjectInputStream(socket.getInputStream());
			
			while(true) 
			{
				String peticion = inputPeticion.readLine();
				
				if(peticion.startsWith("GET")) 
				{
					atenderGET(peticion);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/* Método que atiende una petición GET de un cliente. Comprueba el tipo de petición GET y actúa:
	 * 1) GET SONG: lee el objeto SONG que manda el cliente después de la petición y lo stremea al cliente.
	 * 2) GET RADIO:
	 */
	private void atenderGET(String peticion) 
	{
		if(peticion.equals("GET SONG")) 
		{
			streamSong();
		} 
		
		//TODO: añadir un GET RADIO
	}
	
	
	private void streamSong() {
		try {
			Song s = (Song) inputPeticion.readObject();
			s = SongBuilder.construirCancion(s);
			SongStreaming songStreaming = new SongStreaming(socket, s);
			songStreaming.init();
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
