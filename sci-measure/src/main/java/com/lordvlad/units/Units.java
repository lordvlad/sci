package com.lordvlad.units;

import java.text.ParseException;

/**
 * A Facade for simple access to the units package. 
 * 
 * @author reusch
 */
public class Units {
	public static UnitFormat getFormat() {
		return UnitFormat.getInstance();
	}
	
	public static Unit<?> parse(String s) throws ParseException {
		return (Unit<?>) getFormat().parseObject(s);
	}
	
	public static String format(Unit<?> u) {
		return getFormat().format(u);
	}
}
