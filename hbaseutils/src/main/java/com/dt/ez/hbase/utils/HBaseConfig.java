package com.dt.ez.hbase.utils;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;

import java.util.Map;

public class HBaseConfig {
	private final Configuration conf;
	
	public HBaseConfig (String zkHost, String zkPort) {
		conf = HBaseConfiguration.create ();
		conf.set (HConstants.ZOOKEEPER_QUORUM, zkHost);
		conf.set (HConstants.ZOOKEEPER_CLIENT_PORT, zkPort);
	}
	
	public HBaseConfig set (Map <String, String> props) {
		for (Map.Entry <String, String> e: props.entrySet ())
			conf.set (e.getKey (), e.getValue ());
		return this;
	}
	
	public HBaseConfig set (String key, String value) {
		conf.set (key, value);
		return this;
	}
	
	public Configuration get () {
		return this.conf;
	}
	
	/*
	public Class <? extends HBaseConfiguration> getConfClass () {
		return HBaseConfiguration.class;
	}
	*/
}
