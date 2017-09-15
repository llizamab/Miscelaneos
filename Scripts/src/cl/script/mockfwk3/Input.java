package cl.script.mockfwk3;

import java.util.ArrayList;
import java.util.List;

public class Input {

	protected TdLocale local;
	
	/**
	 * @return the local
	 */
	public TdLocale getLocal() {
		return local;
	}

	/**
	 * @param local the local to set
	 */
	public void setLocal(TdLocale local) {
		this.local = local;
	}

	protected String campo;
	
	protected Long codigo;
	
	protected List<Cosa> cosas;

	/**
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the cosas
	 */
	public List<Cosa> getCosas() {
		if (this.cosas == null) {
			this.cosas = new ArrayList<Cosa>();
		}
		return cosas;
	}

	/**
	 * @param cosas the cosas to set
	 */
	public void setCosas(List<Cosa> cosas) {
		this.cosas = cosas;
	}
	
	
}
