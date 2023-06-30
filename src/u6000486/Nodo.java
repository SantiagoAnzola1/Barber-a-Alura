package u6000486;

public class Nodo {
	//Atributos
	private Icancion cancion;//Se encapsula Para obtener el nombre de los libros 
	private Nodo siguiente;// Se encapsula Para conectar los nodos con su siguiente 
	private Nodo anterior;//Se encapsula Para conectar los nodos con su anterior 
	
	//Constructores
	public Nodo(Icancion cancion) {
		
		this.cancion = cancion;
		this.siguiente = this;
		this.anterior = this;
	}
	
	


	public Nodo(Icancion cancion, Nodo anterior, Nodo siguiente) {//Constructor para obtener dato y definir apuntadores de cada nodo
		
		this.cancion = cancion;
		this.siguiente = siguiente;
		this.anterior = anterior;
	}




	public Icancion getCancion() {
		return cancion;
	}




	public void setCancion(Icancion cancion) {
		this.cancion = cancion;
	}




	public Nodo getSiguiente() {
		return siguiente;
	}




	public void setSiguiente(Nodo siguiente) {
		this.siguiente = siguiente;
	}




	public Nodo getAnterior() {
		return anterior;
	}




	public void setAnterior(Nodo anterior) {
		this.anterior = anterior;
	}




	


	
	
}
