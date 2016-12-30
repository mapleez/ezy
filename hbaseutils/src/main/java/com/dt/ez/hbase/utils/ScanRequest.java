package com.dt.ez.hbase.utils;

import java.io.IOException;

import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Scan;

public class ScanRequest {

	private int limit = -1;
	private long timestamp = HConstants.LATEST_TIMESTAMP;
	private int version = 1;
	private boolean reverse = false;
	
	private byte [] startRow;
	private byte [] stopRow;
	private Scan scan;
	
	public ScanRequest () {
		scan = new Scan ();
	}
	
	public ScanRequest limit (int num) {
		limit = num;
		return this;
	}
	
	public ScanRequest reverse (boolean flag) {
		reverse = flag;
		return this;
	}
	
	public ScanRequest startKey (byte [] key) {
		startRow = key;
		return this;
	}
	
	public ScanRequest stopKey (byte [] key) {
		stopRow = key;
		return this;
	}
	
	public ScanRequest keyRange (byte [] start, byte [] stop) {
		return startKey (start).stopKey (stop);
	}
	
	public Scan getScan () throws IOException {
		scan.setCaching (limit);
		if (null != startRow) scan.setStartRow (startRow);
		if (null != stopRow) scan.setStartRow (stopRow);
		scan.setTimeStamp (timestamp);
		scan.setMaxVersions (version);
		scan.setReversed (reverse);
		return scan;
	}
	
}


