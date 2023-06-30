package u6000486;

import java.io.File;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

public class Funciones {
	
	private  BasicPlayer reproductor;

	public Funciones() {
		reproductor= new BasicPlayer();
	}
	
	public void abrirArchivo(String ruta) {
		try {
			reproductor.open(new File(ruta));
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	public void reproCancion(String ruta) {
		try {
			reproductor.open(new File(ruta));
			
			reproductor.play();
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	public void iniciar() throws Exception  {
		reproductor.play();
	}
	
	public void pausar() throws Exception{
		reproductor.pause();
	}
	
	public void parar() throws Exception{
		reproductor.stop();
	}
	public void seguir() throws Exception{
		reproductor.resume();
	}

	
	
}
