package proy;

import java.util.HashMap;
import java.util.ArrayList;

interface shape {
	default void shapeMap ( token fig ) {
		HashMap<String, String> models = fig.getModelsPieces();
		
		for ( String i: models.keySet() ) {
			fig.setSides( new piece( "gray", models.get(i) ), i);
		}
	}
}

interface insertBool {
	default boolean insertB (token toIn, token whereIn) {
		HashMap<String, piece> sidesTo = toIn.family;
		HashMap<String, piece> sidesWhere = whereIn.family;
		
		boolean val = true;
		for ( String n : sidesTo.keySet() ) {
			if (( sidesWhere.get(n).color == "gray" ) && 
				( sidesWhere.get(n).form == sidesTo.get(n).form )) {
			} else { val = false; break; }
		}
		if (val == true) { return true; }
		return false;
	}
}

interface insertToken {
	default boolean insertT (token toIn, token whereIn) {
		HashMap<String, piece> sidesTo = toIn.family;
		HashMap<String, piece> sidesWhere = whereIn.family;
		
		boolean val = true;
		
		for ( String n : sidesTo.keySet() ) {
			if (( sidesWhere.get(n).color == "gray" ) && 
				( sidesWhere.get(n).form == sidesTo.get(n).form )) {
			} else { val = false; break; }
		}
		if (val == true) {
			for ( String n : sidesTo.keySet() ) {
				sidesWhere.get(n).color = sidesTo.get(n).color;
			}
			return true;
		}
		return false;
	}
}

public class map implements shape, insertToken, insertBool {
	HashMap <Integer, token[]> data;
	map () {}
	void setMap ( HashMap <Integer, token[]> x ) { 
		this.data = x; 		
		for ( token[] n : this.data.values() ) {
			for ( token m : n ) { shapeMap(m); }
		}
		connectData();
		for ( token[] n : this.data.values() ) {
			for ( token m : n ) { m.connectPieces(); }
		}
	}
	void connectData () { // NO GENERICO
		// Horizontal
		for ( token[] n : this.data.values() ) {
			for ( int m=0; m < n.length-1; m++ ) {
				n[m].connectSides( n[m+1] );
			}
		}				
		// Vertical
		int p = 1;
		for ( int i=this.data.size(); i>1; i-- ) {
			if ( i == 1+(this.data.size()/2) ) p = 0;
			setUp ( this.data.get(i), getDownLine ( this.data.get(i-1) ), p );			
		}
	}
	
	ArrayList<piece> getDownLine ( token[] x ) { // NO GENERICO     
		ArrayList<piece> list = new ArrayList<>();
		ArrayList<piece> repeat = new ArrayList<>();
		for ( token m : x ) {
			if ( repeat.contains(m.getFam("left")) == false ) {
				list.add( m.getFam("left") );
				repeat.add( m.getFam("left") );
			}
			if ( repeat.contains(m.getFam("center")) == false ) {
				list.add( m.getFam("center") );
				repeat.add( m.getFam("center") );
			}
			if ( repeat.contains(m.getFam("right")) == false ) {
				list.add( m.getFam("right") ); 
				repeat.add( m.getFam("right") );
			}
		} 
		return list;
	}
	
	public HashMap <Integer, token[]> getData () { return data; }
	void setUp ( token[] x, ArrayList<piece> y, int z ) { // NO GENERICO
		int n = z;
		for ( token m : x ) {
			if ( z == 0 ) {
				if (n == 0) {
					m.setSides( y.get(n), "up" );
					m.setSides( y.get(n+1), "upR" );
					n++; continue;
				} 
				if ( n == y.size() - 2 ) {
					m.setSides( y.get(n), "upL" );
					m.setSides( y.get(n+1), "up" );
					continue;
				}
			}
			m.setSides( y.get(n), "upL" );
			m.setSides( y.get(n+1), "up" );
			m.setSides( y.get(n+2), "upR" );
			n += 2;
		}	
	}
	
	public void showMap () { // NO GENERICO
		HashMap<String, String> colors = new HashMap<>();
		colors.put ( "gray", "\033[30m" );
		colors.put ( "rred", "\033[31m" );
		colors.put ( "yllw", "\033[33m" );
		colors.put ( "blue", "\033[34m" );
		colors.put ( "prpl", "\033[35m" );
		colors.put ( "reset", "\u001B[0m" );		
		
		int z = 0, cond = 1;
		piece p;
		System.out.println("");
		System.out.print("      0   1   2   3    4");
		System.out.println(""); System.out.println("");
		
		while ( cond <= 5 ) {
			token t = this.data.get( cond )[0];
			
			if ( z == 0 ) p = t.family.get("upL");
			else p = t.family.get("left");
		
			if ( (cond == 1) ) System.out.print("1  ");
			if ( (cond == 2) ) System.out.print("2  ");
			if ( (cond == 3) && (z==0) ) System.out.print("3  ");
			if ( (cond == 3) && (z==1) ) System.out.print("4  ");
			if ( (cond == 4) ) System.out.print("5  ");
			if ( (cond == 5) ) System.out.print("6  ");
			
			if ( (cond == 1) || (cond == 5) ) System.out.print("    ");
			if ( (cond == 2) || (cond == 4) ) System.out.print("  ");
			
			while ( cond != 9999 ) {
				if ( p.color != "gray" ) { 
					System.out.print( colors.get( p.color ) );
					if ( p.form == "t-up" ) System.out.print(" ▲");
					else System.out.print(" ▼");
					System.out.print( colors.get( "reset" ) );
				} else {
					if ( p.form == "t-up" ) System.out.print(" △");
					else System.out.print(" ▽");
				}
				
				if ( !( p.family.containsKey("right")) ) break;
				p = p.family.get("right");
			} System.out.println("");
			
			if ( (z==0) && (cond==3) ) { z = 1; continue; }	
			cond++;
		} System.out.println("");
				
	}
}
