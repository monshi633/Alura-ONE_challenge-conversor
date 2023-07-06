package test;

import enums.TemperatureUnit;
import util.TemperatureConverter;

public class TestTempConvert {

	public static void main(String[] args) {

//		TemperatureConvert tc = new TemperatureConvert("10.012", "CELSIUS", "FARENHEIT");
//		System.out.println(tc.getConversionValue());
		
//		TemperatureConverter tc = new TemperatureConverter();
		TemperatureUnit inputFrom = TemperatureUnit.KELVIN;
		System.out.println(inputFrom.toString());
		TemperatureUnit inputTo = TemperatureUnit.FARENHEIT;
		double convertedTemp = TemperatureConverter.getConversionValue(10.25, inputFrom, inputTo);
		System.out.println(convertedTemp);
//		tc.getConversionValue(10.25, inputFrom, inputTo);
	}
}
