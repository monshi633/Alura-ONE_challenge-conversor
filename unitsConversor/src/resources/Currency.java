package resources;

import java.math.BigDecimal;

public class Currency {

	@SuppressWarnings("unused")
	private String base;
	@SuppressWarnings("unused")
	private String to;
	@SuppressWarnings("unused")
	private double amount;
	private double converted;
	@SuppressWarnings("unused")
	private double rate;
	@SuppressWarnings("unused")
	private int lastUpdate;
	
	public Currency(String base, String to, double amount, double converted, double rate, int lastUpdate) {
		this.base = base;
		this.to = to;
		this.amount = amount;
		this.converted = converted;
		this.rate = rate;
		this.lastUpdate = lastUpdate;
	}

	public BigDecimal getConverted() {
		String convertedString = String.valueOf(converted);
		return new BigDecimal(convertedString);
	}
}
