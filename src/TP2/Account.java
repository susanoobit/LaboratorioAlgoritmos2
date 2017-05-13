package TP2;
import java.util.Comparator;

public class Account implements Comparable<Account> {
	private Client client;
	public final static int MAX_CODE_LENGTH = 5;
	private int[] code = new int[MAX_CODE_LENGTH];
	private float balance;
	
	public Account (String clientName, int[] code, float balance) throws Exception {
		this.client = new Client(clientName);
		validateCode(code);
		this.code = code.clone();
		this.setBalance(balance);
	}

	@Override
	public String toString() {
		return "[Client name: " + this.client.getName() + ", " +
			"Code: " + getStringCode() + ", " +
			"Balance: " + String.valueOf(balance) + "]";
	}
	
	@Override
	public Account clone() {
		try {
			return new Account(this.client.getName(), this.code.clone(), this.balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int[] getCode () {
		return code;
	}
	
	public String getStringCode () {
		String sc = "";
		for (int i : this.code) sc += String.valueOf(i);
		return sc;
	}
	
	public void setCode (int[] code) throws Exception {
		validateCode(code);
		this.code = code.clone();
	}
	
	public Client getClient () {
		return client;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public String getCurrencyBalance(String currency, String decimalSeparator) {
		return currency + " " + String.format("%.2f", balance);
	}
	
	private static void validateCode (int[] code) throws Exception {
		if (code.length != MAX_CODE_LENGTH) throw new Exception("Client code length is out of range. Expected length: " + MAX_CODE_LENGTH + ".");
	}
	
	@Override
	public int compareTo(Account a) {
		int c = compareBy(a, Account::compareByClient);
		if (c != 0) return c;
		c = compareBy(a, Account::compareByCode);
		if (c != 0) return c;
		c = compareBy(a, Account::compareByBalance);
		return c;
	}
	
	public int compareBy(Account a, Comparator<Account> comp) {
		return comp.compare(this, a);
	}
	
	public static int compareByClient (Account a1, Account a2) {
		return a1.getClient().compareTo(a2.getClient());
	}
	
	public static int compareByCode (Account a1, Account a2) {
		return a1.getStringCode().compareTo(a2.getStringCode());
	}
	
	public static int compareByBalance (Account a1, Account a2) {
		return Float.compare(a1.getBalance(), a2.getBalance());
	}
}
