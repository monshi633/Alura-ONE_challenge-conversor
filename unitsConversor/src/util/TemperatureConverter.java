package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import enums.TemperatureUnit;

public class TemperatureConverter {
	/*
	 * Converts a BigDecimal from one unit to another using C as a medium if needed to
	 * i.e: from F to K: convertToC(F) -> convertFromC(K)
	 */
	public static BigDecimal getConversionValue(BigDecimal inputValue, TemperatureUnit fromUnit, TemperatureUnit toUnit) {
		if (fromUnit.toString() == "CELSIUS") {
			return convertFromC(inputValue, toUnit).setScale(3, RoundingMode.HALF_UP);
		} else if (toUnit.toString() == "CELSIUS") {
			return convertToC(inputValue, fromUnit).setScale(3, RoundingMode.HALF_UP);
		} else {
			return convertFromC(convertToC(inputValue, fromUnit), toUnit).setScale(3, RoundingMode.HALF_UP);
		}
	}

	/*
	 * Converts value from any unit to C
	 */
	private static BigDecimal convertToC(BigDecimal inputValue, TemperatureUnit unitFrom) {
		switch (unitFrom) {
		case FARENHEIT:
//			return (inputValue - 32) * 5 / 9;
			return inputValue.subtract(new BigDecimal("32")).multiply(new BigDecimal("5")).divide(new BigDecimal("9"),10,RoundingMode.HALF_UP);
		case KELVIN:
//			return inputValue - 273.15;
			return inputValue.subtract(new BigDecimal("273.15"));
		default:
			throw new IllegalArgumentException("Unexpected value: " + unitFrom);
		}
	}

	/*
	 * Converts value from C to any unit
	 */
	private static BigDecimal convertFromC(BigDecimal inputValue, TemperatureUnit unitFrom) {
		switch (unitFrom) {
		case FARENHEIT:
//			return inputValue * 9 / 5 + 32;
			return inputValue.multiply(new BigDecimal("9")).divide(new BigDecimal("5"),10,RoundingMode.HALF_UP)
					.add(new BigDecimal("32"));
		case KELVIN:
//			return inputValue + 273.15;
			return inputValue.add(new BigDecimal("273.15"));
		default:
			throw new IllegalArgumentException("Unexpected value: " + unitFrom);
		}
	}
	
}
