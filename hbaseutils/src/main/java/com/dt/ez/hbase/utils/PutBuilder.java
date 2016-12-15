package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.Writables;
import org.apache.hadoop.io.Writable;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.List;

/**
 * A Builder Pattern helper class to allow expressive Put modifications.
 */
public class PutBuilder {

    private byte [] currentColumnFamily;
    private byte [] currentRowKey;
    private long currentTimeStamp = HConstants.LATEST_TIMESTAMP;
    private Map <byte [] ,byte []> datas;
    
    public PutBuilder columnFamily (String columnFamily) {
        return columnFamily (Bytes.toBytes (columnFamily));
    }

    public PutBuilder columnFamily (byte [] columnFamily) {
        this.currentColumnFamily = columnFamily;
        this.datas = new HashMap <byte [], byte []> ();
        return this;
    }

    public PutBuilder rowkey (Writable writeableRowKey) {
        try {
            return rowkey (Writables.getBytes (writeableRowKey));
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
    }

    public PutBuilder rowkey (byte [] rowKey) {
        this.currentRowKey = rowKey;
        return this;
    }
    
    public PutBuilder rowkey (int rowKey) {
        this.currentRowKey = Bytes.toBytes (rowKey);
        return this;
    }

    public PutBuilder rowkey (long rowKey) {
        this.currentRowKey = Bytes.toBytes (rowKey);
        return this;
    }

    public PutBuilder rowkey (String rowKey) {
        this.currentRowKey = Bytes.toBytes (rowKey);
        return this;
    }

    public PutBuilder rowkey (double rowKey) {
        this.currentRowKey = Bytes.toBytes (rowKey);
        return this;
    }

    public PutBuilder rowkey (ByteBuffer rowKey) {
        this.currentRowKey = Bytes.toBytes (rowKey);
        return this;
    }
    
    public PutBuilder timestamp (long timeStamp) {
        this.currentTimeStamp = timeStamp;
        return this;
    }

    public PutBuilder record (String columnName, byte [] rowValue) {
        return record (Bytes.toBytes (columnName), rowValue);
    }

    public PutBuilder record (String columnName, int rowValue) {
        return record (columnName, Bytes.toBytes (rowValue));
    }
    
    public PutBuilder record (String columnName, short rowValue) {
    	return record (columnName, Bytes.toBytes (rowValue));
    }

    public PutBuilder record (String columnName, long rowValue) {
        return record (columnName, Bytes.toBytes (rowValue));
    }

    public PutBuilder record (String columnName, String columnValue) {
        return record (columnName, Bytes.toBytes (columnValue));
    }

    public PutBuilder record (String columnName, Writable rowValue)
    	throws IOException {
        return record (Bytes.toBytes (columnName), Writables.getBytes (rowValue));
    }

    public PutBuilder record (byte [] columnName, byte [] rowValue) {
    	this.datas.put (columnName, rowValue);
    	return this;
    }
    
    public Put build () {
    	if (datas.size () <= 0) 
    		return null;
    	Put put = new Put (currentRowKey);
    	for (Map.Entry <byte [], byte []> e : this.datas.entrySet())
    		put.addColumn (this.currentColumnFamily, e.getKey (), e.getValue ());
    	return put;
    }

    public PutBuilder reset () {
        this.currentColumnFamily = null;
        this.currentRowKey = null;
        this.currentTimeStamp = HConstants.LATEST_TIMESTAMP;
        this.datas.clear ();
        return this;
    }

}




