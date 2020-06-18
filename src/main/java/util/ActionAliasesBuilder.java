package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class ActionAliasesBuilder {

	public static void main(String[] args) {
		ActionAliasesBuilder.build();
	}


	public static void build(){
		ObjectOutputStream en = null;
		ObjectOutputStream it = null;
		Map<String[], Command> aliasesEN;
		Map<String[], Command> aliasesIT;
		File itFile = null;
		File enFile = null;

		try {
			itFile = new File("src/main/resources/bin/CommandAliases_it.dat");
			itFile.createNewFile();
			enFile = new File("src/main/resources/bin/CommandAliases_en.dat");
			enFile.createNewFile();
			it = new ObjectOutputStream(new FileOutputStream(itFile));
			en = new ObjectOutputStream(new FileOutputStream(enFile));

			aliasesIT = new HashMap<String[], Command>();
			aliasesEN = new HashMap<String[], Command>();
			String[] stringUSE_IT = {"usa", "utilizza"};
			aliasesIT.put(stringUSE_IT,Command.USE);
			String[] stringUSE_EN = {"use", "utilize"};
			aliasesEN.put(stringUSE_EN, Command.USE);
			String[] stringGET_IT = {"prendi", "raccogli"};
			aliasesIT.put(stringGET_IT, Command.GET);
			String[] stringGET_EN = {"grab", "obtain", "get", "pick"};
			aliasesEN.put(stringGET_EN, Command.GET);
			String[] THROW_IT = {"lancia", "getta", "lascia"};
			aliasesIT.put(THROW_IT, Command.THROW);
			String[] THROW_EN = {"throw", "toss", "drop"};
			aliasesEN.put(THROW_EN, Command.THROW);
			String[] JUMP_IT = {"salta"};
			aliasesIT.put(JUMP_IT, Command.JUMP);
			String[] JUMP_EN = {"jump"};
			aliasesEN.put(JUMP_EN, Command.JUMP);
			String[] EAT_IT = {"mangia"};
			aliasesIT.put(EAT_IT, Command.EAT);
			String[] EAT_EN = {"eat"};
			aliasesEN.put(EAT_EN, Command.EAT);
			String[] PUNCH_IT = {"colpisci", "distruggi"};
			aliasesIT.put(PUNCH_IT, Command.PUNCH);
			String[] PUNCH_EN = {"punch", "hit", "destroy"};
			aliasesEN.put(PUNCH_EN, Command.PUNCH);
			String[] GO_IT = {"vai"};
			aliasesIT.put(GO_IT, Command.GO);
			String[] GO_EN = {"go"};
			aliasesEN.put(GO_EN, Command.GO);
			String[] BAG_IT = {"zaino", "borsa", "inventario"};
			aliasesIT.put(BAG_IT, Command.BAG);
			String[] BAG_EN = {"bag", "inventory"};
			aliasesEN.put(BAG_EN, Command.BAG);
			String[] OBSERVE_IT = {"osserva", "analizza", "guarda", "esamina"};
			aliasesIT.put(OBSERVE_IT, Command.OBSERVE);
			String[] OBSERVE_EN = {"observe", "analyze", "look", "examine"};
			aliasesEN.put(OBSERVE_EN, Command.OBSERVE);
			String[] OPEN_IT = {"apri"};
			aliasesIT.put(OPEN_IT, Command.OPEN);
			String[] OPEN_EN = {"open"};
			aliasesEN.put(OPEN_EN, Command.OPEN);
			String[] CLOSE_IT = {"chiudi"};
			aliasesIT.put(CLOSE_IT, Command.CLOSE);
			String[] CLOSE_EN = {"close"};
			aliasesEN.put(CLOSE_EN, Command.CLOSE);

			it.writeObject(aliasesIT);
			en.writeObject(aliasesEN);
		}catch(IOException ex) {
			System.out.println("diocane");
		} finally {
			try {
				Objects.requireNonNull(it).close();
				Objects.requireNonNull(en).close();
			} catch (IOException e) {

			}
		}
	}

}
