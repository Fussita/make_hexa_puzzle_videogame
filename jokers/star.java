package proy.jokers;

import proy.chooser;
import proy.eye;
import proy.map;

public class star extends joker {
	public star ( eye x, chooser y, map z ) { super(x, y, z); }

	@Override
	public void validate () {}

	@Override
	public void effect () { 
		this.ey.setPts( ey.getPts() + 100 ); 
		System.out.println( " > ' Una estrella alumbra tu camino... ' " );
	}
}
