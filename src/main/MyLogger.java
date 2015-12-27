package main;

import entity.Entity;
import entity.GE;

public class MyLogger {
	// Ambito
	public final static int VIEW = 0;
	public final static int INPUT = 1;
	public final static int AI = 2;
	
	// Tipo
	public final static int NO_KEY = 0;
	
	// Importancia
	public final static int ERROR = 0;
	public final static int WARNING = 1;
	public final static int DEBUG = 4;
	
	public static void log(int ambito, int tipo, int importancia, String clase, String tabla, Long id) {
		String s1 = null;
		String s2 = null;
		String s3 = null;
		String s4 = null;
		String s5 = null;
		String s6 = null;
		switch (importancia) {
			case ERROR:
				s1 = "***ERROR*** ";
				break;
			case WARNING:
				s1 = "**WARNING** ";
				break;
			case DEBUG:
				s1 = "DEBUG ";
				break;
		}
		s2 = "en ámbito ";
		switch (ambito) {
			case VIEW:
				s2 += "VISTA. ";
				break;
			case INPUT:
				s2 += "INPUT. ";
				break;
			case AI:
				s2 += "AI. ";
				break;
		}
		switch (tipo) {
			case NO_KEY:
				s3 += "No se ha encontrado la clave en la tabla. ";
				break;
		}
		s4 = "Clase afectada: " + clase;
		s5 = " Tabla afectada: " + tabla;
		s6 = " ID en cuestión: " + id.toString();
		Entity e = GE.getEntity(id);
		if (e != null) {
			s6 += " (" + e.getDescription() + ") ";
		}
		
		if (Main.DEBUG) {
			System.out.println(s1+s2+s3+s4+s5+s6);
		}
	}
}
