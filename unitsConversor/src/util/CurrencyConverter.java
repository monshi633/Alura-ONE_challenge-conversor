package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import enums.CurrencyUnit;
import resources.ApiKey;
import resources.Currency;

public class CurrencyConverter {

	public static BigDecimal getConversionValue(BigDecimal inputValue, CurrencyUnit fromUnit, CurrencyUnit toUnit) {
		String apiKey = new ApiKey().getApiKey();
		String urlRequest = "https://anyapi.io/api/v1/exchange/convert?base=" + fromUnit + "&to=" + toUnit + "&amount=" + inputValue.doubleValue() + "&apiKey=" + apiKey;
		return apiConnect(urlRequest);
	}
	
	/*
	 * Connects to API
	 * Sends url request
	 * Returns conversion as BigDecimal
	 */
	private static BigDecimal apiConnect(String urlRequest) {
		try {
			@SuppressWarnings("deprecation")
			URL url = new URL(urlRequest);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

//			Check if connect is made
			int responseCode = conn.getResponseCode();
//			200 OK - 400 Bad request - 500 Internal server error
			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			} else {
//				Get API response (JSON)
				InputStream inputStream = conn.getInputStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				String response = readAll(rd);
//				Extract API useful data
				Gson gson = new Gson();
				Currency currency = gson.fromJson(response, Currency.class);
				return currency.getConverted();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BigDecimal("0");
		}
	}
	
	/*
	 * Reads API response and returns it as a String
	 */
	private static String readAll(Reader rd) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		int i;
		while ((i = rd.read()) != -1) {
			stringBuilder.append((char) i);
		}
		return stringBuilder.toString();
	}
	
}
