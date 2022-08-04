package proy.jokers;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import proy.chooser;
import proy.eye;
import proy.map;
import proy.token;
public class hammer extends joker {
	public hammer ( eye x, chooser y, map z ) { super(x, y, z); }

	@Override
	public void validate () {
		if( this.ey.getPts() >= 50) { 
			this.ey.setPts( this.ey.getPts()-50 );
			
			int x = 1, y = 1;
			System.out.println(" > En que fila esta el hexagono a partir ? [ 1-" + this.mp.getData().size() + " ]");
			System.out.print(" > ");
			Scanner scan = new Scanner(System.in);
			y = scan.nextInt();
			
			
			System.out.println(" > Ingrese la columna del hexagono a partir ? [ 1-" + this.mp.getData().get(y).length + " ]");
			System.out.println(" > ");
			x = scan.nextInt();
			
			cEffect(y, x-1); 
		}
		else {System.out.println(" > Puntaje insuficiente para el comodin. ");}
	}

	@Override
	public void effect () {
		Random r = new Random();
		HashMap<Integer, token[]> dat = this.mp.getData();
		int ranInt = r.nextInt(dat.size()) + 1;
		int ranInt2=r.nextInt(dat.get(ranInt).length);
		cEffect(ranInt, ranInt2);
		System.out.println( " > ' Parece que algo se rompio... ' " );
	}
	public void cEffect (int y, int x) {
		HashMap<Integer, token[]> dat = this.mp.getData();
		token n = dat.get( y )[x];
		n.getFam("left").setColor("gray");
		n.getFam("center").setColor("gray");
		n.getFam("right").setColor("gray");
		n.getFam("up").setColor("gray");
		n.getFam("upL").setColor("gray");
		n.getFam("upR").setColor("gray");
	}
}