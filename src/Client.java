
public class Client implements Comparable<Client> {
	private String name;
	
	public Client (String name) {
		this.name = name;
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) throws Exception {
		validateName(name);
		this.name = name;
	}
	
	private void validateName(String name) throws Exception {
		if (name.length() == 0) throw new Exception("Client name cannot be empty.");
	}
	
	@Override
	public int compareTo(Client c) {
		return name.compareTo(c.getName());
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public Client clone() {
		return new Client(this.name);
	}
}
