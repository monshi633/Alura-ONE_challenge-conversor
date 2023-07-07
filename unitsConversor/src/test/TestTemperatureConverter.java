package test;

import java.math.BigDecimal;

import enums.TemperatureUnit;
import util.TemperatureConverter;

public class TestTemperatureConverter {

	public static void main(String[] args) {

		TemperatureUnit inputFrom = TemperatureUnit.KELVIN;
		TemperatureUnit inputTo = TemperatureUnit.FARENHEIT;
		BigDecimal inputValue = new BigDecimal("125.329");
		BigDecimal convertedTemp = TemperatureConverter.getConversionValue(inputValue, inputFrom, inputTo);
		System.out.println(convertedTemp);

	}
}
