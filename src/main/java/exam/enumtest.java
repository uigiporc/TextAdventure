package exam;

public enum enumtest {
	h(0),
	g(1),
	f(2),
	e(3),
	d(4),
	c(5),
	b(6),
	a(7);
	
	private final int code;
	
	enumtest(int code){
		this.code = code;
	}
	
	public int getCode(){
		return this.code;
	}
	
	public static int getEnumCode(String c) {
		return enumtest.valueOf(c).getCode();
	}
}
