package AppDataProgramFilms;


public class Utilidades {
	
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	public static Character comillas = '"';
	
	/*
	 * funcion auxiliar necesaria para castear un string a un GregorianCalendar
	 * @param date - String
	 * @return GregorianCalendar- con su aï¿½o, mes y dia
	 *
	public static GregorianCalendar dateToCalendar (String date) {
		String[] aux1 = date.split(Utilidades.comillas.toString());
		String[] aux = aux1[0].split("-");
		int anyo = Integer.parseInt(aux[0]);
		int mes = Integer.parseInt(aux[1]);
		int dia = Integer.parseInt(aux[2]);
		
		return new GregorianCalendar(anyo, mes, dia);
	}
	
	/**
	 * funcion necesaria para convertir un String con la fecha y la hora al tipo GregorianCalendar. 
	 * Utiliza el parseInt de java
	 * @param dateTime-String
	 * @return GregorianCalendar
	 *
	public static GregorianCalendar timeToCalendar (String dateTime) {
		String[] s= dateTime.split(" ");
		String[] date = s[0].split("-");
		String[] time = s[1].split(":");
		
		int anyo = Integer.parseInt(date[0]);
		int mes = Integer.parseInt(date[1]);
		int dia = Integer.parseInt(date[2]);
		int hora = Integer.parseInt(time[0]);
		int minutos = Integer.parseInt(time[1]);
		int segundos = Integer.parseInt(time[2].replace(".0",""));
		
		return new GregorianCalendar(anyo, mes, dia, hora, minutos, segundos);
		
	}
	
	/**
	 * funcion auxiliar que convierte un GregorianCalendar a un String con la fecha y la hora almacenada.
	 * formato correspondiente: aaaa/mm/dd hh:mm:ss
	 * @param calendar
	 * @return String
	 *
	public static String calendarToDateTime (GregorianCalendar calendar) {
		String ret;
		String[] aux = calendar.getTime().toString().split(" ");
		String mes = " ";
		
		switch (aux[1].toUpperCase()) {
		case "JAN": mes = "01"; break;
		case "FEB": mes = "02"; break;
		case "MAR": mes = "03"; break;
		case "APR": mes = "04"; break;
		case "MAY": mes = "05"; break;
		case "JUN": mes = "06"; break;
		case "JUL": mes = "07"; break;
		case "AUG": mes = "08"; break;
		case "SEP": mes = "09"; break;
		case "OCT": mes = "10"; break;
		case "NOV": mes = "11"; break;
		case "DEC": mes = "12"; break;
		default: mes = " "; break;
		}
		ret = aux[5] + "-" + mes + "-"  + aux[2] + " " + aux[3];
		
		return ret;
	}
	
	/**
	 * funcion auxiliar que convierte un GregorianCalendar a un String con la fecha almacenada.
	 * formato correspondiente: aaaa/mm/dd
	 * @param calendar
	 * @return String
	 *
	public static String calendarToDate (GregorianCalendar calendar) {
		String ret;
		String[] aux = calendar.getTime().toString().split(" ");
		String mes = " ";
		
		switch (aux[1].toUpperCase()) {
		case "JAN": mes = "01"; break;
		case "FEB": mes = "02"; break;
		case "MAR": mes = "03"; break;
		case "APR": mes = "04"; break;
		case "MAY": mes = "05"; break;
		case "JUN": mes = "06"; break;
		case "JUL": mes = "07"; break;
		case "AUG": mes = "08"; break;
		case "SEP": mes = "09"; break;
		case "OCT": mes = "10"; break;
		case "NOV": mes = "11"; break;
		case "DEC": mes = "12"; break;
		default: mes = " "; break;
		}
		ret = aux[5] + "-" + mes + "-"  + aux[2];
		
		return ret;
	}
	
	/**
	 * Funcion que devuelve is una cadena esta compuesta por numeros (enteros o decimales, el '.' esta admitido)
	 * mientras un caracter sea numero o '.' o '\n' devolvera true. eoc devolvera false
	 */
	public static boolean isNumber(String myString){
		
		final Character dot = new Character('.');
		final Character endLine = new Character('\n');
		boolean b = true;
		int i = 1;
		
		String[] cad = myString.split("");
		
		while(b && i<cad.length){
			Character c = new Character (cad[i].charAt(0));
			if(!Character.isDigit(c) && !c.equals(dot) && !c.equals(endLine)){
				b = false;
			}
			i++;
		}
		return b;
	}
	
	/**
	 * Funcion que devuelve si una cadena esta compuesta por numeros (enteros o decimales, el '.' esta admitido)<br>
	 * mientras un caracter sea numero o '.' o '\n' devolvera true. eoc devolvera false <br>
	 * También comprueba si el numero tiene exactamente 8 numeros (DNI)
	 */
	public static boolean isNumberNIF(String myString){
		
		final Character dot = new Character('.');
		final Character endLine = new Character('\n');
		boolean b = true;
		int i = 1;
		
		String[] cad = myString.split("");
		
		if (cad.length != 9)
			b = false;
		
		while(b && i<cad.length){
			Character c = new Character (cad[i].charAt(0));
			if(!Character.isDigit(c) && !c.equals(dot) && !c.equals(endLine)){
				b = false;
			}
			i++;
		}
		return b;
	}
	
	public static boolean isFecha(String f){
		
		String[] fecha = f.split("-");
		if(fecha.length != 3){
			return false;
		}
		
		String[] anyo = fecha[0].split("");
		if(anyo.length != 5){
			return false;
		}
		for(int i=0; i<5; ++i){
			if(!isNumber(anyo[i])){
				return false;
			}
		}
		
		String[] mes = fecha[1].split("");
		if(mes.length != 3){
			return false;
		}
		for(int i=0; i<3; ++i){
			if(!isNumber(mes[i])){
				return false;
			}
		}
		
		String[] dia = fecha[2].split("");
		if(dia.length != 3){
			return false;
		}
		for(int i=0; i<3; ++i){
			if(!isNumber(dia[i])){
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isHora(String h){
		
		String[] hora = h.split(":");
		if(hora.length != 3){
			return false;
		}
		
		String[] hor = hora[0].split("");
		if(hor.length != 3){
			return false;
		}
		for(int i=0; i<3; ++i){
			if(!isNumber(hor[i])){
				return false;
			}
		}
		
		String[] min = hora[1].split("");
		if(min.length != 3){
			return false;
		}
		for(int i=0; i<3; ++i){
			if(!isNumber(min[i])){
				return false;
			}
		}
		
		String[] seg = hora[2].split("");
		if(seg.length != 3){
			return false;
		}
		for(int i=0; i<3; ++i){
			if(!isNumber(seg[i])){
				return false;
			}
		}
		
		return true;
	}

	public static boolean isCalendar(String string) {
		String[] aux = string.split(" ");
		return (aux.length==2 && isHora(aux[1]) && isFecha(aux[0]));
	}
}
