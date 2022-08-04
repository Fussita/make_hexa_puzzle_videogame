package proy.jokers;

import proy.chooser;
import proy.eye;
import proy.map;

abstract public class joker {
	eye ey;
	chooser ch;
	map mp;
	
	public joker ( eye x, chooser y, map z ) {
		this.ey = x;
		this.ch = y;
		this.mp = z;
	}
	public void validate () {};
	public void effect () {};
	public void cEffect () {};
}
