package proy;

import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

interface tokenGenerator {   //Ya que queriamos cumplir con los principios Solid se paso la delegacion del metodo gentoken 
	default void genToken ( token fig ) { // a una Interface siendo esta token generator, para asi no asignarle mas responsabulidades al
		Random r = new Random();          //chooser
		String[] colors = { "rred", "blue", "yllw", "prpl" };
		String color = colors[ r.nextInt( colors.length ) ]; //Se encarga de acuerdo a la lista de colores y los modelos que ya posee
																//asignarle ese color y crear el objeto en base a los modelos que ya
		fig.setColor(color);                                      //se tienen, asignando el color del token a cada una de sus piezas
		int ranInt = r.nextInt( fig.models.size() );
		String[] mod = fig.getModel( "m" + ranInt );
		for ( String i: mod ) {
			String f = fig.getModelPiece(i);
			piece p = new piece (color,f);
			fig.setSides(p, i);
		}                                                            //Despues para generarlos de forma distinta se quiso rotar al final el token
		for ( int i=0; i<r.nextInt( 6 );  ) { fig.rotate(); }
	} 
} 
public class chooser implements tokenGenerator { //El chooser es la clase que se encarga de tener sus objetos del tipo token
	HashMap<Integer, token> figs;    //con estos colocados dentro de un hashmap para acceder a estos
	HashMap<String, String> colors = new HashMap<>();
	
	public chooser ( HashMap<Integer, token> x ) { //Ya posee los colores con su respectivo codigo ascii
		this.colors.put ( "gray", "\033[33m" );
		this.colors.put ( "rred", "\033[31m" );
		this.colors.put ( "yllw", "\033[33m" );
		this.colors.put ( "blue", "\033[34m" );
		this.colors.put ( "prpl", "\033[35m" );
		this.colors.put ( "reset", "\u001B[0m" );
		this.figs = x;
		for ( token n : x.values() ) { genToken(n); }
	}
	public HashMap<Integer, token> getFigs() { return figs; }
	
	public void menuRot() {
		System.out.println(" > Inserte el numero de la figura a rotar");
		System.out.print(" > ");
		Scanner scan = new Scanner(System.in);
		int y = scan.nextInt();
		rotFig(y);
	}
	public token getFig ( int x ) { return this.figs.get(x); }       //Rota la figura de acuerdo a las direcciones que el token posee
	public void rotFig ( int x ) { this.figs.get(x).rotate(); }      //Genera un nuevo token
	public void genNull ( int x ) { genToken( this.figs.get(x) ); }
	public void showChooses () {                                     //Se encarga de mostrar las opciones dentro del chooser
		String[] hexa = { "upL", "up", "upR", "left", "center", "right" };
		
		for ( token n : this.figs.values() ) {
			System.out.println( colors.get(n.color) );			
			for ( String f : hexa ) {                                //Para este juego se establecieron los triangulos que esten
				if ( f == "left" ) { System.out.println(" "); }        //con la forma de t-up
				if ( n.family.containsKey(f) ) {
					if ( n.family.get(f).form == "t-up" ) System.out.print("▲");
					else System.out.print("▼");
				} else {
					if ( (f=="upR") || (f=="upL") || (f=="center") ) System.out.print("△");
					else System.out.print("▽");
				}
			}
			System.out.println( colors.get("reset") );
		}
		System.out.println(" ");
	}
	
}
