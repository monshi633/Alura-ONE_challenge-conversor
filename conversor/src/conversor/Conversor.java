package conversor;

public class Conversor {

	public boolean validateInput(String amount) {
//		input debe tener al menos un numero seguido de un punto opcional
//		y hasta 8 digitos despues del punto
		if(!amount.matches("^\\d+(\\.\\d{0,8})?$")) {
//			Si input no aprueba se comprueba la existencia de guiones
//			para dar pista de que solo se aceptan numeros positivos
			if(amount.matches(".*-.*")) {
				System.out.println("No se admiten numeros negativos");
				return false;
			}
			System.out.println("Solo valores numericos, opcional de hasta 8 digitos despues del punto");
			return false;
		}
		System.out.println(amount);
		return true;
	}
}
