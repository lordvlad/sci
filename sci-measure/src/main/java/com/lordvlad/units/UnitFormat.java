package com.lordvlad.units;

import java.text.Format;
import java.text.ParsePosition;

public abstract class UnitFormat extends Format {
	
	private static final long serialVersionUID = 2642066919572053470L;
	private static UnitFormat INSTANCE = null;

	public static UnitFormat getInstance() {
		if (INSTANCE == null) INSTANCE = new CompositeUnitFormat();
		return INSTANCE;
	}

	protected ParsePosition inc(ParsePosition pos) {
		pos.setIndex(pos.getIndex()+1);
		return pos;
	}
}
