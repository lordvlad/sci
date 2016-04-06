package com.lordvlad.units;

import java.text.Format;
import java.text.ParsePosition;

public abstract class UnitFormat extends Format {
	
	private static final long serialVersionUID = 2642066919572053470L;

	public static UnitFormat getInstance(SystemOfUnits system) {
		return CompositeUnitFormat.getInstance(system);
	}
	
	public static UnitFormat getInstance() {
		return CompositeUnitFormat.getInstance();
	}

	protected ParsePosition inc(ParsePosition pos) {
		pos.setIndex(pos.getIndex()+1);
		return pos;
	}
}
