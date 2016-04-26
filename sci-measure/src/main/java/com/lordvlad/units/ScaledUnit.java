package com.lordvlad.units;

import java.io.Serializable;
import java.util.function.Function;

import com.lordvlad.quantities.Quantity;

class ScaledUnit<Q extends Quantity> extends Unit<Q> implements Serializable {
	private static final long serialVersionUID = -5488981475737797350L;

	final Unit<Q> baseUnit;
	final double scale;

	private Function<Double, Double> toBaseUnitLambda;
	private Function<Double, Double> fromBaseUnitLambda;

	static <T extends Quantity> Unit<T> of(Unit<T> baseUnit, Scale scale) {
		if (baseUnit instanceof ScaledUnit) {
			ScaledUnit<T> base = (ScaledUnit<T>) baseUnit;
			Scale s = MetricScale.of(base.scale * scale.scaling);
			return s == null ? of(base.baseUnit, base.scale * scale.scaling) : of(base.baseUnit, s);
		}
		return new ScaledUnit<T>(baseUnit, scale);
	}

	static <T extends Quantity> Unit<T> of(Unit<T> baseUnit, double scale) {
		if (baseUnit instanceof ScaledUnit) {
			ScaledUnit<T> base = (ScaledUnit<T>) baseUnit;
			return of(base.baseUnit, base.scale * scale);
		}
		return new ScaledUnit<T>(baseUnit, scale);
	}

	private ScaledUnit(Unit<Q> baseUnit, Scale scale) {
		super(scale.prefix + baseUnit.toString());
		this.baseUnit = baseUnit;
		this.scale = scale.scaling;
	}

	private ScaledUnit(Unit<Q> baseUnit, double scale) {
		super(scale + " * " + baseUnit.toString());
		this.baseUnit = baseUnit;
		this.scale = scale;
	}

	@Override
	Unit<Q> getBaseUnit() {
		return baseUnit;
	}

	@Override
	Function<Double, Double> toBaseUnit() {
		if (toBaseUnitLambda == null)
			toBaseUnitLambda = new Function<Double, Double>() {

				public Double apply(Double a) {
					return a * scale;
				}

			};
		return toBaseUnitLambda;
	}

	@Override
	Function<Double, Double> fromBaseUnit() {
		if (fromBaseUnitLambda == null)
			fromBaseUnitLambda = new Function<Double, Double>() {

				public Double apply(Double a) {
					return a / scale;
				}

			};
		return fromBaseUnitLambda;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseUnit == null) ? 0 : baseUnit.hashCode());
		long temp;
		temp = Double.doubleToLongBits(scale);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScaledUnit<?> other = (ScaledUnit<?>) obj;
		if (baseUnit == null) {
			if (other.baseUnit != null)
				return false;
		} else if (!baseUnit.equals(other.baseUnit))
			return false;
		if (Double.doubleToLongBits(scale) != Double.doubleToLongBits(other.scale))
			return false;
		return true;
	}

	@Override
	protected Unit<Q> clone() throws CloneNotSupportedException {
		ScaledUnit<Q> u = new ScaledUnit<>(baseUnit, scale);
		u.symbol = symbol;
		return u;
	}

}
