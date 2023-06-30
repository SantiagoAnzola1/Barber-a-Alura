package u6000486;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class Icancion {
	
	String cantante;
	String  nombe;
	
	File archivo;
	String ruta;
	
	Tag etiqueta;
	AudioFile leer;
	
//	public Icancion( File archivo) {
//		try {
//			leer=AudioFileIO.read(archivo);
//		} catch (CannotReadException | IOException | TagException | ReadOnlyFileException
//				| InvalidAudioFrameException e) {
//			e.printStackTrace();
//		}
//
//		etiqueta=leer.getTag();
//		
//		this.cantante = etiqueta.getFirst(FieldKey.ARTIST);
//		this.nombe = etiqueta.getFirst(FieldKey.TITLE);
//		this.archivo = archivo;
//		this.ruta = archivo.toString();
//		
//	}
	


	    public Icancion(File archivo) {
	        try {
	            leer = AudioFileIO.read(archivo);
	            etiqueta = leer.getTag();
	            
	            this.cantante = etiqueta.getFirst(FieldKey.ARTIST);
	            this.nombe = etiqueta.getFirst(FieldKey.TITLE);
	            
	            // If the title is empty, use the file name as a fallback
	            if (this.nombe=="") {
	                this.nombe = archivo.getName();
	                System.out.println(this.nombe);
	                System.out.println("Warning: No title found. Using file name instead.");
	            }

	            this.archivo = archivo;
	            this.ruta = archivo.toString();

	        } catch (CannotReadException | IOException | TagException | ReadOnlyFileException
	               e) {
	            e.printStackTrace();
	        } catch (InvalidAudioFrameException e) {
	            // Handle the case when no audio header is found
	        	System.out.println(this.nombe);
	        	this.nombe = archivo.getName();
	        	System.out.println("Warning: No title found. Using file name instead.");
	            System.out.println("Warning: Invalid audio file. Skipping file: " + archivo.getName());
	        }
	    }
	


	public String getCantante() {
		return cantante;
	}

	public void setCantante(String cantante) {
		this.cantante = cantante;
	}

	public String getNombe() {
		return nombe;
	}

	public void setNombe(String nombe) {
		this.nombe = nombe;
	}

	public File getArchivo() {
		return archivo;
	}

	public void setArchivo(File archivo) {
		this.archivo = archivo;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Tag getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(Tag etiqueta) {
		this.etiqueta = etiqueta;
	}

	public AudioFile getLeer() {
		return leer;
	}

	public void setLeer(AudioFile leer) {
		this.leer = leer;
	}



	
	

	
}
