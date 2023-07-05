package test;

import com.google.gson.*;

import unitsConversor.Currency;

public class TestJSON {

	public static void main(String[] args) {

		String jString = "{\"base\":\"USD\",\"to\":\"EUR\",\"amount\":100,\"converted\":91.79,\"rate\":0.9179,\"lastUpdate\":1688428800}";

		Gson gson = new Gson();
		Currency moneda = gson.fromJson(jString, Currency.class);
		System.out.println(moneda.getConverted());
	}

}