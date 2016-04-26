package com.lordvlad.units;

import java.io.Serializable;

import com.lordvlad.quantities.Quantity;

class Scale implements Serializable{
	private static final long serialVersionUID = -5007326384129272459L;
	final double scaling;
	final String prefix;

	public Scale(String prefix, double scaling) {
		this.prefix = prefix;
		this.scaling = scaling;
	}

	public <Q extends Quantity> Unit<Q> scale(Unit<Q> u) {
		return ScaledUnit.of(u, this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		long temp;
		temp = Double.doubleToLongBits(scaling);
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
		Scale other = (Scale) obj;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		if (Double.doubleToLongBits(scaling) != Double.doubleToLongBits(other.scaling))
			return false;
		return true;
	}
	
	
}