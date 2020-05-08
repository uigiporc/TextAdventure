package util;

public enum Action {
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
		for(Action temp : Action.values()) {
			if(s.equals(temp.toString())) {
				return true;
			}
		}
		throw new IllegalActionException();
	}
	
	public static Action toCommand(String s) throws IllegalActionException {
		for(Action temp : Action.values()) {
			if(s.equals(temp.toString())) {
				return temp;
			}
		}
		throw new IllegalActionException();
	}
}
