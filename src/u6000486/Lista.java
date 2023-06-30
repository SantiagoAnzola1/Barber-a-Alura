package u6000486;

import java.util.ArrayList;


import javax.swing.DefaultListModel;

public class Lista {
	
	
	private int tamano;
	private Nodo cabeza;

   
	
	
	public Lista() {
		super();
		this.tamano = 0;
		this.cabeza = null;
	}


	

	
	public void insertar(Icancion cancion, DefaultListModel<String> nombres,DefaultListModel cantantes, ArrayList<String> rutas) {
		
		if (tamano==0 && cabeza==null) { 
			cabeza=new Nodo(cancion);
			cabeza.setAnterior(cabeza); 
			tamano++;						
		}else {
			Nodo aux=new Nodo(cancion, cabeza.getAnterior(), cabeza);
			cabeza.getAnterior().setSiguiente(aux);
			cabeza.setAnterior(aux);			
		
			tamano++; 
		}
		nombres.addElement(cancion.getNombe());
		cantantes.addElement(cancion.getCantante());
		rutas.add(cancion.getRuta());
	}
	public void eliminar(int posicion, DefaultListModel<String> nombres,DefaultListModel cantantes, ArrayList<String> rutas) {
		
		if (tamano==1) {
			tamano=0;
			
		}else if(posicion==tamano-1) {
			Nodo dato=cabeza.getAnterior();
			cabeza.setAnterior(cabeza.getAnterior().getAnterior());
			cabeza.getAnterior().setSiguiente(cabeza);
			
			tamano--;
		
			
		}else {
			
			Nodo aux=cabeza;
			for(int i=0; i<posicion;i++) {
			
				aux=aux.getSiguiente();
			}
			Nodo dato=aux;
			aux.getAnterior().setSiguiente(aux.getSiguiente());
			aux.getSiguiente().setAnterior(aux.getAnterior());
			
			System.out.println("Se elimina: "+aux.getCancion().getNombe()+" - ("+aux.getCancion().getCantante()+")");
			tamano--;
			
			
		}
		cantantes.remove(posicion);
		nombres.remove(posicion);
		rutas.remove(posicion);
		
	}
}
