package proy;

import java.util.HashMap;

import proy.difficulty.difficulty;

import java.util.ArrayList;

interface kboom {                 //Es la interface que se encarga de explotar los token
	public default boolean blowUp ( token t ) {
		String cl = "";
		for ( piece n : t.getFamily().values() ) {  //Busca el color de una de sus piezas
			cl = n.color;
			break;
		}
		
		if (cl == "gray") return false; //Si hay alguna pieza que sea "gris" entonces no se explota
		for ( piece n: t.family.values() ) {     
			if ( cl != n.color ) { return false; } //despues compara si para cada uno de las fichas que posee el hexagono es
		}                                          // del mismo color, sino es del mismo color devuelve false, del resto true
		return true;
	}
}

public class eye implements kboom {
	int pts;
	map Map;
	difficulty diff;
	
	public eye ( map M, difficulty n ) {   //El ojo u observador es el que se encarga de ir actualizando los puntajes 
		this.diff = n;                     //conforme a como se va desarrollando el juego, tiene puntaje y un mapa que tiene que
		this.pts = 0;                         //observar
		this.Map = M;
	}
	
	public void goEye ( int x ) { 
		this.pts += x;
		blowUpCenters(); 
	}
	
	public int getPts () { return pts; }
	public void setPts ( int x ) { this.pts = x; }
	
	public void blowUpCenters () {
		int cont = 0;
		HashMap<Integer, token[]> x = this.Map.data;     //va revisando dentro del mapa 
		ArrayList<token> blow = new ArrayList<>();
		for ( token[] n : x.values() ) {
			for ( token m : n ) { 
				if ( blowUp(m) == true ) {
					blow.add(m);                     //los va agregando los centros a una lista de de token
					cont++; 
				}
			}
		}
		for ( token n : blow ) { n.fullColor("gray"); } //despues por cada uno de los centros de la lista almacenada se eliminan y
		if ( cont >= 1 ) {                                //se cambian por gris dentro del tablero
			System.out.println(" > Figura de " + cont + " piezas! ");
			System.out.println("");
		}
		this.pts += cont*6;
	}
}
