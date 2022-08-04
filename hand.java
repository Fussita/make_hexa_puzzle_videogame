package proy;

import java.util.HashMap;

public class hand { //La mano es la que se encarga de ver si se puede colocar los token dentro del mapa
	eye eye;         //de mandarle el mensaje al ojo una vez que se inserto dentro del mapa y el chooser para tener las opciones
	chooser ch;      // a insertar y su labor mas importante insertar tokens en el tablero
	map Map;
	
	hand ( map M, eye y, chooser ch ) { 
		this.Map = M; 
		this.eye = y;
		this.ch = ch;
	}
	
	boolean insertOptions () {
		for ( token n : ch.figs.values() ) {
			for ( token[] m : this.Map.data.values() ) {  //para cada uno de los token dentrol mapa 
				for ( token p : m ) {                     //se agarran cada una de los token y se intenta colocar
					for ( int q=0; q<6; q++ ) {
						if ( Map.insertB(n, p) == true ) return false;
					}
				}
			}
		}
		return true;   // devuelve true cuando no envuentra una forma de insertar en el tablero
	}
	
	void placeAPiece ( int z, int y, int x ) { //se encarga de ver si se puede colocar o no un token dentro del tablero
		HashMap<Integer, token[]> m = Map.data;
		token n = m.get( y )[x]; 	
		
		if ( Map.insertT( ch.figs.get(z) , n) == true) {
			eye.goEye( ch.figs.get(z).family.size() );
			ch.figs.get(z).family.clear();
			ch.genNull(z);
		}
	}
}
