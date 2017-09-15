package cl.scripts.generaConsts;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final GeneraConstante obj = new GeneraConstante(
			"R:\\desarrollo\\workspace\\Scripts\\src\\cl\\scripts\\generaConsts\\ConstPrueba.java", 
			"CST", "nombreFichSinFecha2", null, GeneraConstante.Tipos.String);
		
		final GeneraConstante obj2 = new GeneraConstante(
				"R:\\desarrollo\\workspace\\Scripts\\src\\cl\\scripts\\generaConsts\\ConstPrueba.java", 
				"CSTXKJCCCL", "xxx", "3", GeneraConstante.Tipos.Long);
		// 10 val max para apostrofe
		// CST_GNRAANX230456
		// CST_ENERCNANE1346
		// CST_GNACNSANTX256
		// CST_GNERCOTE23046
		// CST_GENATEX130456
		for (int x = 0; x < 1; x++) {
			obj.generarConstante();
			obj2.generarConstante();
		}
	}

}
