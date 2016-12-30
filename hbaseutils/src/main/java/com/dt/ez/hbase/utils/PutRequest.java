package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class PutRequest {
	
	private List <Put> puts;
	private Put current;
	
	public PutRequest () {
		this (0);
	}
	
	public PutRequest (int rowNum) {
		puts = new ArrayList <Put> (rowNum);
	}

	/**
	 * When you calling this method. We will create a put entry.
	 * And the later calling will make side effect at this put tile
	 * next rowkey () calling.
	 * 
	 * @param rowKey
	 *   Row key specifying a record in HBase.
	 * @param offset
	 *   The offset of rowKey byte sequence.
	 * @param length
	 *   The length of rowKey byte sequence.
	 * @return
	 *   Return the current.
	 */
	public PutRequest rowkey (byte [] rowKey, int offset, int length) {
		Put p = new Put (rowKey, offset, length);
		puts.add (p);
		current = p;
		return this;
	}
	
    public PutRequest rowkey (byte [] rowKey) {
    	return rowkey (rowKey, 0, rowKey.length);
    }
    
    public PutRequest rowkey (int rowKey) {
        return this.rowkey (Bytes.toBytes (rowKey));
    }

    public PutRequest rowkey (long rowKey) {
        return this.rowkey (Bytes.toBytes (rowKey));
    }

    public PutRequest rowkey (String rowKey) {
        return this.rowkey (Bytes.toBytes (rowKey));
    }

    public PutRequest rowkey (double rowKey) {
        return rowkey (Bytes.toBytes (rowKey));
    }
    
    public PutRequest rowkey (float rowKey) {
        return rowkey (Bytes.toBytes (rowKey));
    }

    public PutRequest rowkey (short rowKey) {
        return rowkey (Bytes.toBytes (rowKey));
    }
    
    /**
     * Add a column <columnfamily, column, value>
     * 
     * @param cf
     *   column family
     * @param c
     *   column
     * @param value
     *   value
     * @return
     */
    public PutRequest addColumn (byte [] cf, byte [] c, byte [] value) {
    	if (null != current) current.addColumn (cf, c, value);
    	return this;
    }
	
    public PutRequest addColumn (byte [] cf, byte [] c, int value) {
    	return addColumn (cf, c, Bytes.toBytes (value));
    }
    
    public PutRequest addColumn (byte [] cf, byte [] c, long value) {
    	return addColumn (cf, c, Bytes.toBytes (value));
    }
    
    public PutRequest addColumn (byte [] cf, byte [] c, String value) {
    	return addColumn (cf, c, Bytes.toBytes (value));
    }
    
    public PutRequest addColumn (byte [] cf, byte [] c, short value) {
    	return addColumn (cf, c, Bytes.toBytes (value));
    }
    
    public PutRequest addColumn (byte [] cf, byte [] c, double value) {
    	return addColumn (cf, c, Bytes.toBytes (value));
    }
    
    public PutRequest addColumn (byte [] cf, byte [] c, float value) {
    	return addColumn (cf, c, Bytes.toBytes (value));
    }
    
    /**
     * Get put iterator.
     */
    public Iterator <Put> getIterator () {
    	return this.puts.iterator ();
    }
    
    public List <Put> getPuts () {
    	return this.puts;
    }
    
    /**
     * Return pushed data size in total.
     * 
     * @return
     */
    public long pushDataSize () {
    	long size = 0;
    	for (Put p : puts)
    		size += p.heapSize ();
    	return size;
    }
    
    public void reset () {
    	this.current = null;
    	puts.clear ();
    }
    
	/**
	 * Debug entry
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
