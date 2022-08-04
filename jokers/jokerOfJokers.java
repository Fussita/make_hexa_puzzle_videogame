package proy.jokers;

import java.util.ArrayList;
import java.util.Random;
import proy.chooser;
import proy.eye;
import proy.map;
public class jokerOfJokers extends joker {
	ArrayList<joker> jokers;
	
	public jokerOfJokers ( eye x, chooser y, map z, ArrayList<joker> p ) { 
		super(x, y, z); 
		this.jokers = p;
	}

	@Override
	public void validate () {
		if( this.ey.getPts() >= 70 ) { 
			this.ey.setPts( this.ey.getPts() - 70 );
			this.effect(); 
		}
		else { System.out.println(" > Puntaje insuficiente para el comodin. "); }	
	}

	@Override
	public void effect () {
		Random r = new Random();
		int ranInt = r.nextInt( this.jokers.size() ) + 1;
		if ( ranInt == 4 ) ranInt = 3;
		this.jokers.get(ranInt).effect();
	}
}
