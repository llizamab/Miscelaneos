package cl.scripts.generaTexto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class GeneraText {
	
	
	public static void metodo(Boolean flag) {
		System.out.println("metodo ini - flag:" + flag);
		flag = true;
		System.out.println("metodo out - flag:" + flag);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		final String[] a = new String[]{"[gruposPedExt][gruposPedExt data]codGrupoPedExtOrig"
		 ,"[gruposPedExt][gruposPedExt data]codGrupoPedExt"
		 ,"[gruposPedExt][gruposPedExt data]descCortaGrupoPedExt"
		 ,"[gruposPedExt][gruposPedExt data]fechaInicioSurtidoGpe"
		 ,"[gruposPedExt][gruposPedExt data]fechaFinSurtidoGpe"
		 ,"[gruposPedExt][gruposPedExt data]txtTipoTransporte"
		 ,"[gruposPedExt][gruposPedExt data]codLocProveedor"
		 ,"[gruposPedExt][gruposPedExt data]descLocProveedor"
		 ,"[gruposPedExt][gruposPedExt data]codTerceroProveedor"
		 ,"[gruposPedExt][gruposPedExt data]codPubProveedor"
		 ,"[gruposPedExt][gruposPedExt data]descProveedor"
		 ,"[gruposPedExt][gruposPedExt data]codLocAlmacen"
		 ,"[gruposPedExt][gruposPedExt data]descLocAlmacen"
		 ,"[gruposPedExt][gruposPedExt data]codTerceroAlmacen"
		 ,"[gruposPedExt][gruposPedExt data]codPubAlmacen"
		 ,"[gruposPedExt][gruposPedExt data]descAlmacen"
		 ,"[gruposPedExt][gruposPedExt data]codPtoOperGlnPoCarga"
		 ,"[gruposPedExt][gruposPedExt data]codLocalizPoCarga"
		 ,"[gruposPedExt][gruposPedExt data]txtNombrePoCarga"
		 ,"[gruposPedExt][gruposPedExt data]codPtoOperGlnPoDescarga"
		 ,"[gruposPedExt][gruposPedExt data]codLocalizPoDescarga"
		 ,"[gruposPedExt][gruposPedExt data]txtNombrePoDescarga"
		 ,"[gruposPedExt][gruposPedExt data]codPtoOperGlnPoFacturacion"
		 ,"[gruposPedExt][gruposPedExt data]codLocalizPoFacturacion"
		 ,"[gruposPedExt][gruposPedExt data]txtNombrePoFacturacion"
		 ,"[gruposPedExt][gruposPedExt data]codPtoOperGlnPoFacturProv"
		 ,"[gruposPedExt][gruposPedExt data]codLocalizPoFacturProv"
		 ,"[gruposPedExt][gruposPedExt data]txtNombrePoFacturProv"
		 ,"[gruposPedExt][gruposPedExt data]codPtoOperGlnPoPago"
		 ,"[gruposPedExt][gruposPedExt data]codLocalizPoPago"
		 ,"[gruposPedExt][gruposPedExt data]txtNombrePoPago"
		 ,"[gruposPedExt][gruposPedExt data]codPtoOperGlnPoContacto"
		 ,"[gruposPedExt][gruposPedExt data]codLocalizPoContacto"
		 ,"[gruposPedExt][gruposPedExt data]txtNombrePoContacto"
		 ,"[gruposPedExt][gruposPedExt data]codDemoraPedir"
		 ,"[gruposPedExt][gruposPedExt data]codHoraTopePedir"
		 ,"[gruposPedExt][gruposPedExt data]codDemoraGenPlanCom"
		 ,"[gruposPedExt][gruposPedExt data]codHoraTopeGenPlanCom"
		 ,"[gruposPedExt][gruposPedExt data]codDemoraExpedir"
		 ,"[gruposPedExt][gruposPedExt data]codHoraTopeExpedir"
		 ,"[gruposPedExt][gruposPedExt data]codDemoraEntradaAlmacen"
		 ,"[gruposPedExt][gruposPedExt data]codHoraTopeEntradaAlmacen"
		 ,"[gruposPedExt][gruposPedExt data]codLoginFormalizador"
		 ,"[gruposPedExt][gruposPedExt data]fecFechaValor"
		 ,"[gruposPedExt][gruposPedExt data]codLocAlmacenReg"
		 ,"[gruposPedExt][gruposPedExt data]descLocAlmacenReg"
		 ,"[gruposPedExt][gruposPedExt data]codTerceroAlmacenReg"
		 ,"[gruposPedExt][gruposPedExt data]codPubAlmacenReg"
		 ,"[gruposPedExt][gruposPedExt data]descAlmacenReg"
		 ,"[gruposPedExt][gruposPedExt data]codGrupoCoberturaAlm"};
		
		
//		for (final String xx : a) {
//			System.out.println("-------------");
//			System.out.println("Ordenación por " + xx);
//			System.out.println("");
//
//			System.out.println("Paso 1. Se ejecuta la operación informando ordenación por el parámetro " + xx + " ascedente o descedente.");
//			System.out.println("Paso 2. La operación pasa las validaciones de entrada y termina el flujo correctamente.");
//			System.out.println("Paso 3. Validar que el retorno está ordenado de acorde a lo informado inicialmente.");
//		}
//		
		
		for (int x = 7; x < 87; x++) {

			System.out.println("");
			System.out.println("-----------------------------------");
			if (x < 10) {
			System.out.println("-- <OP_01-F-00"+ x +">");
			} else {
				System.out.println("-- <OP_01-F-0"+ x +">");	
			}
			
			System.out.println("-----------------------------------");
			System.out.println("-- <PRE-CONDICION>");
			System.out.println("");
			System.out.println("-- </PRE-CONDICION>");
			System.out.println("-- <POST-CONDICION>");
			System.out.println("-- No es necesario insertar datos");
			System.out.println("-- </POST-CONDICION>");
			System.out.println("");
			System.out.println("-----------------------------------");
			if (x < 10) {
			System.out.println("-- </OP_01-F-00"+ x +">");
			} else {
				System.out.println("-- <OP_01-F-0"+ x +">");	
			}
			
			
			System.out.println("-----------------------------------");
			System.out.println("");

		}
		
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		System.out.println(cal.getTimeInMillis());
		
		System.out.println(cal.getTime().getTime());
		
		
		
//		for (int x = 0; x < 700; x++) {
//			System.out.println("<ns:gvsCentrosEliminadosData>");
//			System.out.println("\t<ns:codCentroNegocio>33682000601" + x + "</ns:codCentroNegocio>");
//			System.out.println("</ns:gvsCentrosEliminadosData>");
//		}
		
		
		final Boolean flag = false;
		
		metodo(flag);
		
		if (!flag) {
			System.out.println("entro al if");
		}
		
		
		// sumar 3 doubles
//		final List<Double> dbls = new ArrayList<Double>();
//		dbls.add(-999999999.999d);
//		dbls.add(52.126d);
//		dbls.add(41.434d);
//		Double result = null;
//		boolean flag = true;
//		
//		for (final Double valor : dbls) {
//			if (flag) {
//				result = valor;
//				flag = false;
//			} else {
//				
//				final BigDecimal bg = new BigDecimal(String.valueOf(result));
//				result = bg.add(new BigDecimal(String.valueOf(valor))).doubleValue();
//				
////				result = result + valor;
//				System.out.println("suma:" + trunc3dec(result));
//			}
//		}
//		System.out.println("Total:" + trunc3dec(result));
//		
		
		
		
		/*
		
		final BigDecimal valor = new BigDecimal("0.000000000000000000000000000000001");
		
		boolean tieneDecimales = false;
		if (valor != null) {
			// tiene decimales si el valor absoluto de la resto de 
			// si mismo sin decimales es mayor de zero
			tieneDecimales = new BigDecimal("0").compareTo(
				valor.subtract(
					new BigDecimal(valor.toBigInteger())).abs()) > 0;
		}
		
		System.out.println("valor: " + valor.toString());
		System.out.println("tiene decimales: " + tieneDecimales);
		
		
		tieneDecimales = valor.remainder(BigDecimal.ONE).abs().compareTo(new BigDecimal("0")) > 0;
		
		System.out.println("tiene decimales: " + tieneDecimales);
		
		
		
		BigDecimal d = BigDecimal.valueOf(-1548.5649);
		BigDecimal result = d.subtract(d.setScale(0, RoundingMode.FLOOR)).movePointRight(d.scale());      
		System.out.println(result);
		
		*/
		
		/*
		String tblIntermedia = "D_EJECUTOR_INT";
		
		final String insert = "INSERT INTO " + tblIntermedia
				+ " SELECT * FROM " + tblIntermedia.substring(0, 
						tblIntermedia.lastIndexOf("_INT"));
		
		
		
		//Si es 2,3557 = 2
		System.out.println("2,3557 = " + round0dec(2.3557d));
		//Si es 2,7557 = 3
		System.out.println("2,7557 = " + round0dec(2.7557d));
		//Si es 2,5000 = 3
		System.out.println("2,5000 = " + round0dec(2.5000d));
		System.out.println("1 = " + 1d);
		
		System.out.println("2,3557 = " + trunc3dec(2.3557d));
		//Si es 2,7557 = 3
		System.out.println("2,75599 = " + trunc3dec(2.75599d));
		
		System.out.println("2,9 = " + trunc3dec(2.9d));
		
		System.out.println("0,0 = " + trunc3dec(0d));
		
		System.out.println("2,009 = " + trunc3dec(2.009d));
		*/

//		System.out.print(buuff);
		
	}
	
	private static void generaInsert() {

		final StringBuilder soap = new StringBuilder();
		final StringBuilder inserts = new StringBuilder();
		
		int ped = 0;
		for (int x = 0; x < 10; x++) { // 10
			
			soap.append("<ns:listaCentrosData>\n\t")
			.append("<ns:codCentroNegocio>"+ x +"</ns:codCentroNegocio>\n")
			.append("</ns:listaCentrosData>\n");
			
			inserts.append("INSERT INTO D_ORGANIZACION_R(COD_N_TERCERO, COD_N_TIPO_TERCERO) VALUES ("+ x +", 74);\n");

			inserts.append("INSERT INTO D_CENTRO_NEGOCIO_R(COD_N_CENTRO_NEGOCIO, COD_N_PUBLICO) VALUES ("+ x +", 1201);\n");
			
			inserts.append("INSERT INTO D_TIENDA_R(COD_N_CENTRO_NEGOCIO) VALUES ("+ x +");\n");
			
			inserts.append("INSERT INTO O_Pedidos_Prevventa_Prod_H(COD_N_CENTRO_NEGOCIO, COD_N_ITEM, FEC_D_PREVISION, FEC_D_ANCLA_1, NUM_TOTAL_PREVISION_VENTA_KG, NUM_TOTAL_PREVISION_VENTA_UD, NUM_CANTIDAD_ALARMA, COD_N_UM_ALARMA) VALUES ("+ x +",120000000001, TRUNC(SYSDATE), TRUNC(SYSDATE), 20.02, 2.02, 1.01, 2);\n");
		
			for (int y = 0; y < 300; y++, ped++) { // 300
				
				/* op1
				inserts.append("INSERT INTO O_PEDIDO(COD_N_PEDIDO, COD_V_ESTADO_PEDIDO_ACTUAL, ")
					.append("COD_N_CENTRO_NEGOCIO, COD_N_NEGOCIO, COD_N_PUBLICO_CENTRO, ")
					.append("FEC_D_FECHA_SERVICIO, FEC_D_CIERRE, COD_N_GRUPO_COMPRA, ")
					.append("TXT_DESCRIPCION_GC, COD_N_TIPO_SERVICIO, FEC_D_CREACION, ")
					.append("COD_V_USR_CREACION, MCA_PEDIDO_COMPLETO) ")
				.append("VALUES (" + ped + ", 'VE' , "+ x +", 11, 1101, TRUNC(SYSDATE), ")
					.append("TRUNC(SYSDATE), 127400000001, 'DESCRIPCION GC', 74, SYSDATE, 'FdP', 'S');\n");
				
				inserts.append("INSERT INTO O_PEDIDO_LINEA_PEDIDO (COD_N_LINEA_PEDIDO, COD_N_PEDIDO, ") 
				.append("COD_N_MERCA, COD_N_ITEM,TXT_DESCRIPCION_ITEM, NUM_PESO_PROMEDIO,  ")
				.append("COD_N_FORMATO_ITEM, COD_N_VARIABLE_LOGISTICA, COD_N_UNIDAD_MEDIDA, ") 
				.append("NUM_PEDIDO_CANTIDAD_FORMATO, TXT_UNIDAD_MEDIDA, COD_N_TIPO_PEDIDO, ") 
				.append("FEC_D_FECHA_SERVICIO, FEC_D_CREACION, COD_V_USR_CREACION, MCA_VISIBLE) ") 
				.append("VALUES (270000000001, " + ped + ", 11001, 110000000001, 'DESC ITEM', 1, ") 
				.append("74, 2, " + y + ", 4, 'AA', 5, TRUNC(SYSDATE), SYSDATE, 'FdP' ,'S');\n");
				*/
				
				
				
				
				inserts.append("INSERT INTO T_RDS_SURTIDO_TIENDA_P2(COD_N_ITEM, COD_N_MERCA, COD_N_TERCERO_TIENDA, COD_N_PUBLICO_TIENDA, NUM_PESO_PROMEDIO, FEC_D_FECHA_INICIO, FEC_D_FECHA_FIN) VALUES(120000000001, 12001, "+ x +", 1201, 12."+ y +", SYSDATE-2, SYSDATE+2);\n");
			}
			
			/* op1
			inserts.append("INSERT INTO T_RDS_SURTIDO_TIENDA_P2(COD_N_ITEM, COD_N_TERCERO_TIENDA, COD_N_PUBLICO_TIENDA, ") 
				.append("NUM_PESO_PROMEDIO, FEC_D_FECHA_INICIO, FEC_D_FECHA_FIN) VALUES ")
				.append("(110000000001, "+ x +", 1101, 11.01, SYSDATE-2, SYSDATE+2);\n");
			
			inserts.append("INSERT INTO O_PEDIDO_OBSERVACION_TIENDA (COD_N_OBSERVACION_PEDIDO, COD_N_CENTRO_NEGOCIO, MCA_OBSERVACION_LEIDA) ") 
			.append("VALUES(74000000000, "+ x +", 'S');\n");
			*/
		}
		
		
		System.out.println("-------------------------------------------------");
		System.out.println(soap.toString());
		
		
		System.out.println("-------------------------------------------------");
		System.out.println(inserts.toString());
		
		System.out.println("-------------------------------------------------");
		
	}
	
	private static Double trunc3dec(final Double entrada) {
		// declaro salida
		Double salida = null;
		// si no es null
		if (entrada != null) {
			final DecimalFormat format = new DecimalFormat("0.###");
			format.setRoundingMode(RoundingMode.DOWN);
			// creo un bigdecimal
			final BigDecimal num = new BigDecimal(
					format.format(entrada).replace(",", "."));
			// trunc hasta 3 decimales
			salida = num.doubleValue();
		}
		// retorno
		return salida;
	}
	
	/**
	 * Trunca a zero decimales.
	 * @param entrada Double
	 * @return Double
	 */
	private static Double round0dec(final Double entrada) {
		// return new BigDecimal(String.valueOf(x)).setScale(numberofDecimals, BigDecimal.ROUND_DOWN);
		// declaro salida
		Double salida = null;
		// si no es null
		if (entrada != null) {
			
			// creo un bigdecimal
			final BigDecimal num = new BigDecimal(String.valueOf(entrada));
			// redondeo hacia arriba si es >= .5
//			num.setScale(0, BigDecimal.ROUND_HALF_UP);
			// setea el valor
			salida = num.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		// retorno
		return salida;
	}

	private static void fechaMenor() {
		final List<Map> listaFechas = new ArrayList<Map>();
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("fecha", new Date());
		listaFechas.add(new HashMap(map));
		
		map.put("fecha", fechaSiguiente(+1));
		listaFechas.add(new HashMap(map));
		
		map.put("fecha", fechaSiguiente(-1));
		listaFechas.add(new HashMap(map));
		
		map.put("fecha", fechaSiguiente(+2));
		listaFechas.add(new HashMap(map));
		
		Date fechaMenor = new Date(Long.MAX_VALUE);
		final Object fechaAuxiliar = null;
		
		for (final Map mapa : listaFechas) {
			final Date fecha = (Date) mapa.get("fecha");
			System.out.println(fecha);
			
			if (fecha.before(fechaMenor)) {
				fechaMenor = fecha;
			}
			
		}
		System.out.println("menor");
		System.out.println(fechaMenor);
	}
	
	private static Date fechaSiguiente(int dias){
		final Date nuevaFecha = new Date();
		final Calendar calendario = Calendar.getInstance();
		calendario.setTime(nuevaFecha);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.MINUTE, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.add(Calendar.DAY_OF_MONTH, dias);
		return calendario.getTime();
}

	
	private static void mapas() {
		final StringBuffer lCodTransac = new StringBuffer();
		final StringBuffer lCodTransac2 = new StringBuffer();
		
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("COD_N_TIPO_HABITACION","tipoHabitacion");
		map.put("TXT_NOM_TIPO_HABITACION","txtNomHabitacion");
		map.put("COD_V_GRUPO_HABITACION","codGrupoHabitacion");
		map.put("TXT_NOM_GRUPO_HABITACION","nomGrupoHabitacion");
		map.put("COD_N_TIPO_COSTE_HABITACION","codTipoCosteHabitacion");
		map.put("TXT_UBICABLE","ubicable");

		/*
		 * <isNotNull property="puntoVerdeSecc" prepend=",">
			COD_N_PVS = #puntoVerdeSecc:DECIMAL#
		</isNotNull>
		 * */

		for (final Map.Entry<String, Object> entry : map.entrySet()) {
			
			// /**
//				 * impAlcoholPeninsula.
//				 */
//				public static final String CP_PAM_IMPALPN = "impAlcoholPeninsula";
			 
			
//			lCodTransac.append("/**\n * ")
//			.append(entry.getValue())
//			.append(".\n */\n public static final String CP_PAM_")
//			.append(entry.getKey())
//			.append(" = \"")
//			.append(entry.getValue())
//			.append("\";\n");
			
			lCodTransac.append("<isNotNull property=\"")
			.append(entry.getValue())
			.append("\" prepend=\",\">\n")
			.append("\t")
			.append(entry.getKey())
			.append(" = #")
			.append(entry.getValue())
			.append(":DECIMAL#\n</isNotNull>\n");
		}
		System.out.print(lCodTransac);
		
		for (final Map.Entry<String, Object> entry : map.entrySet()) {
			// camposNoOblg.put(ConstModificarConfiguracionProducto.CP_PAM_PUNTOVREEC, null);
			lCodTransac2.append("camposNoOblg.put(ConstModificarConfiguracionProducto.CP_PAM_")
			.append(entry.getKey())
			.append(", null);\n");
		}
		System.out.print(lCodTransac2);
//		final StringBuffer buff = new StringBuffer();
//		for (int x = 0; x < 100 ; x++) {
			/*buff.append("          <ns:listaLimitesFiltroData>\n");
			buff.append("               <ns:codLimite>1071001000" + x + "</ns:codLimite>\n");
			buff.append("               <ns:codTipoLimite>2</ns:codTipoLimite>\n");
			buff.append("               <ns:nombreLimite>a</ns:nombreLimite>\n");
			buff.append("      		</ns:listaLimitesFiltroData>\n");*/
			
//			buff.append("          <ns:ccplgAgrupacionesData>\n");
//			buff.append("               <ns:codAgrupacion>" + 1700 + x + "</ns:codAgrupacion>\n");
//			buff.append("      		</ns:ccplgAgrupacionesData>\n");
//		}
//		System.out.print(buff.toString());
	}
	
	private void funcionnoseque() {

		final List<Map<String, Object>> lstOrigen = new ArrayList();
		Map<String, Object> nodoData = new HashMap<String, Object>();
		// A
		nodoData.put("COD_N_ITEM", 101L);
		nodoData.put("COD_N_ITEM_PADRE", 101L);
		lstOrigen.add(new HashMap(nodoData));
		// B
		nodoData.put("COD_N_ITEM", 102L);
		nodoData.put("COD_N_ITEM_PADRE", 101L);
		lstOrigen.add(new HashMap(nodoData));
		// C
		nodoData.put("COD_N_ITEM", 103L);
		nodoData.put("COD_N_ITEM_PADRE", 101L);
		lstOrigen.add(new HashMap(nodoData));
		// D
		nodoData.put("COD_N_ITEM", 201L);
		nodoData.put("COD_N_ITEM_PADRE", 201L);
		lstOrigen.add(new HashMap(nodoData));
		// E
		nodoData.put("COD_N_ITEM", 301L);
		nodoData.put("COD_N_ITEM_PADRE", 301L);
		lstOrigen.add(new HashMap(nodoData));
		// F
		nodoData.put("COD_N_ITEM", 302L);
		nodoData.put("COD_N_ITEM_PADRE", 301L);
		lstOrigen.add(new HashMap(nodoData));
		
		// formato
		Long formato = 0L;
		int cont = 0;

		// lista de nodos
		final List<Nodo> lstNodoOrigen = new ArrayList<Nodo>();
		final Map<Long, Object> nodosPadresOrigen = new HashMap<Long, Object>();
		
		for (final Map data : lstOrigen) {
			final Long codPadre = (Long) data.get("COD_N_ITEM_PADRE"); 
			final Long codItem = (Long) data.get("COD_N_ITEM");
			// constructor para lista origen
			final Nodo nodo = new Nodo(codItem, codPadre);
			nodosPadresOrigen.put(codItem, nodo);
			nodo.setFormato(formato.longValue() + cont++);
			lstNodoOrigen.add(nodo);
		}
		System.out.println("Arbol origen ----------------");
		// recorro para saber los niveles
		for (final Nodo data : lstNodoOrigen) {
			// si el es el primero no tiene padre
			if (data.getCodItem().longValue() != data.getCodItemPadre().longValue()) {
				data.setPadre((Nodo) nodosPadresOrigen.get(data.getCodItemPadre()));
			}
			// esto aplica solo a lista origen
			data.setNiveles();
			System.out.println(data.toMap());
		}
		System.out.println("caminos origen --------------");
		// obtener caminos
		final Map<String, Object> caminosOrigen = new HashMap<String, Object>();
		// por cada nodo
		for (final Nodo nodo : lstNodoOrigen) {
			// por cada nodo
			nodo.generarCamino(caminosOrigen);
		}
		// muestro los path
		for (final Map.Entry<String, Object> entry : caminosOrigen.entrySet()) {
			System.out.println(entry.getKey());
		}
		
		// creo los nodos destino
		final List<Map<String, Object>> lstDestino = new ArrayList<Map<String, Object>>();
		Map<String, Object> nodoDestino = new HashMap<String, Object>();
		// A
		nodoDestino.put("COD_N_ITEM", 501L);
		nodoDestino.put("COD_N_ITEM_PADRE", 501L);
		nodoDestino.put("NIVEL", 1L);
		nodoDestino.put("NIVEL_PADRE", 1L);
		lstDestino.add(new HashMap(nodoDestino));
		// B
		nodoDestino.put("COD_N_ITEM", 502L);
		nodoDestino.put("COD_N_ITEM_PADRE", 501L);
		nodoDestino.put("NIVEL", 2L);
		nodoDestino.put("NIVEL_PADRE", 1L);
		lstDestino.add(new HashMap(nodoDestino));
		// C
		nodoDestino.put("COD_N_ITEM", 503L);
		nodoDestino.put("COD_N_ITEM_PADRE", 502L);
		nodoDestino.put("NIVEL", 3L);
		nodoDestino.put("NIVEL_PADRE", 2L);
		lstDestino.add(new HashMap(nodoDestino));
		// D
		nodoDestino.put("COD_N_ITEM", 504L);
		nodoDestino.put("COD_N_ITEM_PADRE", 501L);
		nodoDestino.put("NIVEL", 2L);
		nodoDestino.put("NIVEL_PADRE", 1L);
		lstDestino.add(new HashMap(nodoDestino));
		
		final List<Nodo> lstNodoDestino = new ArrayList<Nodo>();
		final Map<Long, Object> nodosPadresDestino = new HashMap<Long, Object>();
		
		// volver a 0 para provedor valido 
		cont = 0;
		
		for (final Map data : lstDestino) {
			final Long codPadre = (Long) data.get("COD_N_ITEM_PADRE"); 
			final Long codItem = (Long) data.get("COD_N_ITEM");
			final Long nivel = (Long) data.get("NIVEL"); 
			final Long nivelPadre = (Long) data.get("NIVEL_PADRE");
			// constructor para lista origen
			final Nodo nodo = new Nodo(codItem, codPadre, nivel, nivelPadre);
			nodosPadresDestino.put(codItem, nodo);
			nodo.setFormato(formato.longValue() + cont++);
			lstNodoDestino.add(nodo);
		}
		System.out.println("Arbol destino ----------------");
		// recorro para saber los niveles
		for (final Nodo data : lstNodoDestino) {
			// si el es el primero no tiene padre
			if (data.getCodItem().longValue() != data.getCodItemPadre().longValue()) {
				data.setPadre((Nodo) nodosPadresDestino.get(data.getCodItemPadre()));
			}
			System.out.println(data.toMap());
		}
		System.out.println("caminos destino -------------");
		
		// obtener caminos
		final Map<String, Object> caminosDestino = new HashMap<String, Object>();
		// por cada nodo
		for (final Nodo nodo : lstNodoDestino) {
			// por cada nodo
			nodo.generarCamino(caminosDestino);
		}
		// muestro los path
		for (final Map.Entry<String, Object> entry : caminosDestino.entrySet()) {
			System.out.println(entry.getKey());
		}
		
		// valido si el proveedor es valido
		Boolean proveedorEsValido = Boolean.FALSE;
		// por cada path de origen, veo si en destino existe
		for (final String pathOrigen : caminosOrigen.keySet()) {
			// recorro las keys de destino
			for (final String pathDestino : caminosDestino.keySet()) {
				// si el origen esta incluido dentro del destino
				if (pathDestino.contains(pathOrigen)) {
					// si es valido, quiebro el for
					proveedorEsValido = Boolean.TRUE;
					break;
				}
			}
			// si ya es valido, quiebro el for
			if (proveedorEsValido) {
				break;
			}
		}
		// imprimo si es valido
		
		System.out.println("valido:" + proveedorEsValido);
		
		
		
		
		
		
//		fechaMenor();
		
		final String traza = String.format("%s – %s – Error no controlado: [%s]",
				"ws",
				"ope",
				"error....");
		//System.out.print(traza);
		
		
		final StringBuffer buuff = new StringBuffer();
		
		for (int x = 1; x <= 0; x++) {
			buuff.append("<ns:listaMovimData>\n")
			.append("<ns:codEstadoInv>501</ns:codEstadoInv>\n")
			.append("<ns:codItem>100000000001</ns:codItem>\n")
			.append("<ns:codLoca>99010002</ns:codLoca>\n")
			.append("<ns:codMerca>100</ns:codMerca>\n")
			.append("<ns:codMotivMov>113</ns:codMotivMov>\n")
			.append("<ns:codSubmotivMov>100</ns:codSubmotivMov>\n")
			.append("<ns:codTercero>660101</ns:codTercero>\n")
			.append("<ns:codTipoMov>9</ns:codTipoMov>\n")
			.append("<ns:fechaAlta>2014-11-24</ns:fechaAlta>\n")
			.append("<ns:fechaMovim>2014-11-24</ns:fechaMovim>\n")
			.append("<ns:fechaProceso>2014-11-23</ns:fechaProceso>\n")
			.append("<ns:indCalculoDobleStock>0</ns:indCalculoDobleStock>\n")
			.append("<ns:peso>11.0</ns:peso>\n")
			.append("<ns:uniMedida>1</ns:uniMedida>\n")
			.append("<ns:unidades>10.0</ns:unidades>\n")
			.append("<ns:unidadesItem>13.0</ns:unidadesItem>\n")
			.append("<ns:usuarioAlta>fdp</ns:usuarioAlta>\n")
			.append("<ns:valor>12.0</ns:valor>\n")
            .append("</ns:listaMovimData>\n");
		}
	}
}
