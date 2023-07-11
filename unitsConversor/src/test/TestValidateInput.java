package test;

import util.ValidateInput;

public class TestValidateInput {

	public static void main(String[] args) {

		String input = "10.050";
		System.out.println(ValidateInput.validateInput(input));
		System.out.println(ValidateInput.getValidationError());
	}

}
