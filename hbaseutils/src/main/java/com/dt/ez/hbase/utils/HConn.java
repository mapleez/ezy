package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.io.Closeable;

/* A Singleton Connection. */
public class HConn implements Closeable {

	private static Connection conn = null;
	
	public static Connection getConn (HBaseConfig conf) 
		throws IOException {
		if (null != conn) return conn;
		return conn = ConnectionFactory.createConnection (conf.get ());
	}
	
	public void close () {
		try {
			conn.close ();
		} catch (IOException ioex) {
			// TODO ignore...
		}
	}
}
