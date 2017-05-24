package com.dt.ez.common.utils;

public class FlushAssistor {
	
	private long flushSize = 0;
	private long current = 0;
	
	
	public FlushAssistor (long flushSize) {
		this.flushSize = flushSize;
	}
	
	public boolean canFlush () {
		if (current >= flushSize) {
			reset ();
			return true;
		}
		return false;
	}
	
	public long append (long size) {
		return this.current += size;
	}
	
	@Deprecated
	public long backCounter (long size) {
		return this.current -= size;
	}
	
	private void reset () {
		this.current ^= 0;
	}

	public static void main(String[] args) {

	}

}
