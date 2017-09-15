package cl.scripts.formatogln;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;


public class FormatoGLN {

	public static void main(String[] args) {
//		MathContext ct = new MathContext(1);
		final BigDecimal precio = new BigDecimal("0.0004");
		
		String retorno = null;
		// comparador
		final BigDecimal zerouno = new BigDecimal("0.01");
		// precioCalculado >= 0,01
		if (precio.compareTo(zerouno) >= 0) {
			// precioUnidadMedida truncado a 2 decimales. Ej. 2,4 -> 2,40
			final DecimalFormat formatter = new DecimalFormat("0.00");
			retorno = (formatter.format(precio).replace(
					".", ",")).replace(",00",
					"");
		} else {
			// Si precioCalculado < 0,01
			// comparador
			final BigDecimal zerocinco = new BigDecimal("0.0005");
			// Si precioCalculado < 0,0005
			if (precio.compareTo(zerocinco) < 0) {
				// asignamos al parámetro de salida precioUnidadMedida el valor null
				retorno = String.valueOf(retorno);
			} else {
				// Si precioCalculado >= 0,0005, truncado a 3 decimales
				final DecimalFormat formatter = new DecimalFormat("0.000");
				retorno = (formatter.format(precio).replace(
					".", ",")).replace(",000",
					"");
			}
		}
		
		System.out.println(retorno);
		
		return;
	}

	private void metodo() {
		// Obtengo parametros  codGln
		//final String codGln = (String) "8412345678912";
//		final String codGln = (String) "8499999999090";
//		final String codGln = (String) "8400000000000";
		final String codGln = (String) "8411111111111";
		// si no mide 13 caracteres
		if (codGln.length() != 13) {
			// invalido
//			establecerValides(userDTO, Boolean.FALSE);
			System.out.println("invalido");
			// quiebro el flujo
			return;
		}
		// descompongo la cadena
		// 1-2
		final String subCad1_2 = codGln.substring(0, 2);
		// si es distinta de 84
		if (!"84".equals(subCad1_2)) {
			// invalido
//			establecerValides(userDTO, Boolean.FALSE);
			System.out.println("invalido");
			// quiebro el flujo
			return;
		}
		// 3-7
		final String subCad3_7 = codGln.substring(2, 7);
		// si no es numerico
		if (!subCad3_7.matches("[0-9]+")) {
			// invalido
//			establecerValides(userDTO, Boolean.FALSE);
			System.out.println("invalido");
			// quiebro el flujo
			return;
		}
		// 8-12
		final String subCad8_12 = codGln.substring(7, 12);
		// si no es numerico
		if (!subCad8_12.matches("[0-9]+")) {
			// invalido
//			establecerValides(userDTO, Boolean.FALSE);
			System.out.println("invalido");
			// quiebro el flujo
			return;
		}
		// 13
		final String subCad13 = codGln.substring(12, 13);
		// 13 deberia ser numero aunqque dtan no dice naipe
		
		// chequear algoritmo Dígito Control GLN
		// Caracter 1
		int digito12IniIzda = Integer.parseInt(codGln.substring(0, 1));
		// Caracter 2
		int digito11IniIzda = Integer.parseInt(codGln.substring(1, 2));
		// Caracter 3
		int digito10IniIzda = Integer.parseInt(codGln.substring(2, 3));
		// Caracter 4
		int digito9IniIzda = Integer.parseInt(codGln.substring(3, 4));
		// Caracter 5
		int digito8IniIzda = Integer.parseInt(codGln.substring(4, 5));
		// Caracter 6
	    int digito7IniIzda = Integer.parseInt(codGln.substring(5, 6));
		// Caracter 7
		int digito6IniIzda = Integer.parseInt(codGln.substring(6, 7));
		// Caracter 8
		int digito5IniIzda = Integer.parseInt(codGln.substring(7, 8));
		// Caracter 9
		int digito4IniIzda = Integer.parseInt(codGln.substring(8, 9));
		// Caracter 10
		int digito3IniIzda = Integer.parseInt(codGln.substring(9, 10));
		// Caracter 11
		int digito2IniIzda = Integer.parseInt(codGln.substring(10, 11));
		// Caracter 12
		int digito1IniIzda = Integer.parseInt(codGln.substring(11, 12));
		// Caracter 13
//		int digitoControlGln = Integer.parseInt(codGln.substring(12, 13));
		
		// multiplico impares por 3
		digito11IniIzda *= 3;
		digito9IniIzda *= 3;
		digito7IniIzda *= 3;
		digito5IniIzda *= 3;
		digito3IniIzda *= 3;
		digito1IniIzda *= 3;

		// Sumamos todas las multiplicaciones individuales
		int totalSumParc =  digito12IniIzda
			+ digito11IniIzda
			+ digito10IniIzda
			+ digito9IniIzda
			+ digito8IniIzda
			+ digito7IniIzda
			+ digito6IniIzda
			+ digito5IniIzda
			+ digito4IniIzda
			+ digito3IniIzda
			+ digito2IniIzda
			+ digito1IniIzda;
		// decena superior a totalSumParc
		int decenaSupTotal = (int) (Math.floor(totalSumParc / 10) * 10) + 10;
		// resto
		int difDecenaSupTotal = decenaSupTotal - totalSumParc;
		// si es 10 deberia ser 0
		if (difDecenaSupTotal == 10) {
			difDecenaSupTotal = 0;
		}
		// si caracter nº 13 coincide con difDecenaSupTotal
		if (subCad13.equals(String.valueOf(difDecenaSupTotal))) {
			//  es valido
//			establecerValides(userDTO, Boolean.TRUE);
			System.out.println("Valido");
		} else {
			// sino, es invalido
//			establecerValides(userDTO, Boolean.FALSE);
			System.out.println("invalido");
		}
	}
}
