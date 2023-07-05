package test;

import util.TemperatureConvert;

public class TestTempConvert {

	public static void main(String[] args) {

		TemperatureConvert tc = new TemperatureConvert("10.012", "C", "F");
		System.out.println(tc.getConversion());

	}

}
