package unitsConversor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestNumero {

	public static void main(String[] args) {

		BigDecimal n1 = new BigDecimal("0.1");
		BigDecimal n2 = new BigDecimal("0.2");
		BigDecimal n3 = new BigDecimal("1");
		BigDecimal n4 = new BigDecimal("3");
		
		System.out.println(n1.add(n2));
		System.out.println(n3.divide(n4, 3, RoundingMode.UP));
	}

}