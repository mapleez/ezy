package org.dt.ez.common.conf;

import java.util.Properties;

public class ConfigManager {
	
	private Properties core;
	
	public ConfigManager (String file, ConfigObj [] e) {
		core = new Properties ();
		
		DefValueLoader.loadFromConfDefault (e, this);
	}
	
//	public ConfigManager () {
//		core = new Properties ();
//	}

	public String getString (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (String) v;
	}
	
	public int getInt (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (Integer) v;
	}
	
	public boolean getBool (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (Boolean) v;
	}
	
	public float getFloat (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (Float) v;
	}
	
	public double getDouble (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (Double) v;
	}
	
	public Properties getProperties (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (Properties) v;
	}
	
	public Object getObj (String key) {
		Object o = core.get (key);
		if (o == null) return o;
		
		ConfigObj e = (ConfigObj) o;
		return e.usrValue == null ? 
			e.defValue : e.usrValue;
	}
	
	
	/* ------------------------------ */
	public String getDefString (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (String) v;
	}
	
	public int getDefInt (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (Integer) v;
	}
	
	public boolean getDefBool (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (Boolean) v;
	}
	
	public float getDefFloat (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (Float) v;
	}
	
	public double getDefDouble (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (Double) v;
	}
	
	public Properties getDefProperties (String key) throws Exception {
		Object v = getDefObj (key);
		if (v == null) throw new Exception ();
		
		return (Properties) v;
	}
	
	public Object getDefObj (String key) {
		Object o = core.get (key);
		if (o == null) return o;
		
		ConfigObj e = (ConfigObj) o;
		return e.defValue;
	}
	
	private void setConf (ConfigObj e) {
		core.put (e.getKey (), e);
	}
	
	static class DefValueLoader {
		
		public static void loadFromConfDefault (ConfigObj [] defaults, ConfigManager conf) {
			for (ConfigObj e : defaults)
				conf.setConf (e);
		}
	}

	
	public static void main (String [] args) throws Exception {
		ConfigObj [] defaults = {
			new ConfigObj (ConfigKeys.INPUT_BUFFLEN, ConfigDefaultValue.INPUT_BUFFLEN),
			new ConfigObj (ConfigKeys.OUTPUT_BUFFLEN, ConfigDefaultValue.OUTPUT_BUFFLEN),
			new ConfigObj (ConfigKeys.LISTENER_ADDR, ConfigDefaultValue.LISTENER_ADDR),
			new ConfigObj (ConfigKeys.LISTENER_PORT, ConfigDefaultValue.LISTENER_PORT),
			new ConfigObj (ConfigKeys.MEMORY_OVERFLOW_RATE, ConfigDefaultValue.MEMORY_OVERFLOW_RATE),
			new ConfigObj (ConfigKeys.MYSQL_SERVER_IP, ConfigDefaultValue.MYSQL_SERVER_IP),
			new ConfigObj (ConfigKeys.MYSQL_SERVER_PORT, ConfigDefaultValue.MYSQL_SERVER_PORT),
			new ConfigObj (ConfigKeys.START_AFTEROSBOOT, ConfigDefaultValue.START_AFTEROSBOOT),
		};
		
		ConfigManager conf = new ConfigManager ("/home/hadoop/Desktop/config.conf", defaults);
		boolean start_afterosboot = conf.getBool (ConfigKeys.START_AFTEROSBOOT);
		
		System.out.println (start_afterosboot);
	}
	

}
