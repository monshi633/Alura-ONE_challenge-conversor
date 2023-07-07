package test;

import java.math.BigDecimal;

import enums.CurrencyUnit;
import util.CurrencyConverter;

public class TestCurrencyConverter {

	public static void main(String[] args) {

		CurrencyUnit inputFrom = CurrencyUnit.JPY;
		CurrencyUnit inputTo = CurrencyUnit.MXN;
		BigDecimal inputValue = new BigDecimal("125.46");
		BigDecimal convertedCurrency = CurrencyConverter.getConversionValue(inputValue, inputFrom, inputTo);
		System.out.println(convertedCurrency);
		
	}

}
