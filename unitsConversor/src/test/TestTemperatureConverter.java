package test;

import java.math.BigDecimal;

import enums.TemperatureUnit;
import util.TemperatureConverter;

public class TestTemperatureConverter {

	public static void main(String[] args) {

		TemperatureUnit inputFrom = TemperatureUnit.FARENHEIT;
		TemperatureUnit inputTo = TemperatureUnit.KELVIN;
		BigDecimal inputValue = new BigDecimal("10.253");
		BigDecimal convertedTemp = TemperatureConverter.getConversionValue(inputValue, inputFrom, inputTo);
		System.out.println(convertedTemp);

	}
}
