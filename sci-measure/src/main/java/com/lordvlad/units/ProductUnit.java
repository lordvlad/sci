package com.lordvlad.units;

import java.io.Serializable;

import com.lordvlad.quantities.Quantity;
import com.lordvlad.structures.Lambda;

final class ProductUnit<Q extends Quantity> extends CompositeUnit<Q> implements Serializable{
	private static final long serialVersionUID = 2976784836084464012L;
	Unit<?> leftUnit;
	Unit<?> rightUnit;
	private Lambda<Double, Double> toBaseUnitLambda;
	private Lambda<Double, Double> fromBaseUnitLambda;

	ProductUnit(Unit<?> a, Unit<?> b) {
		this.leftUnit = a;
		this.rightUnit = b;
	}

	@Override
	String getSymbol() {
		return (leftUnit instanceof CompositeUnit) ? "(" + leftUnit + ")" : leftUnit + "*"
				+ ((rightUnit instanceof CompositeUnit) ? "(" + rightUnit + ")" : rightUnit);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Unit<?> pow(int num) {
		return new ProductUnit(this.leftUnit.pow(num),	this.rightUnit.pow(num));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((leftUnit == null) ? 0 : leftUnit.hashCode());
		result = prime * result + ((rightUnit == null) ? 0 : rightUnit.hashCode());
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
		ProductUnit<?> other = (ProductUnit<?>) obj;
		if (leftUnit == null) {
			if (other.leftUnit != null)
				return false;
		} else if (!leftUnit.equals(other.leftUnit))
			return false;
		if (rightUnit == null) {
			if (other.rightUnit != null)
				return false;
		} else if (!rightUnit.equals(other.rightUnit))
			return false;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	Unit<Q> getBaseUnit() {
		return (Unit<Q>) leftUnit.getBaseUnit().times(rightUnit.getBaseUnit());
	}

	@Override Lambda<Double, Double> toBaseUnit() {
		if (toBaseUnitLambda == null) toBaseUnitLambda = new Lambda<Double, Double>(){

			public Double call(Double a) {
				return leftUnit.toBaseUnit().call(rightUnit.toBaseUnit().call(a));
			}
			
		};
		return toBaseUnitLambda;
	}

	@Override Lambda<Double, Double> fromBaseUnit() {
		if (fromBaseUnitLambda == null) fromBaseUnitLambda = new Lambda<Double, Double>(){

			public Double call(Double a) {
				return leftUnit.fromBaseUnit().call(rightUnit.fromBaseUnit().call(a));
			}
			
		};
		return fromBaseUnitLambda;
	
	}
}