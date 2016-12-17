package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.io.Closeable;
//import java.util.List;
//import java.util.ArrayList;

public class HBaseAdmin implements Closeable {

	private Admin admin;
	
	public HBaseAdmin (HBaseConfig conf) throws IOException {
		admin = HConn.getConn (conf).getAdmin ();
	}
	
    /**
     * Creates a table (if it doesn't exist already) with one or more column families
     * and made of one or more regions. Ensure the table to created does not exist.
     *
     * @param tableName name of table
     * @param families set of column families.
     * @param splitKeys ordered list of keys that defines region splits. If the splitkeys
     *  is null, we will not presplit the creating table.
     * @throws IOException
     */
    public void createTable (String tbName, String [] families, String [] splitKeys)
    	throws IOException {
    	TableBuilder table = new TableBuilder (tbName);
    	for (String f: families)
    		table.addColumnFamily (f);
    	if (null != splitKeys) {
        	byte [][] splits = Bytes.toByteArrays (splitKeys);
        	admin.createTable (table.getTableDesc (), splits);
    	} else
    		admin.createTable (table.getTableDesc ());
    }

    /**
      * Creates a table (if it doesn't exist already) with one or more column families
      * and made of one or more regions. Ensure the table to created does not exist.
      *
      * @param tableName name of table
      * @param families set of column families
      * @throws IOException
      */
    public void createTable (String tbName, String [] families) throws IOException {
    	createTable (tbName, families, null);
    }
      
    
    /**
     * Checks if table exists, and requires that it contains the desired column family
     *
     * @param tableName name of the table
     * @param family name of the column family
     * @return true if table exists, false otherwise
     * @throws IOException
     */
    public boolean tableExists (String tbName) throws IOException {
    	return tableExists (TableName.valueOf (tbName));
    }
      
    public boolean tableExists (TableName tbName) throws IOException {
    	return admin.tableExists (tbName); 
    }
    
    
    /**
      * Disables a table, If the special table dont 
      * exist, nothing will be done.
      *
      * @param tableName name of table
      * @throws IOException
      */
    public void disableTable (String tbName) throws IOException {
    	TableName tbnm = TableName.valueOf (tbName);
    	if (admin.isTableEnabled (tbnm))
    		admin.disableTable (tbnm);
    }
    
    public void disableTable (TableName tbName) throws IOException {
    	if (admin.isTableEnabled (tbName))
    		admin.disableTable (tbName);
    }

    /**
      * Deletes a table, If the special table dont 
      * exist, nothing will be done.
      *
      * @param tableName name of table
      * @throws IOException
      */
    public void deleteTable (String tbName) throws IOException {
    	TableName table = TableName.valueOf (tbName);
    	if (tableExists (tbName)) {
    		disableTable (table);
    		admin.deleteTable (table);
    	}
    }
    
    public void deleteTable (TableName tbName) throws IOException {
    	if (tableExists (tbName)) {
	    	disableTable (tbName);
	    	admin.deleteTable (tbName);
    	}
    }

    /**
      * Truncates a table, If the special table dont 
      * exist, nothing will be done.
      *
      * @param tableName name of table
      * @param preserveSplits true if region splits should be preserved
      * @throws IOException
      */
    public void truncateTable(String tbName, boolean preserveSplits)
    	throws IOException {
      TableName table = TableName.valueOf (tbName);
      if (tableExists (table))
        admin.truncateTable (table, preserveSplits);
    }
    
    public void close () throws IOException {
    	this.admin.close ();
    }
    
    /* For debug testing. */
    public static void main (String [] args) {
    	HBaseConfig conf = new HBaseConfig ("master1,master2,slave1", "2181");
    	HBaseAdmin admin = null;
    	String [] families = new String [] {
    		"cf1", "cf2"
    	};
    	
    	String [] splits = new String [] {
    		"1", "2", "3", "4"
    	};
    	
    	String tableName = "test:hbase_admin_debug";
    	String tableName1 = "test:hbase_admin_debug1";
    	
    	try {
    		admin = new HBaseAdmin (conf);
//    		admin.createTable (tableName, families, splits);
    		admin.deleteTable (tableName);
    		admin.deleteTable (tableName1);
//    		if (admin.tableExists (tableName1) && admin.tableExists (tableName))
//    			System.out.println ("Error");
//    		else
//    			System.out.println ("Ok");
    		admin.close ();
    	} catch (IOException ioex) {
    		ioex.printStackTrace ();
    	}
    	    	
    }
    
}


