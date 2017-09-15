package cl.script.mockfwk3;


public enum TdLocale {
	ES, DE, FR, GB, PT;

	public String value() {
		return name();
	}

	public static TdLocale fromValue(String v) {
		return valueOf(v);
	}
}
