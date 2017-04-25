package com.dt.ez.common.utils;

import java.util.Stack;

public class SimpleTimer {
	
	private Stack <Long> timeStart;
	
	public SimpleTimer () {
		timeStart = new Stack <Long> ();
	}
	
	/**
	 * Reset last timer record;
	 */
	public long reset () {
		long start = System.currentTimeMillis ();
		if (! timeStart.isEmpty ()) {
			timeStart.push (start);
			return start;
		}
		long current = timeStart.pop ();
		current = start;
		timeStart.push (current);
		return current;
	}
		
	public void removeAll () {
		timeStart.clear ();
	}
	
	/**
	 * Remove last timer record if exists
	 */	
	public void remove () {
		if (! timeStart.isEmpty ())
			timeStart.pop ();
	}
	
	public long start () {
		long start = System.currentTimeMillis ();
		timeStart.push (start);
		return start;
	}
	
	public long stop () {
		long end = System.currentTimeMillis ();
		if (timeStart.isEmpty ())
			return 0;
		long start = timeStart.pop ();
		return (end - start);
	}
	
	public long [] stopAll () {
		long [] res = new long [this.timeStart.size ()];
		int i = 0;
		while (! timeStart.empty ()) {
			long start = timeStart.pop ();
			res [i ++] = System.currentTimeMillis () - start;
		}
		return res;
	}
	
	/**
	 * Return current spending time from last millisecond.
	 * @return
	 */
	public long click () {
		long current = System.currentTimeMillis ();
		if (timeStart.empty ()) {
			timeStart.push (current);
			return 0;
		}
		
		long top = timeStart.peek ();
		return (current - top);
	}
	
	
	public static void sleep (long seconds) {
		try {
			Thread.sleep (seconds);
		} catch (Exception ex) {
			ex.printStackTrace ();
		}
	}

	public static void main (String [] args) {
		long spend = 0, start = 0;
		SimpleTimer timer = new SimpleTimer ();
		
		start = timer.start ();
		sleep (1);
		spend = timer.click ();
		System.out.printf ("Spending %d.\n", spend);
		
		sleep (3 * 1000);
		spend = timer.click ();
		System.out.printf ("Spending %d.\n", spend);
		
		sleep (5 * 1000);
		spend = timer.click ();
		System.out.printf ("Spending %d.\n", spend);
		
		timer.removeAll ();
	}

}



