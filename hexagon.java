package proy;

public class hexagon extends token{  ///Es la clase hexagono que posee sus direcciones, modelos para poder usarlos chooser
	hexagon (String x) { 
		super(x); 
		
		String[] sides = {"left", "upL", "up", "upR", "right", "center"};	
		for ( String i: sides ) { this.sides.add(i); }
		
		this.modelsPieces.put("left", "t-dn");
		this.modelsPieces.put("upL", "t-up");
		this.modelsPieces.put("up", "t-dn");
		this.modelsPieces.put("upR", "t-up");
		this.modelsPieces.put("right", "t-dn");
		this.modelsPieces.put("center", "t-up");
		
		String[] m0 = {"left", "up", "right"};
		this.models.put("m0", m0);
		String[] m1 = {"left", "right"};
		this.models.put("m1", m1);
		String[] m2 = {"center"};
		this.models.put("m2", m2);
		String[] m3 = {"upL", "upR"};
		this.models.put("m3", m3);
		String[] m4 = {"left", "up", "right", "upL", "center"};
		this.models.put("m4", m4);
		String[] m5 = {"left", "up", "center", "upL"};
		this.models.put("m5", m5);
		String[] m6 = {"left", "center"};
		this.models.put("m6", m6); 
	}
	@Override
	public void connectSides ( token x ) {        //Conectar sus lados entre si para generar el tablero
		x.setSides( this.family.get("right"), "left" );
		x.setSides( this.family.get("upR"), "upL" );
	}
	@Override
	public void connectPieces () {                //Conectar sus piezas para igualmente generar el tablero
		piece c = this.family.get("center");
		piece l = this.family.get("left");
		piece r = this.family.get("right");
		piece uL = this.family.get("upL");
		piece uR = this.family.get("upR");
		piece u = this.family.get("up");
		
		l.setFam("right", c);
		l.setFam("up", uL);
		l.setFam("upR", u);
		
		r.setFam("left", c);
		r.setFam("up", uR);
		r.setFam("upL", u);
		
		u.setFam("right", uR);
		u.setFam("left", uL);
		
		c.setFam("right", r);
		c.setFam("left", l);
		c.setFam("up", u);
		c.setFam("upL", uL);
		c.setFam("upR", uR);
		
		uL.setFam("right", u);
		
		uR.setFam("left", u);
	}
}