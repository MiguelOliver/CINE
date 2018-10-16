package Enumerados;

public enum Dias {
	LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;
	
	/**
	 * Lista los valores del enumerado y pone el valor "Seleccione dia" el primnero
	 * @return una lista con los enumerados (String)
	 */
	public static String[] lista(){
		String[] l = new String[8];
		Dias[] d = values();
		
		l[0] = "Seleccione dia";
		
		for(int i=0; i< 7; ++i){
			l[i+1] = d[i].toString();
		}
		
		return l;
	}
}
