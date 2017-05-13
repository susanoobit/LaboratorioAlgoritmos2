package Aula4;


public class Client {

	private int code;
	private String name;
	
	public Client () {
		this.code = 0;
		this.name = "";
	}
	
	public Client (int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "" + code + ";" + name;
	}

	public int getcode () { return code; }
	
	public void setcode (int code) { this.code = code; }
	
	public String getname () { return name; }
	
	public void setname (String name) { this.name = name; }

}
