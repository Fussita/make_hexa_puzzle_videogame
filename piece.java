package proy;

import java.util.HashMap;

public class piece { //Se podria decir que es de las clases mas importantes de este programa y se encarga de almacenar el color 
	String color;         //de las piezas de los tokens y determinar la forma de estas
	String form;
	
	HashMap<String, piece> family = new HashMap<>();
	
	// [ "l", "d", "c", "up", "down", "upL", "upR", "downL", "downR" ];
	
	piece (String x, String y) { 
		this.color = x;
		this.form = y;
	}
	public void setForm (String a) { this.form = a; }
	public void setColor (String x) { this.color = x; }
	public void setFam ( String x, piece y ) { this.family.put(x, y); }
}
