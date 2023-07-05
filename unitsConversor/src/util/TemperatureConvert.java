package util;

import java.math.BigDecimal;
import enums.TemperatureUnit;

public class TemperatureConvert {

	private BigDecimal inputValue;
	private String inputUnitFrom;
	private String inputUnitTo;

	private BigDecimal cToF = new BigDecimal("2");

	public TemperatureConvert(String inputValue, String inputUnitFrom, String inputUnitTo) {
//		this.inputValue = Double.parseDouble(inputValue);
		this.inputValue = new BigDecimal(inputValue);
		this.inputUnitFrom = inputUnitFrom;
		this.inputUnitTo = inputUnitTo;
	}

	/*
	 * Converts inputValue from one unit to another
	 * using C as a medium if needed to
	 * i.e: from F to K: convertToC(F) -> convertFromC(K)
	 */
	public BigDecimal getConversion() {
		if (inputUnitFrom == "C" && inputUnitTo == "F") {
			return inputValue.multiply(cToF);
		}
		return inputValue;
	}
	
	/*
	 * Converts value from any unit to C
	 */
	private double convertToC(double value, TemperatureUnit fromUnit) {
		switch (fromUnit) {
		case FARENHEIT:
			return (value - 32) * 5 / 9;
		case KELVIN:
			return value - 273.15;
		default:
			throw new IllegalArgumentException("Unexpected value: " + fromUnit);
		}
	}

	/*
	 * Converts value from C to any unit
	 */
//	public void convertfromC() {
//
//	}
}
