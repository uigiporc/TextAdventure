package adventure.builder;

import java.util.Locale;
import java.util.ResourceBundle;

public class CommandsAliasesTest {
	
	public static void main(String[] args) {
		ResourceBundle rs = ResourceBundle.getBundle("adventure.builder.AliasesTest", Locale.getDefault());
		
		System.out.println("INSERISCO: USA; OTTENGO: " + rs.getObject("USA"));
		System.out.println("INSERISCO: UTILIZZA; OTTENGO: " + rs.getObject("UTILIZZA"));
		System.out.println("INSERISCO: PRENDI; OTTENGO: " + rs.getObject("PRENDI"));
		System.out.println("INSERISCO: RACCOGLI; OTTENGO: " + rs.getObject("RACCOGLI"));
		Command command = Command.valueOf(rs.getString("USA"));
		System.out.println("INSERISCO: USA; COMANDO INTERPRETATO: " + command);
		
		rs = ResourceBundle.getBundle("adventure.builder.AliasesTest", Locale.US);
		System.out.println();
		
		System.out.println("INSERISCO: USE; OTTENGO: " + rs.getObject("USE"));
		System.out.println("INSERISCO: UTILIZE; OTTENGO: " + rs.getObject("UTILIZE"));
		System.out.println("INSERISCO: GET; OTTENGO: " + rs.getObject("GET"));
		System.out.println("INSERISCO: PICK; OTTENGO: " + rs.getObject("PICK"));
		Command commandEN = Command.valueOf(rs.getString("PICK"));
		System.out.println("INSERISCO: PICK; COMANDO INTERPRETATO: " + commandEN);
		
	}
	
}
