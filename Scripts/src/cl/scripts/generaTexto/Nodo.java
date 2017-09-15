package cl.scripts.generaTexto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Nodo {
	private Long codItem;
	private Long codItemPadre;
	private Long nivel;
	private Long nivelPadre;
	private Nodo padre;
	private Long formato;
	private List<Nodo> nodosHijos = new ArrayList<Nodo>();
	
	public void setNodoHijo(final Nodo hijo) {
		this.nodosHijos.add(hijo);
	}
	public List<Nodo> getHijos() {
		return this.nodosHijos;
	}
	
	public Nodo getPadre() {
		return this.padre;
	}
	
	public void setFormato(final Long formatoIn) {
		this.formato = formatoIn;
	}
	public Long getFormato() {
		return this.formato;
	}
	
	public Long getCodItemPadre() {
		return this.codItemPadre;
	}
	public Long getCodItem() {
		return this.codItem;
	}

	public Nodo(final Long codItem, final Long codItemPadre) {
		this.codItem = codItem;
		this.codItemPadre = codItemPadre;
	}

	public Nodo(final Long codItem, final Long codItemPadre, final Long nivel, final Long nivelPadre) {
		this.codItem = codItem;
		this.codItemPadre = codItemPadre;
		this.nivel = nivel;
		this.nivelPadre = nivelPadre;
	}
	
	public void generarCamino(final Map caminos) {
		// creo un buff
		final StringBuffer buff = new StringBuffer();
		// me muevo hasta el ultimo padre
		final Nodo ultimo = obtenerUltimoPadre(this);
		// recorro
		recorrerHijos(ultimo, buff, caminos);
	}
	
	@Override
	public String toString() {
		final StringBuffer str = new StringBuffer();
		str.append("nivel:" + this.nivel)
		.append(" nivel-p:" + this.nivelPadre)
		.append(" formato:" + this.formato)
		.append("|");
		return str.toString();
	};
	
	public void recorrerHijos(final Nodo nodo, final StringBuffer buff, final Map caminos) {
		// concateno
		buff.append(nodo.toString());
		
		// obtengo hijos
		final List<Nodo> hijos = nodo.getHijos();
		// si no hay hijos
		if (hijos.isEmpty()) {
			// he llegado a un final de camino
			caminos.put(buff.toString(), null);
			// limpiio el buffer
			return;
		}
		// si no sigo buscando
		for (final Nodo hijo : hijos) {
			recorrerHijos(hijo, new StringBuffer(buff.toString()), caminos);
		}
	}
	
	private Nodo obtenerUltimoPadre(final Nodo nodo) {
		final Nodo padre = nodo.padre;
		if (padre == null) {
			return nodo;
		}
		return obtenerUltimoPadre(padre);
	}
	
	public void setNiveles() {
		// si tienen el mismo id es el padre
		if (codItem.longValue() == codItemPadre.longValue()) {
			this.nivel = 1L;
			this.nivelPadre = 1L;
		} else {
			// si no, es un nivel más bajo
			final Long aux = padre.nivel;
			this.nivelPadre = aux;
			final Long aux2 = aux + 1L;
			this.nivel = aux2;
		}
	}

	public void setPadre(Nodo padre) {
		this.padre = padre;
		// creo la dependencia padre-hijo
		this.padre.setNodoHijo(this);
	}

	public Map toMap() {
		final Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("codItem", this.codItem);
		mapa.put("codItemPadre", this.codItemPadre);
		mapa.put("nivel", this.nivel);
		mapa.put("nivelPadre", this.nivelPadre);
		mapa.put("formato", this.formato);
		return mapa;
	}
}
