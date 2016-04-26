package com.lordvlad.measurements;

import com.lordvlad.amounts.Amount;
import com.lordvlad.quantities.Quantity;
import com.lordvlad.quantities.Time;
import com.lordvlad.structures.Pair;

public class Measurement<Q extends Quantity> extends Pair<Amount< Q>, Amount< Time>> {

	public Measurement(Amount< Q> a, Amount< Time> b) {
		super(a, b);
	}
	
	Amount< Q> getAmount(){return getA();}
	Amount< Time> getTime(){return getB();}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4772022227328152568L;

}
