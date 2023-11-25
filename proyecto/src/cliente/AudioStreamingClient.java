package cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import modelo.Song;

public class AudioStreamingClient {
	
	private Socket socket;
	private Scanner teclado;
	private ObjectOutputStream outputPeticion;
	
	public void init() 
	{
		teclado = new Scanner(System.in);
		
		try
		{
			socket = new Socket("localhost", 6666);
			outputPeticion = new ObjectOutputStream(socket.getOutputStream());
			
			// Muestro el menú con las opciones al cliente.
			while(true) 
			{
				System.out.println("Cliente> Seleccione una opción:");
				System.out.println("\t 1 - Lista de canciones.");
				System.out.println("\t 2 - Solicitar canción.");
				System.out.println("\t 3 - Desconectarse.");
				
				while(true) {
					int opcion = 0;
					while(true) 
					{
						if(teclado.hasNextInt()) 
						{
							opcion = teclado.nextInt();
							if(opcion==1||opcion==2||opcion==3) {
								break;
							} else {
								System.out.println("Cliente> Introduce un número correcto, por favor:");
							}
						}
					}
					switch(opcion) {
						case 1:
							solicitarCanciones();
						case 2:
							reproducirCancion();
						case 3:
							// Desconexión
							break;
					}
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputPeticion.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void solicitarCanciones() 
	{
		
	}
	
	public void reproducirCancion() 
	{
		try {
			// Hago la petición de streaming de la canción
			outputPeticion.writeBytes("GET SONG\r\n");
			outputPeticion.writeObject(new Song("Fly Me To The Moon (2008 Remastered)"));
			
			// Reproduzco la canción
			SongPlayer songPlayer = new SongPlayer(socket);
			songPlayer.init();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
