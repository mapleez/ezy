package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.Put;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.Closeable;

import com.dt.ez.structs.Pair;


public class PutProcessor implements Closeable {
	
	private Table table;
	
	public PutProcessor (TableBuilder tbBuilder)
		throws IOException {
		table = tbBuilder.getTable ();
	}
	
	/**
	 * Performs actual Put operation for the specified record in HBase.
	 * @param record (colName -> data)
	 * @param colFamily
	 * @param rowKey
	 * @return a Put command.
	 */
//	public void putRow (String rowKey, String colFamily, List <Pair <String, String>> records) 
//		throws IOException {
////		PutBuilder put = new PutBuilder ()
//			.columnFamily (colFamily)
//			.rowkey (rowKey);
//		for (Pair <String, String> e : records)
//			put.record (e.getFirst (), e.getSecond ());
//		this.table.put (put.build ());
//	}
//	
//	public void putRow (PutBuilder putBuilder) throws IOException {
//		this.table.put (putBuilder.build ());
//	}
	
//	public void putRows (Iterable <PutBuilder> puts) throws IOException {
//		List <Put> ps = new ArrayList <Put> ();
//		for (PutBuilder p : puts)
//			ps.add (p.build ());
//		table.put (ps);
//	}
    
	public void close () throws IOException {
		this.table.close ();
	}

}

