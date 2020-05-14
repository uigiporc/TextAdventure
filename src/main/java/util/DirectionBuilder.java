package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import util.Direction;

public class DirectionBuilder {
	
	public static void main(String[] args) {
		Map<String[], Direction> en = new HashMap<String[], Direction>();
		Map<String[], Direction> it = new HashMap<String[], Direction>();
		File enFile = new File("src/main/java/util/DirectionAlias_en.properties"); 
		File itFile = new File("src/main/java/util/DirectionAlias_it.properties"); 
		String[] itEAST = {"Destra", "Est"};
		String[] itWEST = {"Ovest", "Sinistra"};
		String[] itNORTH = {"Nord", "Avanti"};
		String[] itSOUTH = {"Sud", "Indietro"};
		String[] enEAST = {"Right", "East"};
		String[] enWEST = {"West", "Left"};
		String[] enNORTH = {"North", "Forward"};
		String[] enSOUTH = {"South", "Back"};
		it.put(itEAST, Direction.EAST);
		it.put(itWEST, Direction.WEST);
		it.put(itNORTH, Direction.NORTH);
		it.put(itSOUTH, Direction.SOUTH);
		en.put(enEAST, Direction.EAST);
		en.put(enWEST, Direction.WEST);
		en.put(enNORTH, Direction.NORTH);
		en.put(enSOUTH, Direction.SOUTH);
		try {
			ObjectOutputStream oosEN = new ObjectOutputStream(new FileOutputStream(enFile));
			ObjectOutputStream oosIT = new ObjectOutputStream(new FileOutputStream(itFile));
			oosEN.writeObject(en);
			oosIT.writeObject(it);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
