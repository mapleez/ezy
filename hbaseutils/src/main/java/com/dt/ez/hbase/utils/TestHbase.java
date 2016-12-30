package com.dt.ez.hbase.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;

import org.apache.hadoop.hbase.TableName;


public class TestHbase {
	public static void main (String [] args) {
		
		/*
		HBaseConfig hbaseconf = new HBaseConfig ("M1:2181,M2:2181", "2181");
		Connection conn = null;
		*/
		
		/*
		PutBuilder putB = new PutBuilder ();
		List <Put> puts = new ArrayList <Put> ();
		
		try {
			conn = ConnectionFactory.createConnection (hbaseconf.get ());
			Table tb = conn.getTable (TableName.valueOf ("ezhfile"));
			
			for (int i = 40000; i < 60000; ++ i) {
			
				putB.columnFamily ("cf").rowkey (Integer.toString (i))
				.record ("v1", Double.toString (i + 0.78))
				.rowkey (Integer.toString (i))
				.record ("v2", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v3", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v4", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v5", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v6", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v7", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v8", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v9", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v10", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v11", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v12", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v13", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v14", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v15", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v16", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v17", Double.toString (i + 0.987))
				.rowkey (Integer.toString (i))
				.record ("v18", Double.toString (i + 0.987));
				tb.put (putB.build ());
				
//				puts.add (new PutBuilder ().columnFamily ("cf").rowkey (Integer.toString (i))
//						.record ("v1", Double.toString (i + 0.78))
//						.rowkey (Integer.toString (i))
//						.record ("v2", Double.toString (i + 0.987)).build ());
				putB.reset ();
				System.out.println (i);
			}
			conn.close ();
		} catch (IOException ioex) {
			ioex.printStackTrace ();
		}
		*/
		
		/*
		HBaseAdmin admin = null;
		TableBuilder tb = new TableBuilder ("ezhfile");
		PutProcessor proc = null;
		
		try {
			admin = new HBaseAdmin (hbaseconf);
//			admin.createTable ("ezhfile", new String [] {"cf"});
			proc = new PutProcessor (tb);
		} catch (IOException ioex) {
			ioex.printStackTrace ();
			return;
		}
		
		PutBuilder put = new PutBuilder ();
		List <PutBuilder> puts = new ArrayList <PutBuilder> (100);
		for (int i = 0; i < 100; ++ i) {
			puts.add (put.columnFamily ("cf").rowkey (Integer.toString (i)).record ("v1", "0.324")
				.columnFamily ("cf").rowkey (Integer.toString (i)).record ("v2", "0.987"));
		}
		
		try {
			proc.putRows (puts);
			proc.close ();
		} catch (IOException ioex) {
			ioex.printStackTrace ();
		}
		
		*/
	}
}
