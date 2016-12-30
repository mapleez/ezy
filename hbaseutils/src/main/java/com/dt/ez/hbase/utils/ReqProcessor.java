/**
 * This is a Request Processor. For PutRequest, ScanRequest, etc.
 * It's underlay holds the connection pool implemented by hbase-client for
 * itself. And you'd better invoke close () after your application shutdown.
 * 
 * Maybe, I think, the class shell be a singleton.
 */
package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.HBaseConfiguration;

import org.apache.hadoop.conf.Configuration;

import java.io.IOException;
import java.io.Closeable;

import java.util.Random;

public class ReqProcessor implements Closeable {
	
	private Connection conn;

	public ReqProcessor () throws IOException {
		conn = ConnectionFactory.createConnection (HBaseConfiguration.create ());
	}
	
	public ReqProcessor (Configuration conf) throws IOException {
		conn = ConnectionFactory.createConnection (conf);
	}

	public ReqProcessor (String zkQuorum, String zkPort) throws IOException {
		conn = ConnectionFactory.createConnection (
			ConfUtil.getDefaultConf (zkQuorum, zkPort));
	}

	/**
	 * Put datas into HBase.
	 * @param tbName
	 * @param req
	 * @throws IOException
	 */
	public void put (TableName tbName, PutRequest req) throws IOException {
		Table tb = conn.getTable (tbName);
		tb.put (req.getPuts ());
		tb.close ();
	}

	public void put (String tbName, PutRequest req) throws IOException {
		put (TableName.valueOf (tbName), req);
	}

	public void put (byte [] tbName, PutRequest req) throws IOException {
		put (TableName.valueOf (tbName), req);
	}

	public void close () throws IOException {
		conn.close ();
	}
	
	
	/**
	 * Unit testing.
	 * @param args
	 */
	public static void main (String [] args) {
		final String prefix = "com.dt.ez";
		final Random rand = new Random ();
		final byte [] cf = Bytes.toBytes ("cf");
		final byte [] c1 = Bytes.toBytes ("7897432");
		final byte [] c2 = Bytes.toBytes ("7897432");
		final byte [] tb = Bytes.toBytes ("ezhfile");
		
		PutRequest req = new PutRequest ();
		
		for (int i = 20000; i < 200000; ++ i) {
			req.rowkey (prefix + i)
			.addColumn (cf, c1, rand.nextDouble ())
			.addColumn (cf, c2, rand.nextDouble ());
			System.out.println (i);
		}
		
		System.out.println ("Total data size : " + req.pushDataSize ());
		
		try {
			ReqProcessor runner = new ReqProcessor ("M1:2181,M2:2181", "2181");
			runner.put (tb, req);
			runner.close ();
		} catch (IOException ioex) {
			ioex.printStackTrace ();
		}
	}
	
}


