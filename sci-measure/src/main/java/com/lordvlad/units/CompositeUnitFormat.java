package com.lordvlad.units;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

class CompositeUnitFormat extends UnitFormat{

	private static final long serialVersionUID = -1972592388421822511L;
	private static final String E_FORMAT_NOT_A_UNIT = null;
	private static CompositeUnitFormat INSTANCE = null;
	private final NumberFormat num;
	private final SingleUnitFormat single;
	
	public static CompositeUnitFormat getInstance(SystemOfUnits system) {
		return new CompositeUnitFormat(system);
	}
	
	public static CompositeUnitFormat getInstance() {
		if (INSTANCE == null) INSTANCE = new CompositeUnitFormat(SI.getInstance());
		return INSTANCE;
	}
	
	CompositeUnitFormat() {
		this(SI.getInstance());
	}
	
	CompositeUnitFormat(SystemOfUnits system) {
		single = SingleUnitFormat.getInstance(system);
		num = NumberFormat.getInstance();
		num.setParseIntegerOnly(true);
	}
	
	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		return format(obj, toAppendTo, pos, false);
	}

	private StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos, boolean appendParens) {
		if (!(obj instanceof Unit)) throw new IllegalArgumentException(E_FORMAT_NOT_A_UNIT);
		if (obj instanceof ProductUnit) {
			if (appendParens) append("(", toAppendTo, pos);
			ProductUnit<?> prod = (ProductUnit<?>) obj;
			toAppendTo = format(prod.leftUnit, toAppendTo, pos, true);
			append("*", toAppendTo, pos);
			toAppendTo = format(prod.rightUnit, toAppendTo, pos, true);
			if (appendParens) append(")", toAppendTo, pos);
		} else if (obj instanceof PowerUnit) {
			PowerUnit<?> pow = (PowerUnit<?>) obj;
			toAppendTo = format(pow.unit, toAppendTo, pos, true);
			append("^", toAppendTo, pos);
			toAppendTo = num.format(pow.power, toAppendTo, pos);
		} else if (obj != Unit.ONE){
			toAppendTo = single.format(obj, toAppendTo, pos);
		}		
		
		return toAppendTo;
	}

	private void append(String what, StringBuffer toAppendTo, FieldPosition pos) {
		pos.setBeginIndex(toAppendTo.length());
		toAppendTo.append(what);
		pos.setEndIndex(toAppendTo.length());
	}

	@Override
	public final Object parseObject(String source, ParsePosition pos) {
		return parseObject(source, pos, 0);
	}

	private Object parseObject(String source, ParsePosition pos, int openParens) {
		Unit<?> u = Unit.ONE;
		Character c = null;
		
		while (pos.getIndex() < source.length()) {			
			c = source.charAt(pos.getIndex());
			
			if ('(' == c) return parseObject(source, inc(pos), openParens + 1);
			else if (')' == c) {inc(pos); return u;}
			else if (Character.isAlphabetic(c)) u = (Unit<?>) SingleUnitFormat.getInstance().parseObject(source, pos);
			else if ('*' == c) u = u.times((Unit<?>) parseObject(source, inc(pos)));
			else if ('/' == c) u = u.over((Unit<?>) parseObject(source, inc(pos)));
			else if ('^' == c) u = u.pow(num.parse(source, inc(pos)).intValue());			 
			else break;
			
			if (u == null) return null;
		}		
		
		return u;
	}
	
	
}
