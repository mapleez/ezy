package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Scan;

import java.io.IOException;

public class ScanBuilder {
	private int limit = -1;
	private long timestamp = HConstants.LATEST_TIMESTAMP;
	private int version = 1;
	private boolean reverse = false;
	
	private byte [] startRow;
	private byte [] stopRow;
	private Scan scan;
	
	public ScanBuilder () {
		scan = new Scan ();
	}
	
	public ScanBuilder limit (int num) {
		limit = num;
		return this;
	}
	
	public ScanBuilder reverse (boolean flag) {
		reverse = flag;
		return this;
	}
	
	public ScanBuilder startKey (byte [] key) {
		startRow = key;
		return this;
	}
	
	public ScanBuilder stopKey (byte [] key) {
		stopRow = key;
		return this;
	}
	
	public ScanBuilder keyRange (byte [] start, byte [] stop) {
		return startKey (start).stopKey (stop);
	}
	
	public Scan build () throws IOException {
		scan.setCaching (limit);
		if (null != startRow) scan.setStartRow (startRow);
		if (null != stopRow) scan.setStartRow (stopRow);
		scan.setTimeStamp (timestamp);
		scan.setMaxVersions (version);
		scan.setReversed (reverse);
		return scan;
	}
}



