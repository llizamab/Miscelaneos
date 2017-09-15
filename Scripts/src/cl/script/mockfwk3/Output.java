package cl.script.mockfwk3;

import java.util.ArrayList;
import java.util.List;

public class Output {

	protected String codError;
	
	protected String desError;
	
	protected Cosa cosa;
	
	/**
	 * @return the cosa
	 */
	public Cosa getCosa() {
		return cosa;
	}

	/**
	 * @param cosa the cosa to set
	 */
	public void setCosa(Cosa cosa) {
		this.cosa = cosa;
	}

	protected List<Cosa> cosas;

	/**
	 * @return the codError
	 */
	public String getCodError() {
		return codError;
	}

	/**
	 * @param codError the codError to set
	 */
	public void setCodError(String codError) {
		this.codError = codError;
	}

	/**
	 * @return the desError
	 */
	public String getDesError() {
		return desError;
	}

	/**
	 * @param desError the desError to set
	 */
	public void setDesError(String desError) {
		this.desError = desError;
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
