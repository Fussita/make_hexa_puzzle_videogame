package proy.jokers;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import proy.chooser;
import proy.eye;
import proy.map;
import proy.token;

public class discard extends joker {	
	public discard ( eye x, chooser y, map z ) { super(x, y, z); }

	@Override
	public void validate () {
		int p = 0;
		if( this.ey.getPts() >= 40) { 
			System.out.print(" > Que hexagono quiere descartar ? [ 1-" + this.ch.getFigs().size() + " ]");
			this.ey.setPts( this.ey.getPts()-40 );
			Scanner scan = new Scanner(System.in);
			p = scan.nextInt();
			cEffect(p); 
		}
		else {System.out.println(" > Puntaje insuficiente para el comodin. ");}
	}

	@Override
	public void effect () {
		Random r = new Random();
		int ranInt = r.nextInt( this.ch.getFigs().size() ) + 1;
		
		HashMap<Integer, token> t = this.ch.getFigs();
		t.get(ranInt).clearFamily();
		this.ch.genNull(ranInt);
		System.out.print(" > ' Seguro era algo que no tenia valor... ' ");
	}
	
	public void cEffect( int x ) {
		HashMap<Integer, token> t = this.ch.getFigs();
		t.get(x).clearFamily();
		this.ch.genNull(x);
	}
}