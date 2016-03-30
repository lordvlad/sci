package com.lordvlad.units;

import java.io.Serializable;

import com.lordvlad.quantities.Quantity;
import com.lordvlad.structures.Lambda;

final class BaseUnit<Q extends Quantity> extends Unit<Q> implements Serializable{
	private static final long serialVersionUID = -5311168996363634717L;
	final String name;

	BaseUnit(String name) {
		this.name = name;
	}

	@Override
	String getSymbol() {
		return name;
	}

	@Override
	Unit<Q> getBaseUnit() {
		return this;
	}

	@Override Lambda<Double, Double> toBaseUnit() {
		return noop;
	}

	@Override Lambda<Double, Double> fromBaseUnit() {
		return noop;
	}
}
