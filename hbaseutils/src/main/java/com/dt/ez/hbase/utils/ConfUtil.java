/**
 * A set of static function to help build
 * org.apache.hadoop.conf.Configuration entry.
 * 
 * Author : ez
 * Date : 2016/12/29
 */
package com.dt.ez.hbase.utils;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;

public class ConfUtil {

	/**
	 * The simplest way to get a Configuration entry.
	 */
	public static Configuration getDefaultConf (String zk, String port) {
		Configuration conf = HBaseConfiguration.create ();
		conf.set (HConstants.ZOOKEEPER_QUORUM, zk);
		conf.set (HConstants.ZOOKEEPER_CLIENT_PORT, port);
		return conf;
	}
	
}
