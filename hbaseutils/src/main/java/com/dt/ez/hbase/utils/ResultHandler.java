package com.dt.ez.hbase.utils;

import org.apache.hadoop.hbase.client.ResultScanner;

public interface ResultHandler <T> {
	T handle (ResultScanner res);
	
	T handle (ResultScanner res, Object param);
}
