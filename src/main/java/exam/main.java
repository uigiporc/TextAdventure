package exam;


import java.util.Scanner;
import engine.GameProgress;
import engine.Parser;
import engine.ResourceHandler;

public class main {

	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void main(String[] args) {
		System.out.println("Gioco avviato.");
		ResourceHandler.loadResources();
		GameProgress.nextRoom();
		while (true) {
			Scanner scanner = new Scanner(System.in);
			Parser.parseCommand(scanner.nextLine().toUpperCase().trim());
		}
	}
}
