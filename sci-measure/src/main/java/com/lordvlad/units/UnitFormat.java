package com.lordvlad.units;

import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

public abstract class UnitFormat extends Format {
	
	private static final long serialVersionUID = 2642066919572053470L;

	@SafeVarargs
	public static UnitFormat getInstance(Class<? extends SystemOfUnits> ... systems) {
		return CompositeUnitFormat.getInstance(systems);
	}
	
	public static UnitFormat getInstance() {
		return CompositeUnitFormat.getInstance();
	}

	protected ParsePosition inc(ParsePosition pos) {
		pos.setIndex(pos.getIndex()+1);
		return pos;
	}
	
	public Unit<?> parse(String source) throws ParseException {
		return (Unit<?>) parseObject(source);
	}
	
	protected Unit<?> parse(String source, ParsePosition pos) {
		return (Unit<?>) parseObject(source, pos);
	}
}
