package proy;

import java.util.HashMap;
import java.util.ArrayList;

abstract public class token { //Para nosotros la clase mas importante del juego siendo la clase primigenia de donde se basarian el 
	String color;                 //resto, su principal funcion es rotar y conectarse a los demas tokens
	ArrayList<String> sides = new ArrayList<>();
	HashMap<String, String> modelsPieces = new HashMap<>();		
	HashMap<String, String[]> models = new HashMap<>();	
	HashMap<String, piece> family = new HashMap<>();
	
	public token (String x) { this.color = x; }
	public void rotate () {
		ArrayList<Integer> positions = new ArrayList<>();
		ArrayList<piece> pieces = new ArrayList<>();
	
		for ( String i: this.family.keySet() ) {
			pieces.add(this.family.get(i));
			
			int position = this.sides.indexOf(i) + 1;
			if( position == this.sides.size() ) { position = 0; }
			positions.add(position);
		}
		this.family.clear();
		for ( int i=0; i < positions.size(); i++ ) {
			this.family.put( this.sides.get( positions.get(i) ), pieces.get(i) );
		}
		for ( String i: this.family.keySet() ) {
			piece p = this.family.get( i );
			String f = this.modelsPieces.get(i);
			p.setForm( f );
		}
	}
	public void connectPieces () {};	
	public void connectSides (token x) {};   
	public void connectUp (token x, int y) {};
	
	public void clearFamily() { this.family.clear(); }
	public void fullColor ( String x ) { for ( piece n: this.family.values() ){ n.setColor(x); }}
	public void setSides (piece x, String y) { this.family.put(y ,x); }
	public void setColor ( String x ) { this.color = x; } 
	public void replaceSide (String y, piece x) { this.family.replace(y ,x); }
	public String[] getModel(String i) { return this.models.get(i); }  
	public piece getFam ( String x ) { return this.family.get(x); }                     
	public HashMap<String, piece> getFamily () { return this.family; }                     
	public String getColor () { return this.color; }  
	public String getModelPiece(String i) { return this.modelsPieces.get(i); }  
	public HashMap<String, String> getModelsPieces() { return this.modelsPieces; }
	
}