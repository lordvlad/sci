package com.lordvlad.math.numbers;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import com.lordvlad.utils.Symbols;

public class NumberWithErrorFormat extends Format {

	private static final String E_WRONG_TYPE = "Cannot format object of type %s";
	private static final long serialVersionUID = 1635184692695412257L;
	
	private static void back(ParsePosition pos) {
		pos.setIndex(pos.getIndex()-1);		
	}

	private static boolean findPlusMinus(String source, ParsePosition pos) {
		boolean e = false;
		Character c;
		while ((c = next(source, pos)) != null) {
			if (c==' ') continue;
			if (c==Symbols.PLUSMINUS) {e = true; continue;}
			back(pos);
			break;
		}
		return e;
	}
	
	public static Format getInstance() {
		return getInstance(Locale.US);
	}

	public static NumberWithErrorFormat getInstance(Locale locale) {
		return getInstance(DecimalFormat.getNumberInstance(locale));
	}

	public static NumberWithErrorFormat getInstance(NumberFormat numberInstance) {
		return new NumberWithErrorFormat(numberInstance);
	}

	private static Character next(String source, ParsePosition pos) {
		if (pos.getIndex() >= source.length()) return null;
		char c = source.charAt(pos.getIndex());
		pos.setIndex(pos.getIndex()+1);
		return c;
	}

	private NumberFormat nf;

	private NumberWithErrorFormat(NumberFormat numberInstance) {
		nf = numberInstance;
	}

	private void appendPlusMinus(StringBuffer toAppendTo) {
		toAppendTo.append(' ');
		toAppendTo.append(Symbols.PLUSMINUS);
		toAppendTo.append(' ');		
	}

	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		if (obj == null) return toAppendTo;
		if (!(obj instanceof NumberWithError))
			throw new IllegalArgumentException(String.format(E_WRONG_TYPE, obj.getClass()));
		
		NumberWithError<?> num = (NumberWithError<?>) obj;
		nf.format(num.val, toAppendTo, pos);
		appendPlusMinus(toAppendTo);
		nf.format(num.err, toAppendTo, pos);
		
		return toAppendTo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Number parseObject(String source, ParsePosition pos) {
		Number val = Number.of(nf.parseObject(source, pos));
		if(findPlusMinus(source, pos)) {
			Number err = Number.of(nf.parseObject(source, pos));
			return NumberWithError.of(val, err);
		} else {
			return val;
		}
	}

}
