package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/* A Singleton Connection. */
public class HConn {

	private static Connection conn = null;
	
	public static Connection getConn (HBaseConfig conf) 
		throws IOException {
		if (null != conn) return conn;
		return conn = ConnectionFactory.createConnection (conf.get ());
	}
}
