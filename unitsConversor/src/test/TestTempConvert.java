package test;

import enums.TemperatureUnit;
import util.TemperatureConverter;

public class TestTempConvert {

	public static void main(String[] args) {

//		TemperatureConvert tc = new TemperatureConvert("10.012", "CELSIUS", "FARENHEIT");
//		System.out.println(tc.getConversionValue());
		
		TemperatureConverter tc = new TemperatureConverter();
		TemperatureUnit inputFrom = null;
		TemperatureUnit inputTo = null;
		inputFrom.setUnitName("CELCIUS");
		inputTo.setUnitName("FARENHEIT");
		tc.getConversionValue(10.25, inputFrom, inputTo);

	}

}
