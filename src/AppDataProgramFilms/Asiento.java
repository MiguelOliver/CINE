package AppDataProgramFilms;

public class Asiento {

	//ATRIBUTOS
	
	private int fila;
	private int columna;

	//CONSTRUCTORES
	
	public Asiento(){
		this.setColumna(0);
		this.setFila(0);
	}
	
	public Asiento(int fila, int columna){
		this.setFila(fila);
		this.setColumna(columna);
	}
	//GETTER Y SETTERS
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	
	public String toString() {
		  return "Fila: " + this.getFila() + Utilidades.LINE_SEPARATOR +
		    "Columna: " + this.getColumna() + Utilidades.LINE_SEPARATOR;
	 }
}
