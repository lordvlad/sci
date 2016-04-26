package com.lordvlad.measurements;

import java.util.ArrayList;

import com.lordvlad.amounts.Amount;
import com.lordvlad.quantities.Quantity;
import com.lordvlad.quantities.Time;
import com.lordvlad.units.Unit;

public class SeriesOfMeasurement< Q extends Quantity> {
	
	ArrayList<Number> values;
	ArrayList<Number> times;
	Unit<Q> valueUnit;
	Unit<Time> timeUnit;
	
	public Amount< Q> measurementAt(Amount< Time> time) {
		Number tx = time.to(timeUnit).getAmount();
		int i = 0;
		for (Number t:times) {
			if (t.equals(tx)) {
				return Amount.of(values.get(i), valueUnit);
			}
			i++;
		}
		return null;
	}
}
