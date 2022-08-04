package proy.jokers;

import proy.chooser;
import proy.eye;
import proy.map;

public class clown extends joker {
	public clown ( eye x, chooser y, map z ) { super(x, y, z); }

	@Override
	public void validate () {}

	@Override
	public void effect () { 
		this.ey.setPts( ey.getPts() - 200 ); 
		System.out.println( " > ' Cuidado te tropiezas... ' " );
	}
}
