package com.lordvlad.amounts;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Locale;

import com.lordvlad.math.numbers.Number;
import com.lordvlad.math.numbers.NumberWithErrorFormat;
import com.lordvlad.units.Unit;
import com.lordvlad.units.UnitFormat;

public class AmountFormat extends Format{
	private static final long serialVersionUID = 1948513806700816868L;
	private static final String E_NOT_AMOUNT = "Cannot format object of type %s";
	private static AmountFormat INSTANCE;
	private UnitFormat uf;
	private Format nf;
	
	private AmountFormat(Locale locale) {
		this(UnitFormat.getInstance(), NumberWithErrorFormat.getInstance(locale));
	}
	
	private AmountFormat(UnitFormat unitInstance, Format numberInstance) {
		this.nf = numberInstance;
		this.uf = unitInstance;
	}

	public AmountFormat() {
		this(Locale.US);
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		if (obj == null) return toAppendTo;
		if (!(obj instanceof Amount)) {
			throw new IllegalArgumentException(String.format(E_NOT_AMOUNT, obj.getClass()));
		}
		Amount<?, ?> amount = (Amount<?, ?>) obj;
		nf.format(amount.getAmount(), toAppendTo, pos);
		appendSpace(toAppendTo);
		uf.format(amount.getUnit(), toAppendTo, pos);
		return toAppendTo;
	}
	
	/**
	 * Appends a space character
	 * 
	 * @param toAppendTo
	 */
	private static void appendSpace(StringBuffer toAppendTo) {
		toAppendTo.append(' ');		
	}
	
	@Override
	public Object parseObject(final String source, final ParsePosition pos) {
		final Number<?> n = (Number<?>) Number.of(nf.parseObject(source, pos));
		skipSpace(source, pos);
		final Unit<?> u = (Unit<?>) uf.parseObject(source, pos);
		return Amount.of(n, u);
	}
	
	/**
	 * If the {@code source} contains a space (' ') character at {@code pos},
	 * let the pointer at {@code pos} jump one place forward. 
	 * 
	 * @param source
	 * @param pos
	 */
	private static void skipSpace(final String source, final ParsePosition pos) {
		if (source.charAt(pos.getIndex()) == ' ') {
			pos.setIndex(pos.getIndex() +1);		
		}
	}
	
	/**
	 * @return the singelton instance
	 */
	public static AmountFormat getInstance () {
		if (INSTANCE == null) INSTANCE = new AmountFormat();
		return INSTANCE;
	}
}
