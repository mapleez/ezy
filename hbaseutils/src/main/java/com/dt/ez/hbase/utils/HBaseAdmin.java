package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
//import java.util.List;
//import java.util.ArrayList;

public class HBaseAdmin {

	private Admin admin;
	
	public HBaseAdmin (HBaseConfig conf) throws IOException {
		admin = HConn.getConn (conf).getAdmin ();
	}
	
    /**
     * Creates a table (if it doesn't exist already) with one or more column families
     * and made of one or more regions
     *
     * @param tableName name of table
     * @param families set of column families
     * @param splitKeys ordered list of keys that defines region splits
     */
    public void createTable (String tbName, String [] families, String [] splitKeys)
    	throws IOException {
    	TableBuilder table = new TableBuilder (tbName);
    	byte [][] splits = null;
    	if (null != splitKeys)
    		splits = Bytes.toByteArrays (splitKeys);	    	
    	for (String f: families)
    		table.addColumnFamily (f);
    	admin.createTable (table.getTableDesc (), splits);
    }

    /**
      * Creates a table (if it doesn't exist already) with one or more column families
      * and made of one or more regions
      *
      * @param tableName name of table
      * @param families set of column families
      */
    public void createTable (String tbName, String [] families) throws IOException {
    	createTable (tbName, families, null);
    }
      
    
    /**
     * Checks if table exists, and requires that it contains the desired column family
     *
     * @param tableName name of the table
     * @param family name of the column family
     *
     * @return true if table exists, false otherwise
     */
    public boolean tableExists (String tbName) throws IOException {
    	return tableExists (TableName.valueOf (tbName));
    }
      
    public boolean tableExists (TableName tbName) throws IOException {
    	return admin.tableExists (tbName); 
    }
    
    
    /**
      * Disables a table
      *
      * @param tableName name of table
      */
    public void disableTable (String tbName) throws IOException {
    	TableName tbnm = TableName.valueOf (tbName);
    	if (tableExists (tbnm))
    		disableTable (tbnm);
    }
    
    public void disableTable (TableName tbName) throws IOException {
    	if (tableExists (tbName)) admin.disableTable (tbName);
    }

    /**
      * Deletes a table
      *
      * @param tableName name of table
      */
    public void deleteTable (String tbName) throws IOException {
    	TableName table = TableName.valueOf (tbName);
//    	disableTable (table);
    	deleteTable (table);
    }
    
    public void deleteTable (TableName tbName) throws IOException {
//    	disableTable (tbName);
    	admin.deleteTable (tbName);
    }

    /**
      * Truncates a table
      *
      * @param tableName name of table
      * @param preserveSplits true if region splits should be preserved
      */
    public void truncateTable(String tbName, boolean preserveSplits)
    	throws IOException {
      TableName table = TableName.valueOf (tbName);
      if (tableExists (table))
        admin.truncateTable (table, preserveSplits);
    }
}


