package util;

public enum Commands {
    PRENDI,
    LANCIA,
    USA,
    SALTA,
    LASCIA,
    MANGIA,
    COLPISCI,
    VAI,
    ZAINO,
    ANALIZZA,
    RACCOGLI,
    CERCA;
	
	public static boolean equals(String s) throws IllegalActionException {
		for(Commands temp : Commands.values()) {
			if(s.equals(temp.toString())) {
				return true;
			}
		}
		throw new IllegalActionException();
	}
	
	public static Commands toCommand(String s) throws IllegalActionException {
		for(Commands temp : Commands.values()) {
			if(s.equals(temp.toString())) {
				return temp;
			}
		}
		throw new IllegalActionException();
	}
}
