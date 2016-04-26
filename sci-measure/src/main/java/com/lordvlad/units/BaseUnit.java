package com.lordvlad.units;

import java.io.Serializable;
import java.util.function.Function;

import com.lordvlad.quantities.Quantity;

final class BaseUnit<Q extends Quantity> extends Unit<Q> implements Serializable{
	private static final long serialVersionUID = -5311168996363634717L;

	
	BaseUnit(String name, String...aliases) {
		super(name, aliases);
	}

	@Override
	Unit<Q> getBaseUnit() {
		return this;
	}

	@Override Function<Double, Double> toBaseUnit() {
		return noop;
	}

	@Override Function<Double, Double> fromBaseUnit() {
		return noop;
	}

	@Override
	protected Unit<Q> clone() throws CloneNotSupportedException {
		return new BaseUnit<Q>(this.symbol, this.aliases);
	}
}
