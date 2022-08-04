package proy;

import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import proy.difficulty.difficulty;
import proy.difficulty.easy;
import proy.difficulty.normal;
import proy.difficulty.hard;
import proy.jokers.clown;
import proy.jokers.discard;
import proy.jokers.hammer;
import proy.jokers.joker;
import proy.jokers.jokerOfJokers;
import proy.jokers.star;

public class Main {		
	private static Scanner scanner;

	public static void main ( String[] args ) {
		HashMap<Integer, token> opts = new HashMap<>();
		
		int cn = 0, op, x, y, sizeChooser = 3;
		for ( int i=1; i <= sizeChooser; i++ ) { opts.put(i, new hexagon("gray")); }
		
		chooser ch = new chooser ( opts );
		map Map = new map ();
		eye eye = new eye ( Map, iniDiff() );
		eye.setPts(1000);
		hand hand = new hand ( Map, eye, ch );
		ArrayList<joker> jokers = iniJokers( eye, ch, Map );
		
		iniMapStandard ( Map );
		while ( hand.insertOptions() == false ) {		
			Map.showMap();
			System.out.println( " > Puntaje : [ " + eye.getPts() + " ]");
			ch.showChooses();			
			
			if ( jokersMenu(jokers, ch) == true ) { continue; } 
			else {}
				
			System.out.println(" > Inserte el Token a usar " ); op = verifyInterval( 1, ch.figs.size() );
			
			if ( op <= ch.figs.size() ) { 
				System.out.println(" > Inserte coordenada y "); y = verifyInterval ( 1, 5 );
				
				if ( (y == 1) || (y == 5) ) cn = 2;
				if ( (y == 2) || (y == 4) ) cn = 3;
				if ( y == 3 ) cn = 4;
				
				System.out.println(" > Inserte coordenada x "); x = verifyInterval ( 1, cn+1 );
				hand.placeAPiece( op, y, x-1 );
			} else { ch.figs.get( op - ch.figs.size() ).rotate(); } // F = Tamano - Opcion
		}
	}
	
	public static boolean jokersMenu ( ArrayList<joker> j, chooser ch ) {
		int p = 0;
		System.out.println( " > Herramientas : ");
		System.out.println( " 0. Continuar ");
		System.out.println( " 1. Martillo -> $50 ");
		System.out.println( " 2. Descartar -> $40 ");
		System.out.println( " 3. Comodin -> $70 ");
		System.out.println( " 4. Rotar Ficha ");
		
		
		scanner = new Scanner(System.in);
		p = scanner.nextInt();
		
		if ( p == 0 ) { return false; }
		else {
			if ( p == 1 ) j.get(0).validate(); // Hammer
			if ( p == 2 ) j.get(1).validate(); // Discard
			if ( p == 3 ) j.get(2).validate(); // JOJ
			if ( p == 4 ) ch.menuRot();
			return true;
		}	
	}
	
	public static difficulty iniDiff () {
		int op = 0;
		System.out.println(" "); System.out.println(" ");
		System.out.println(" > La Dificultad hace variar el puntaje que se obtiene tras la formacion de un hexagono.");
		System.out.println(" > A mayor dificultad, menor puntaje obtenido! ");
		System.out.println(" > Ingrese el numero correspondiente a la dificultad deseada : ");
		System.out.println(" 1. Facil ");
		System.out.println(" 2. Normal ");
		System.out.println(" 3. Dificil ");
		op = verifyInterval( 1, 3 );
		difficulty[] diff = { new easy(), new normal(), new hard() };
		return diff[op-1];
	}
	
	public static ArrayList<joker> iniJokers ( eye x, chooser y, map z ) {
		ArrayList<joker> jjs = new ArrayList<>();
		ArrayList<joker> list = new ArrayList<>();
		jjs.add( new hammer (x, y, z ) );
		jjs.add( new discard (x, y, z) );
		jjs.add( new star (x, y, z) );
		jjs.add( new clown (x, y, z) );
		joker JOJ = new jokerOfJokers (x, y, z, jjs );
		
		list.add( new hammer (x, y, z ) );
		list.add( new discard (x, y, z) );
		list.add(JOJ);
		
		return list;
	}
	
	public static int verifyInterval ( int x, int y ) {
		int z = 0;
		while ( z == 0 ) {
			z = controlScanInt ();
			if ( (z>=x) && (z<=y) ) { break; }
			else { 
				System.out.println(" > Error. El numero insertado es invalido");
				z = 0;
			}
		}
		return z;
	}	
	
	public static boolean verifyInt ( String x ) {
		char[] charList = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		boolean cond;
		
		for ( int n=0; n < x.length(); n++ ) {
			cond = false;
			for ( char m: charList ) {
				if ( x.charAt(n) == m ) { cond = true; break; }
			}
			if ( cond == false ) return false;
		}
		return true;
	}
	
	public static int controlScanInt ( ) {
		String p;
		int z = 0;
		while ( z == 0 ) {
			System.out.print(" > ");
			p = new Scanner(System.in).nextLine();
			if ( verifyInt (p) == true ) { z = Integer.parseInt(p); }
			else { System.out.println(" > Error. Debe insertar un entero. Intente otra vez."); }
		}
		return z;
	}
	
	public static void iniMapStandard ( map Map ) {
		// Map
		HashMap<Integer, token[]> box = new HashMap<>();
		HashMap <Integer, token[]> size = new HashMap<>();
		size.put( 1, new hexagon[3] );
		size.put( 2, new hexagon[4] );
		size.put( 3, new hexagon[5] );
		size.put( 4, new hexagon[4] );
		size.put( 5, new hexagon[3] );
		
		for ( int i=1; i <= size.size(); i++ ) {
			for ( int n=0; n<size.get(i).length; n++ ) {
				size.get(i)[n] = new hexagon("gray");
			}
			box.put(i, size.get(i));
		}
		Map.setMap(box);
	}
}