package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class TableBuilder {

	private String tbName;
	private int maxVersion = 1;
	private HBaseConfig conf;
	
	private List <HColumnDescriptor> cList;
	
	public TableBuilder (String name) {
		this.tbName = name;
	}
	
	public TableBuilder (String name, HBaseConfig conf) {
		this (name);
		this.conf = conf;
	}
	
	public TableBuilder setName (String name) {
		tbName = name;
		return this;
	}
	
	public TableBuilder setVersion (int version) {
		this.maxVersion = version;
		return this;
	}
	
	private void _checkColumnList () {
		if (null == cList) cList = new ArrayList <HColumnDescriptor> ();
	}
	
	public TableBuilder addColumnFamily (String cf) {
		_checkColumnList ();
		cList.add (new HColumnDescriptor (cf));
		return this;
	}
	
	public TableBuilder addColumnFamily (byte [] cf) {
		return addColumnFamily (Bytes.toString (cf));
	}
	
	public TableBuilder addColumnFamily (HColumnDescriptor cdesc) {
		_checkColumnList ();
		cList.add (cdesc);
		return this;
	}
	
	public TableBuilder setConf (HBaseConfig conf) {
		this.conf = conf;
		return this;
	}
	
	public Table getTable () throws IOException {
		if (null == conf) return null;
		return HConn.getConn (conf)
			.getTable (TableName.valueOf (tbName));
	}
	
	public HTableDescriptor getTableDesc () {
		HTableDescriptor td = new HTableDescriptor (TableName.valueOf (this.tbName));
		for (HColumnDescriptor cp: cList)
			td.addFamily (cp);
		// TODO... Other properties to add into HColumnDescriptor;
		return td;
	}
	
	@Override
	public String toString () {
		return "[" + tbName + " version " + maxVersion + "]"; 
	}

}
