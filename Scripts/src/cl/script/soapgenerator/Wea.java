package cl.script.soapgenerator;

public class Wea {

	public static void main(String[] args) {
		
		final String[] arr = new String[] {"codGrupoPedExtOrig"
		                      ,"codGrupoPedExt"
		                      ,"descCortaGrupoPedExt"
		                      ,"fechaInicioSurtidoGpe"
		                      ,"fechaFinSurtidoGpe"
		                      ,"txtTipoTransporte"
		                      ,"codLocProveedor"
		                      ,"descLocProveedor"
		                      ,"codTerceroProveedor"
		                      ,"codPubProveedor"
		                      ,"descProveedor"
		                      ,"codLocAlmacen"
		                      ,"descLocAlmacen"
		                      ,"codTerceroAlmacen"
		                      ,"codPubAlmacen"
		                      ,"descAlmacen"
		                      ,"codPtoOperGlnPoCarga"
		                      ,"codLocalizPoCarga"
		                      ,"txtNombrePoCarga"
		                      ,"codPtoOperGlnPoDescarga"
		                      ,"codLocalizPoDescarga"
		                      ,"txtNombrePoDescarga"
		                      ,"codPtoOperGlnPoFacturacion"
		                      ,"codLocalizPoFacturacion"
		                      ,"txtNombrePoFacturacion"
		                      ,"codPtoOperGlnPoFacturProv"
		                      ,"codLocalizPoFacturProv"
		                      ,"txtNombrePoFacturProv"
		                      ,"codPtoOperGlnPoPago"
		                      ,"codLocalizPoPago"
		                      ,"txtNombrePoPago"
		                      ,"codPtoOperGlnPoContacto"
		                      ,"codLocalizPoContacto"
		                      ,"txtNombrePoContacto"
		                      ,"codDemoraPedir"
		                      ,"codHoraTopePedir"
		                      ,"codDemoraGenPlanCom"
		                      ,"codHoraTopeGenPlanCom"
		                      ,"codDemoraExpedir"
		                      ,"codHoraTopeExpedir"
		                      ,"codDemoraEntradaAlmacen"
		                      ,"codHoraTopeEntradaAlmacen"
		                      ,"codLoginFormalizador"
		                      ,"fecFechaValor"
		                      ,"codLocAlmacenReg"
		                      ,"descLocAlmacenReg"
		                      ,"codTerceroAlmacenReg"
		                      ,"codPubAlmacenReg"
		                      ,"descAlmacenReg"
		                      ,"codGrupoCoberturaAlm"};
		
		int y = 0;
		for (int x = 34; x < 84; x++) {
			
			final StringBuilder xx = new StringBuilder();
			
		
		xx.append("<con:call id=\"b202d935-0004-44be-960a-2de3c9a3f8" + x + "\" name=\"OP_01-F-0" + x + "\"><con:settings><con:setting id=\"com.eviware.soapui.impl.wsdl.WsdlRequest@request-headers\">&lt;xml-fragment/></con:setting></con:settings><con:encoding>UTF-8</con:encoding><con:endpoint>http://localhost:7001/logis/services/ED_GrupoPedidoExterno_Detalle_v1_0_0</con:endpoint><con:request><![CDATA[<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://www.mercadona.es/schema/ED_GrupoPedidoExterno_Detalle/v1/0/0\" xmlns:ws=\"http://ws.integration.fwk.mercadona.es\" xmlns:ns1=\"http://www.mercadona.es/schema/framework/v1/0\">\n");
		xx.append("<soapenv:Header/>\n");
			xx.append(" <soapenv:Body>\n");
			xx.append("<ns:ConsultarCalendarioGPEPorGPE>\n");
			xx.append(" <ns:input>\n");
			xx.append("  <ns:localeCountry>ES</ns:localeCountry>\n");
			xx.append("  <ns:localeLanguage>es</ns:localeLanguage>\n");
			xx.append("  <ns:localeVariant>FdP</ns:localeVariant>\n");
			xx.append("   <ns:codAplicacion>logis</ns:codAplicacion>\n");
			xx.append("   <ns:codUsuario>user</ns:codUsuario>\n");
			xx.append("    <ns:gruposPedExtEnt>\n");
			xx.append("      <ns:gruposPedExtEntData>\n");
			xx.append("            <ns:indicadorIgnoraCodGPE>0</ns:indicadorIgnoraCodGPE>\n");
			xx.append("            <ns:fechaDesde>2017-01-23T14:43:30.123</ns:fechaDesde>\n");
			xx.append("            <ns:indicadorPoFacturProvEsNulo>0</ns:indicadorPoFacturProvEsNulo>\n");
			xx.append("           <ns:indBusquedaCobertura>S</ns:indBusquedaCobertura>\n");
          xx.append("            <ns:devolverRegulador>N</ns:devolverRegulador> \n");
          xx.append("    <ns:indFecha>V</ns:indFecha>\n");
          xx.append("   </ns:gruposPedExtEntData>\n");
          xx.append("  </ns:gruposPedExtEnt>\n");
          xx.append("   <ws:serviceContext>\n");
          xx.append("      <ns1:pageContext>\n");
          xx.append("       <ns1:orderBy>" + arr[y] + "</ns1:orderBy>\n");
          xx.append("         <ns1:orderType>asc</ns1:orderType>\n");
          xx.append("      </ns1:pageContext>\n");
          xx.append("      </ws:serviceContext>\n");
          xx.append("    </ns:input>\n");
          xx.append("  </ns:ConsultarCalendarioGPEPorGPE>\n");
          xx.append("  </soapenv:Body>\n");
          xx.append("	</soapenv:Envelope>]]></con:request><con:credentials><con:authType>No Authorization</con:authType></con:credentials><con:jmsConfig JMSDeliveryMode=\"PERSISTENT\"/><con:jmsPropertyConfig/><con:wsaConfig mustUnderstand=\"NONE\" version=\"200508\" action=\"http://www.mercadona.es/schema/ED_GrupoPedidoExterno_Detalle/v1/0/0/ServiceClass/ConsultarCalendarioGPEPorGPE\"/><con:wsrmConfig version=\"1.2\"/></con:call>\n");
          
          y++;
          System.out.println(xx.toString());
		}
		
		}
		
		
}
