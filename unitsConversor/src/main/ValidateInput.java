package main;

public class ValidateInput {

	private static String validationError;
	
	public static boolean validateInput(String amount) {
		/*
		 * Input must have at least a number
		 * followed by an optional dot and at least one number
		 */		
		if (amount.matches("^\\d+(\\.\\d+)?$") || amount.matches("")) {
			return true;
		} else {
			if (amount.matches("-.*")) {
				validationError = "No se admiten numeros negativos";
				return false;
			} else {
				validationError = "Solo numeros y opcional un punto seguido de decimales";
				return false;
			}
		}
	}

	public static String getValidationError() {
		return validationError;
	}
	
}