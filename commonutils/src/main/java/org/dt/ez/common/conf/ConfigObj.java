package org.dt.ez.common.conf;

public class ConfigObj {
	
	private final String key;
	
	public Object defValue;
	public Object usrValue;
	
	public ConfigObj (String key, Object def) {
		defValue = def;
		this.key = key;
	}
	
	public String getKey () {
		return key;
	}

}
