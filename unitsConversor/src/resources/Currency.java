package util;

public class Currency {

	private String base;
	private String to;
	private double amount;
	private double converted;
	private double rate;
	private int lastUpdate;
	
	public Currency(String base, String to, double amount, double converted, double rate, int lastUpdate) {
		this.base = base;
		this.to = to;
		this.amount = amount;
		this.converted = converted;
		this.rate = rate;
		this.lastUpdate = lastUpdate;
	}

	public double getConverted() {
		return converted;
	}
}
